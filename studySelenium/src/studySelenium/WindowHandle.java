package studySelenium;

import java.util.Set;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowHandle {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://rahulshettyacademy.com/loginpagePractise/#");
		chr.manage().window().maximize();
		chr.findElement(By.className("blinkingText")).click();
		// Duyệt qua và ghi nhận các tab đang mở
		/*Set<String> windows = chr.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parent = it.next();
		String child = it.next();*/
		ArrayList<String> tabs=new ArrayList<String> (chr.getWindowHandles());
		
		chr.switchTo().window(tabs.get(0));
		WebDriverWait w = new WebDriverWait(chr,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='im-para red']")));
		String email = chr.findElement(By.xpath("//p[@class='im-para red']")).getText().split("at")[1].trim().split(" ")[0];
		System.out.println(email);
		
		chr.switchTo().window(tabs.get(1));
		chr.findElement(By.id("username")).sendKeys(email);
	}

}
