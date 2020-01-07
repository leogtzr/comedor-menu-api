package com.comedor.util;

import com.comedor.input.sheet.DayColumnIndexes;
import com.comedor.input.sheet.InputSheetFileRowIndexes;
import com.comedor.menu.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;

public final class MenuUtils {

    private MenuUtils() {}

    public static Optional<Food> extractMealInfoFromRow(
            final Row row,
            final DayColumnIndexes index,
            final MealType mealType
            ) {
        final Food food = new Food();

        final Cell cellName = row.getCell(index.index());
        final String value = cellName.getStringCellValue();
        if (cellName == null || value.trim().isEmpty()) {
            return Optional.empty();
        }
        final Cell cellKCal = row.getCell(index.index() + 1);

        final int kCal = (int)cellKCal.getNumericCellValue();
        food.setKCal(kCal);
        food.setMealType(mealType);
        food.setName(cellName.getStringCellValue());

        return Optional.of(food);
    }

    public static Optional<SaladBar> extractFromSaladsRow(final Row[] saladsRows, final DayColumnIndexes dayIndex) {
        final Cell salads1 = saladsRows[0].getCell(dayIndex.index());
        final Cell salads2 = saladsRows[1].getCell(dayIndex.index());
        final Cell salads3 = saladsRows[2].getCell(dayIndex.index());

        if (!salads1.getStringCellValue().isEmpty()
                && !salads2.getStringCellValue().isEmpty()
                && !salads3.getStringCellValue().isEmpty()) {
            return Optional.of(
                new SaladBar(
                    Arrays.asList(salads1.getStringCellValue(), salads2.getStringCellValue(), salads3.getStringCellValue())
                )
            );
        }
        return Optional.empty();
    }

    public static Optional<Integer> titleTagPosition(final Sheet sheet) {
        int pos = 4;

        for (int i = 1; i < Constants.MAX_VALUE_TO_FIND_TITLE; i++) {
            final Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            final Cell cell = row.getCell(0);
            final String value = cell.getStringCellValue();
            if (value.contains("MENU SEMANAL DEL") || value.contains("MENUS")) {
                pos = i;
                break;
            }
        }

        return pos >= Constants.MAX_VALUE_TO_FIND_TITLE ? Optional.empty() : Optional.of(pos);
    }

    public static Optional<Menu> extractMenuFromSheet(final Sheet sheet) {

        final Optional<Integer> titleRowPosition = MenuUtils.titleTagPosition(sheet);
        if (titleRowPosition.isEmpty()) {
            return Optional.empty();
        }

        titleRowPosition.ifPresent(pos -> InputSheetFileRowIndexes.TITLE.adjust(pos));

        final Menu menu = new Menu();
        final String title = sheet.getRow(InputSheetFileRowIndexes.TITLE.get()).getCell(0).getStringCellValue();
        menu.setTitle(title);

        final Row breakFastRow = sheet.getRow(InputSheetFileRowIndexes.BREAKFAST.get());
        final Row mainRow1 = sheet.getRow(InputSheetFileRowIndexes.MAIN1.get());
        final Row mainRow2 = sheet.getRow(InputSheetFileRowIndexes.MAIN2.get());
        final Row antojitosRow = sheet.getRow(InputSheetFileRowIndexes.ANTOJITO.get());
        final Row side1Row = sheet.getRow(InputSheetFileRowIndexes.SIDE1.get());
        final Row side2Row = sheet.getRow(InputSheetFileRowIndexes.SIDE2.get());
        final Row soupOrCreamRow = sheet.getRow(InputSheetFileRowIndexes.SOUP_OR_CREAM.get());
        final Row dessertRow = sheet.getRow(InputSheetFileRowIndexes.DESSERT.get());
        final Row lightRow = sheet.getRow(InputSheetFileRowIndexes.LIGHT.get());

        final Row saladsRow1 = sheet.getRow(InputSheetFileRowIndexes.SALADS.get());
        final Row saladsRow2 = sheet.getRow(InputSheetFileRowIndexes.SALADS.get() + 1);
        final Row saladsRow3 = sheet.getRow(InputSheetFileRowIndexes.SALADS.get() + 2);

        final Map<Day, DayMeal> dayMealMenu = new HashMap<>();

        for (final DayColumnIndexes i : DayColumnIndexes.values()) {
            final List<Food> alternatives = new ArrayList<>();
            final DayMeal dayMeal = new DayMeal();

            final Optional<Food> breakFast = MenuUtils.extractMealInfoFromRow(breakFastRow, i, MealType.BREAKFAST);
            final Optional<Food> option1 = MenuUtils.extractMealInfoFromRow(mainRow1, i, MealType.PRINCIPAL);
            final Optional<Food> option2 = MenuUtils.extractMealInfoFromRow(mainRow2, i, MealType.PRINCIPAL);
            final Optional<Food> antojito = MenuUtils.extractMealInfoFromRow(antojitosRow, i, MealType.ANTOJITO);
            final Optional<Food> side1 = MenuUtils.extractMealInfoFromRow(side1Row, i, MealType.GUARNICION);
            final Optional<Food> side2 = MenuUtils.extractMealInfoFromRow(side2Row, i, MealType.GUARNICION);
            final Optional<Food> soupOrCream = MenuUtils.extractMealInfoFromRow(soupOrCreamRow, i, MealType.SOPA_O_CREMA);
            final Optional<Food> dessert = MenuUtils.extractMealInfoFromRow(dessertRow, i, MealType.POSTRE);
            final Optional<Food> light = MenuUtils.extractMealInfoFromRow(lightRow, i, MealType.LIGHT);

            final Row[] saladRows = {saladsRow1, saladsRow2, saladsRow3};
            final Optional<SaladBar> salads = MenuUtils.extractFromSaladsRow(saladRows, i);

            breakFast.ifPresent(dayMeal::setBreakfast);
            option1.ifPresent(alternatives::add);
            option2.ifPresent(alternatives::add);
            antojito.ifPresent(alternatives::add);
            side1.ifPresent(alternatives::add);
            side2.ifPresent(alternatives::add);
            soupOrCream.ifPresent(alternatives::add);
            dessert.ifPresent(alternatives::add);
            light.ifPresent(alternatives::add);

            dayMeal.setLunchDinnerFoodAlternatives(alternatives);

            salads.ifPresentOrElse(sl -> dayMeal.setSalads(sl.getSalads()), () -> dayMeal.setSalads(new ArrayList<>()));

            dayMealMenu.put(i.day(), dayMeal);
        }

        menu.setMenu(dayMealMenu);

        return Optional.of(menu);
    }

}
