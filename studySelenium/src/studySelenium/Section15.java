package studySelenium;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class Section15 {

	public static void main(String[] args) throws IOException {
		WebDriver chr = new ChromeDriver();
		chr.get("https://rahulshettyacademy.com/angularpractice/");
		chr.switchTo().newWindow(WindowType.TAB);
		
		// Lấy data ở 1 tab và sử dụng ở 1 tab khác
		ArrayList<String> tabs=new ArrayList<String> (chr.getWindowHandles());		
		chr.switchTo().window(tabs.get(1));
		chr.get("https://rahulshettyacademy.com/");
		String courseName = chr.findElement(By.xpath("(//h2//a[contains(@href,'https://courses.rahulshettyacademy.com/p/')])[1]")).getText();
		chr.switchTo().window(tabs.get(0));
		chr.findElement(By.xpath("//input[@name='name']")).sendKeys(courseName);
		
		//Chụp hình 1 thành phần trang web
		File screenShot =((TakesScreenshot)chr.findElement(By.xpath("//input[@name='name']"))).getScreenshotAs(OutputType.FILE);
		//File screenShot = chr.findElement(By.xpath("//input[@name='name']")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File("C:\\Users\\anhlu\\OneDrive\\Desktop\\screenshot.png"));
		
		// Lấy kích thước element
		System.out.println(chr.findElement(By.xpath("//input[@name='name']")).getRect().getHeight());
		System.out.println(chr.findElement(By.xpath("//input[@name='name']")).getRect().getWidth());
		chr.quit();
	}

}
