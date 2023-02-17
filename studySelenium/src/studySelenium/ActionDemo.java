package studySelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionDemo {

	public static void main(String[] args) throws InterruptedException {
		WebDriver chr = new ChromeDriver();
		chr.get("https://cellphones.com.vn/");
		chr.manage().window().maximize();
		
		Actions a = new Actions(chr);
		a.moveToElement(chr.findElement(By.xpath("//div[@class='block-top-home is-flex']//span[contains(text(),'Điện thoại')]"))).build().perform();
		a.moveToElement(chr.findElement(By.xpath("//input[@id='inp$earch']"))).click().keyDown(Keys.SHIFT).sendKeys("h").keyUp(Keys.SHIFT).sendKeys("ello ").build().perform();
		Thread.sleep(2000);
		chr.findElement(By.xpath("//input[@id='inp$earch']")).sendKeys("S 23 ultra");
		// CHọn toàn bộ text trong text box
		a.moveToElement(chr.findElement(By.xpath("//input[@id='inp$earch']"))).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
		chr.findElement(By.xpath("//input[@id='inp$earch']")).clear();
		a.sendKeys(chr.findElement(By.xpath("//input[@id='inp$earch']")), "Welcome").perform();
	}

}
