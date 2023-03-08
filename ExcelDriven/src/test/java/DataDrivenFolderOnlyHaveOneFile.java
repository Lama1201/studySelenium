import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;

public class DataDrivenFolderOnlyHaveOneFile {

	public static void main(String[] args) throws IOException {
		DataDrivenFolderOnlyHaveOneFile a = new DataDrivenFolderOnlyHaveOneFile();
		System.out.println(a.readExcel("C:\\Users\\Lama\\OneDrive\\Desktop\\DemoData.xlsx", "testdata", "buyProducts"));
	}

	public ArrayList<String> readExcel(String filePath, String sheetName, String testCaseName) throws IOException {
		ArrayList<String> data = new ArrayList<String>();
		
		try (InputStream inp = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(inp);
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}
			for (int i = 1; i <=sheet.getLastRowNum(); i++) {
				if (sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(testCaseName)) {
					for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {
						Cell cell = sheet.getRow(i).getCell(j);
						//check if data type of cell is string
						if(cell.getCellType()== CellType.STRING) {
							data.add(cell.getStringCellValue());
						}
						else {
							data.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
						}
					}	
					break;
				}
			}
	        workbook.close();
			return data;
		}
	}
	
	// return Object[][] to use as dataProvider of testNG
	public Object[][] readExcelDataProvider(String filePath, String sheetName, String testCaseName) throws IOException {
		DataFormatter formatter = new DataFormatter();
		try (InputStream inp = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(inp);
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}
			int r =0;
			List<Object[]> listRows = new ArrayList<Object[]>();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Object[] row =new Object[sheet.getRow(i).getLastCellNum()-1];
				if (sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(testCaseName)) {
					for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {
						Cell cell = sheet.getRow(i).getCell(j);
//						switch (cell.getCellType()) {
//	                    case STRING:
//	                        row[-1] = cell.getStringCellValue();
//	                        break;
//	                    case NUMERIC:
//	                        row[-1] = cell.getNumericCellValue();
//	                        break;
//	                    case BOOLEAN:
//	                        row[-1] = cell.getBooleanCellValue();
//	                        break;
//	                    default:
//	                        row[-1] = " ";
//						}
//						if(cell.getCellType()== CellType.STRING) {
//							row[j-1] = cell.getStringCellValue();
//						}
//						else {						
//							row[j-1] = NumberToTextConverter.toText(cell.getNumericCellValue());
//						}	
						row[j-1] = formatter.formatCellValue(cell);
					}
					listRows.add(row);
					r++;
				}
				
			}
			Object[][] data = new Object[r][];
			for(int n =0;n<r;n++) {
				data[n]= listRows.get(n);
			}			
	        workbook.close();
	    	return data;
			
		}
	}
}
