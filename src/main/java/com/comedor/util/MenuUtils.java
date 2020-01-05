package com.comedor.util;

import com.comedor.input.sheet.DayColumnIndexes;
import com.comedor.menu.Food;
import com.comedor.menu.MealType;
import com.comedor.menu.SaladBar;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Arrays;
import java.util.Optional;

public final class MenuUtils {

    private MenuUtils() {}

    private static final DataFormatter dataFormatter = new DataFormatter();

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

        try {
            final int kCal = (int)cellKCal.getNumericCellValue();
            food.setKCal(kCal);
        } catch (final IllegalStateException ex) {
            ex.printStackTrace();
            System.out.println("Then ... [" + cellKCal.getStringCellValue() + "], for: " + index);
        }

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

    public static int titleTagPosition(final Sheet sheet) {
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

        return pos;
    }

}
