package studySelenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;



public class Section13 {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); 
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking")); // Block popup
		// Add the WebDriver proxy capability.
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("proxy.garage-standard.jp:8213");
		options.setCapability("proxy", proxy);
		options.setAcceptInsecureCerts(true); // bỏ qua https certification
		
		WebDriver chr = new ChromeDriver(options);
		chr.get("https://qaclickacademy.com/practice.php");		
		
		//Login(chr);
		//TakeScreenShot(chr);
		CheckBrokenUrl(chr);
		

	}
	public static void Login(WebDriver chr) {
		chr.findElement(By.id("email")).sendKeys("anh.luuhai@live.com");
		chr.findElement(By.id("password")).sendKeys("Password1@");
		chr.findElement(By.xpath("//button[@type='submit']")).click();
	}	
	
	public static void DeleteCookie(WebDriver chr) {
		chr.manage().deleteAllCookies(); // Xóa cookie
		chr.navigate().refresh();
		System.out.println(chr.getCurrentUrl());
	}
	
	public static void TakeScreenShot(WebDriver chr) throws IOException {
		File screenShot =((TakesScreenshot)chr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File("C:\\Users\\Lama\\OneDrive\\Desktop\\screenshot.png"));
	}
	
	public static void CheckBrokenUrl(WebDriver chr) throws MalformedURLException, IOException {
		SoftAssert a = new SoftAssert(); // Sử dụng SoftAssert để code ko bị stop giữa chừng
		List<WebElement> links = chr.findElements(By.tagName("a"));		
		// Duyệt qua từng liên kết và kiểm tra liên kết có bị hỏng hay không
		for (WebElement link : links) {		
			if(link.getAttribute("href").isBlank()) {

			}
			else {
				// Cách lấy response code
				HttpURLConnection connection = (HttpURLConnection) new URL(link.getAttribute("href")).openConnection();
			    connection.connect();
			    int responseCode = connection.getResponseCode();
			    if (responseCode > 400) {
			        //System.out.println("Broken link: " + link.getAttribute("href"));
			        a.assertTrue(responseCode < 400, link.getAttribute("href") +" is broken with code "+ responseCode);
			    }
			}		   
		}
		a.assertAll();
	}
	
}
