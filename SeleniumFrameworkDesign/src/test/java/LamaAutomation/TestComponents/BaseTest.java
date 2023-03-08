package LamaAutomation.TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import LamaAutomation.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		//properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+"//src//main//java//LamaAutomation//resources//GlobalData.properties");
		prop.load(fis);
		//Kiểm tra nếu có nhập browser trong terminal thì lấy browser, ko có thì lấy trong file GlobalData.properties
		// nếu đều kiện (!=) là true thì chạy (?), false thì chạy ()		
		String browserName = System.getProperty("browser", null) != null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();			
			WebDriverManager.chromedriver().setup();	
			// allow to set test run in headless mode
						if(browserName.contains("headless")) {
							options.addArguments("headless"); // run test in headless mode								
						}		
			driver = new ChromeDriver(options);	
			driver.manage().window().setSize(new Dimension(1920,1080)); // this for headless mode
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();			
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//Read json to string
		String jsonContent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to HashMap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public Object[][] readExcel(String filePath, String sheetName, String testCaseName) throws IOException {
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
						row[j-1] = formatter.formatCellValue(sheet.getRow(i).getCell(j));		
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
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchBrowser() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	public String takeScreenshot(String methodName, WebDriver driver) throws InterruptedException, IOException {
        Thread.sleep(1000);      
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);        
        File theDir = new File("./reports/");
        if (!theDir.exists()) {
              theDir.mkdirs();
        }
        FileUtils.copyFile(screenShot, new File("./reports/" + methodName + "_"+ dateFormat.format(new Date())+".png")); 
        return System.getProperty("user.dir")+"/reports/" + methodName + "_"+ dateFormat.format(new Date())+".png";
    }
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}	

}
