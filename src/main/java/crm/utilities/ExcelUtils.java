package crm.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import crm.constants.FrameworkConstants;
import crm.exceptions.FrameworkException;
import crm.exceptions.InvalidPathForExcelException;

public final class ExcelUtils {

	private ExcelUtils() {
	}

	public static Object[][] readMultipleData(String sheetName) {
		try (FileInputStream fis = new FileInputStream(FrameworkConstants.getExcelpath())) {
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			int lastRow = sh.getLastRowNum();
			int lastCel = sh.getRow(0).getLastCellNum();

			Object[][] data = new Object[lastRow][lastCel];

			for (int i = 0; i < lastRow; i++) {
				for (int j = 0; j < lastCel; j++) {
					data[i][j] = sh.getRow(i + 1).getCell(j).getStringCellValue();
				}
			}
			return data;
			
		} catch (FileNotFoundException e) {
			throw new InvalidPathForExcelException("Excel File you are trying to read is not found", e);
		} catch (IOException e) {
			throw new FrameworkException("Some IO exception occurred while reading excel file", e);
		}
	}

	public static void writeDataIntoExcel(String sheetName, int rowNo, int celNo, String value) {
		try (FileInputStream fis = new FileInputStream(FrameworkConstants.getExcelpath())) {
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet(sheetName);
			if (sheet == null) {
				sheet = wb.createSheet(sheetName);
			}
			sheet.createRow(rowNo).createCell(celNo).setCellValue(value);
			try (FileOutputStream fos = new FileOutputStream(FrameworkConstants.getExcelpath())) {
				wb.write(fos);
			}
		} catch (FileNotFoundException e) {
			throw new InvalidPathForExcelException("Excel File you are trying to write is not found", e);
		} catch (IOException e) {
			throw new FrameworkException("Some IO exception occurred while writing into excel file", e);
		}
	}

	public static String readDataFromExcel(String sheetName, int rowNo, int celNo) {
		try (FileInputStream fis = new FileInputStream(FrameworkConstants.getExcelpath())) {
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet(sheetName);
			if (sheet != null) {
				return sheet.getRow(rowNo).getCell(celNo).getStringCellValue();
			} else {
				return null;
			}
		} catch (FileNotFoundException e) {
			throw new InvalidPathForExcelException("Excel File you are trying to read is not found", e);
		} catch (IOException e) {
			throw new FrameworkException("Some IO exception occurred while reading excel file", e);
		}
	}
}
