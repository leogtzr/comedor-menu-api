package com.comedor.util;

import com.comedor.menu.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MenuUtilsTest {

    @Test
    void shouldBeAbleToParseMenuFile() throws IOException, InvalidFormatException {
        final String path = "src/test/resources/menu.xls";

        final File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        final Workbook workbook = WorkbookFactory.create(new File(absolutePath));

        final int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            final Sheet sheet = workbook.getSheetAt(i);
            final String sheetName = sheet.getSheetName();
            final Optional<Menu> sheetOpt = MenuUtils.extractMenuFromSheet(sheet);
            if (sheetOpt.isEmpty()) {
                fail(String.format("Should have parsed: %s", sheetName));
            }
        }

        workbook.close();
    }

    @Test
    void shouldExtractMenuFromSheet1() throws IOException, InvalidFormatException {
        final String path = "src/test/resources/menu.xls";

        final File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        final Workbook workbook = WorkbookFactory.create(new File(absolutePath));
        final Sheet sheet = workbook.getSheetAt(0);

        final Optional<Menu> sheetOpt = MenuUtils.extractMenuFromSheet(sheet);

        if (sheetOpt.isEmpty()) {
            fail("should be able to parse sheet");
        }

        final Menu menu = sheetOpt.get();
        final DayMeal menuMonday = menu.getMenu().get(Day.MONDAY);
        final List<Food> lunchDinnerFoodAlternatives = menuMonday.getLunchDinnerFoodAlternatives();
        if (lunchDinnerFoodAlternatives == null || lunchDinnerFoodAlternatives.isEmpty()) {
            fail("lunch food alternatives should not be empty ... ");
        }

        final int expectedNumberOfFoodAlternatives = 8;
        assertEquals(
                expectedNumberOfFoodAlternatives,
                lunchDinnerFoodAlternatives.size(),
                String.format("Got=%d, expected=%d for food alternatives.", lunchDinnerFoodAlternatives.size(), expectedNumberOfFoodAlternatives)
        );

        final Food expectedBreakfast = new Food("caldillo de papa con chilaca", 315, MealType.BREAKFAST);
        assertEquals(expectedBreakfast, menuMonday.getBreakfast());

        final List<Food> expectedMondayFoodAlternatives =
                Arrays.asList(
                        new Food("pozole", 345, MealType.MAIN1),
                        new Food("chilaquiles verdes con pollo", 350, MealType.MAIN2),
                        new Food("burrito/ quesadilla", 350, MealType.ANTOJITO),
                        new Food("fideos", 145, MealType.SIDE1),
                        new Food("frijoles con curtidos", 135, MealType.SIDE2),
                        new Food("crema de verduras", 125, MealType.SOUP_OR_CREAM),
                        new Food("arroz con leche", 268, MealType.DESSERT),
                        new Food("wrap de pollo", 198, MealType.LIGHT)
                );

        final List<String> expectedMondaySalads =
                Arrays.asList("ENSALADA GRIEGA", "REPOLLO CON RABANOS", "CEBOLLA FILETEADA");

        assertEquals(menuMonday.getSalads(), expectedMondaySalads);
        assertEquals(lunchDinnerFoodAlternatives, expectedMondayFoodAlternatives);

        workbook.close();
    }
}
