package com.comedor.app;

import com.comedor.input.sheet.DayColumnIndexes;
import com.comedor.input.sheet.InputSheetFileRowIndexes;
import com.comedor.menu.*;
import com.comedor.util.MenuUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

// @SpringBootApplication
public class ComedorMenuApiApplication {

	public static void main(final String[] args) throws IOException, InvalidFormatException {
		// SpringApplication.run(ComedorMenuApiApplication.class, args);

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		final Workbook workbook = WorkbookFactory.create(new File(args[0]));

		// final Sheet lastSheet = workbook.getSheetAt(workbook.getNumberOfSheets() - 3);
		final Sheet lastSheet = workbook.getSheetAt(0);
		System.out.println(lastSheet.getSheetName());

		final DataFormatter dataFormatter = new DataFormatter();

		// System.out.println(lastSheet.getRow(4).getCell(0));
		System.out.println("Title index is: " + InputSheetFileRowIndexes.TITLE.get());

		final int titleRowPosition = MenuUtils.titleTagPosition(lastSheet);
		System.out.println("Title row pos: " + titleRowPosition);
		InputSheetFileRowIndexes.TITLE.adjust(titleRowPosition);

		System.out.println("Title index is now: " + InputSheetFileRowIndexes.TITLE.get());

		final Menu menu = new Menu();
		final String title = lastSheet.getRow(InputSheetFileRowIndexes.TITLE.get()).getCell(0).getStringCellValue();
		menu.setTitle(title);

		// All the following code is iterable ... I mean ... I can use a single variable with a loop using X.values()
		final Row breakFastRow = lastSheet.getRow(InputSheetFileRowIndexes.BREAKFAST.get());
		final Row mainRow1 = lastSheet.getRow(InputSheetFileRowIndexes.MAIN1.get());
		final Row mainRow2 = lastSheet.getRow(InputSheetFileRowIndexes.MAIN2.get());
		final Row antojitosRow = lastSheet.getRow(InputSheetFileRowIndexes.ANTOJITO.get());
		final Row side1Row = lastSheet.getRow(InputSheetFileRowIndexes.SIDE1.get());
		final Row side2Row = lastSheet.getRow(InputSheetFileRowIndexes.SIDE2.get());
		final Row soupOrCreamRow = lastSheet.getRow(InputSheetFileRowIndexes.SOUP_OR_CREAM.get());
		final Row dessertRow = lastSheet.getRow(InputSheetFileRowIndexes.DESSERT.get());
		final Row lightRow = lastSheet.getRow(InputSheetFileRowIndexes.LIGHT.get());

		final Row saladsRow1 = lastSheet.getRow(InputSheetFileRowIndexes.SALADS.get());
		final Row saladsRow2 = lastSheet.getRow(InputSheetFileRowIndexes.SALADS.get() + 1);
		final Row saladsRow3 = lastSheet.getRow(InputSheetFileRowIndexes.SALADS.get() + 2);

		final Map<Day, DayMeal> dayMealMenu = new HashMap<>();

		for (final DayColumnIndexes i : DayColumnIndexes.values()) {
			final List<Food> alternatives = new ArrayList<>();
			final DayMeal dayMeal = new DayMeal();

			final Optional<Food> breakFast = MenuUtils.extractMealInfoFromRow(breakFastRow, i, MealType.BREAKFAST);
			final Optional<Food> opcion1 = MenuUtils.extractMealInfoFromRow(mainRow1, i, MealType.PRINCIPAL);
			final Optional<Food> opcion2 = MenuUtils.extractMealInfoFromRow(mainRow2, i, MealType.PRINCIPAL);
			final Optional<Food> antojito = MenuUtils.extractMealInfoFromRow(antojitosRow, i, MealType.ANTOJITO);
			final Optional<Food> side1 = MenuUtils.extractMealInfoFromRow(side1Row, i, MealType.GUARNICION);
			final Optional<Food> side2 = MenuUtils.extractMealInfoFromRow(side2Row, i, MealType.GUARNICION);
			final Optional<Food> soupOrCream = MenuUtils.extractMealInfoFromRow(soupOrCreamRow, i, MealType.SOPA_O_CREMA);
			final Optional<Food> dessert = MenuUtils.extractMealInfoFromRow(dessertRow, i, MealType.POSTRE);
			final Optional<Food> light = MenuUtils.extractMealInfoFromRow(lightRow, i, MealType.LIGHT);

			final Row[] saladRows = {saladsRow1, saladsRow2, saladsRow3};
			final Optional<SaladBar> salads = MenuUtils.extractFromSaladsRow(saladRows, i);

			opcion1.ifPresent(alternatives::add);
			opcion2.ifPresent(alternatives::add);
			antojito.ifPresent(alternatives::add);
			side1.ifPresent(alternatives::add);
			side2.ifPresent(alternatives::add);
			soupOrCream.ifPresent(alternatives::add);
			dessert.ifPresent(alternatives::add);
			light.ifPresent(alternatives::add);

			dayMeal.setLunchDinnerFoodAlternatives(alternatives);
			breakFast.ifPresent(dayMeal::setBreakfast);

			salads.ifPresentOrElse(sl -> dayMeal.setSalads(sl.getSalads()), () -> dayMeal.setSalads(new ArrayList<>()));

			dayMealMenu.put(i.day(), dayMeal);
		}

		dayMealMenu.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(v);
		});

		workbook.close();

	}

}
