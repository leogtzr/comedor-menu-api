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

		final Sheet sheet = workbook.getSheetAt(0);

		final Optional<Menu> menu = MenuUtils.extractMenuFromSheet(sheet);
		// System.out.println(menu.);

		workbook.close();

	}

}
