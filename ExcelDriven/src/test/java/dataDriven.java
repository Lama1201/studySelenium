import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static Object[][] readExcel(String filePath, String sheetName, String testCaseName) throws IOException {
		
		try (InputStream inp = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(inp);
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}
			Object[][] data = new Object[sheet.getLastRowNum()+1][sheet.getRow(0).getLastCellNum()];
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				if (sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(testCaseName)) {
					for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {
						Cell cell = sheet.getRow(i).getCell(j);
						switch (cell.getCellType()) {
	                    case STRING:
	                        data[i][j] = cell.getStringCellValue();
	                        System.out.println(data[i][j]);
	                        break;
	                    case NUMERIC:
	                        data[i][j] = cell.getNumericCellValue();
	                        System.out.println(data[i][j]);
	                        break;
	                    case BOOLEAN:
	                        data[i][j] = cell.getBooleanCellValue();
	                        System.out.println(data[i][j]);
	                        break;
	                    default:
                        data[i][j] = null;
						}
					}
				}
			}
			System.out.println(data);
	        workbook.close();
			return data;
		}
	}

	public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub
		System.out.println(readExcel("C:\\Users\\Lama\\OneDrive\\Desktop\\DemoData.xlsx","testdata", "buyProducts"));
	}

}