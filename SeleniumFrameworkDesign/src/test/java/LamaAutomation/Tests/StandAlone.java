package LamaAutomation.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAlone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver chr = new ChromeDriver();
		chr.manage().window().maximize();
		chr.get("https://rahulshettyacademy.com/client");
		
		chr.findElement(By.id("userEmail")).sendKeys("faker@email.com");
		chr.findElement(By.id("userPassword")).sendKeys("Password1@");
		chr.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(chr,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = chr.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		for(WebElement p: products) {
			if(p.findElement(By.tagName("b")).getText().equalsIgnoreCase("adidas original")) {
				System.out.println(p.findElement(By.tagName("b")).getText());				
				p.findElement(By.xpath(".//button[2]")).click();
			}
		}
	}

}
