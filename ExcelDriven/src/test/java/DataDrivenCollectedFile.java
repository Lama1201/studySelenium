import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenCollectedFile {
public static void main(String[] args) throws IOException {
		
		System.out.println(readExcel("C:\\Users\\Lama\\OneDrive\\Desktop","DemoData.xlsx","testdata","buyProducts"));
	}

	public static ArrayList<String> readExcel(String filePath, String fileName, String sheetName, String testCaseName) throws IOException {
		ArrayList<String> data = new ArrayList<String>();
        //Create a File object with the provided file path
        File file = new File(filePath);

        //Get all files in the directory
        File[] files = file.listFiles();

        //Loop through the files to find the Excel file with the matching name
        File excelFile = null;
        for (File f : files) {
            if (f.getName().equals(fileName)) {
                excelFile = f;
                break;
            }
        }

        //Check if the Excel file was found
        if (excelFile == null) {
            throw new FileNotFoundException("Excel file not found: " + fileName);
        }

        //Create a FileInputStream for the Excel file
        FileInputStream fis = new FileInputStream(excelFile);

        //Create a Workbook object based on the file extension
        Workbook workbook = null;
        if (fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(fis);
        } else if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(fis);
        }

        //Get the Sheet object with the specified name
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }

        //Loop through the rows of the sheet to find the row with the matching test case name
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(testCaseName)) {
                for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {
                	Cell cell = sheet.getRow(i).getCell(j);
					//check if data type of cell is string
					if(cell.getCellType()== CellType.STRING) {
						data.add(cell.getStringCellValue());
					}
					else {
//						String s = Double.toString(cell.getNumericCellValue());
//						if(s.split("[.]")[1].equalsIgnoreCase("0")) {
//							data.add(s.split("[.]")[0]);
//						}
//						else {
//							data.add(s);
//						}	
						data.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
					}
				}	
				break;
            }
        }

        //Close the FileInputStream and Workbook objects
        fis.close();
        workbook.close();
        return data;
    }

}
