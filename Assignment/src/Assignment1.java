import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment1 {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://rahulshettyacademy.com/AutomationPractice/");
		chr.manage().window().maximize();
		// Check and uncheck first checkbox
		chr.findElement(By.id("checkBoxOption1")).click();
		System.out.println("First check box is checked:" + chr.findElement(By.id("checkBoxOption1")).isSelected());
		chr.findElement(By.id("checkBoxOption1")).click();
		System.out.println("First check box is checked:" + chr.findElement(By.id("checkBoxOption1")).isSelected());
		// Count number of checkbox in page
		List<WebElement> checkBoxes = chr.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println("Number of checkbox: "+checkBoxes.size());
	}
}
