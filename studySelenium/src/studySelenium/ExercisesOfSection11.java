package studySelenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ExercisesOfSection11 {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://qaclickacademy.com/practice.php");
		chr.manage().window().maximize();
		
		// 1. Tổng số link trong trang web
		System.out.println(chr.findElements(By.tagName("a")).size());
		
		// 2. Tổng số link nằm trong footer
		WebElement footer = chr.findElement(By.id("gf-BIG"));
		System.out.println(footer.findElements(By.tagName("a")).size());
		//System.out.println(chr.findElement(By.id("gf-BIG")).findElements(By.tagName("a")).size());
		
		// 3. Tổng số link nằm ở cột đầu tiên trong footer
		WebElement firstCol = footer.findElement(By.xpath("(//ul)[1]"));
		System.out.println(firstCol.findElements(By.tagName("a")).size());
		//System.out.println(chr.findElement(By.id("gf-BIG")).findElement(By.xpath("(//ul)[1]")).findElements(By.tagName("a")).size());
		
		// 4. Click vào từng link trong 3. sau đó kiểm tra từng tab mới mở
		List<WebElement> links = firstCol.findElements(By.tagName("a"));
		//List<WebElement> links = chr.findElement(By.id("gf-BIG")).findElement(By.xpath("(//ul)[1]")).findElements(By.tagName("a"));
		
		for(WebElement n: links) {
			String openLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
			n.sendKeys(openLink);
			//n.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		}
		ArrayList<String> tabs = new ArrayList<String>(chr.getWindowHandles());
		for(String n: tabs) {
			chr.switchTo().window(n);
			System.out.println(chr.getTitle());
		}
		
		chr.quit();
	}

}
