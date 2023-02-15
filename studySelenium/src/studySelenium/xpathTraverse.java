package studySelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class xpathTraverse {

	public static void main(String[] args) throws InterruptedException {
		WebDriver chr1 = new ChromeDriver();
		chr1.get("https://rahulshettyacademy.com/AutomationPractice/");
		chr1.manage().window().maximize();
		
		// From parent to child
		chr1.findElement(By.xpath("//div/a/button[text()='Home']")).click();
		chr1.navigate().back();
		
		// From sibling to sibling (ngang hàng)
		System.out.println(chr1.findElement(By.xpath("//div/button[1]/following-sibling::button[1]")).getText());
		System.out.println(chr1.findElement(By.xpath("//div/button[1]/following-sibling::button[2]")).getText());
		
		Thread.sleep(2000);
		// Nếu trong xpath có space " " thì thay thế space bằng dấu chấm "." 
		// From child to parent
		chr1.findElement(By.xpath("//button[contains(text(),'Practice')]/parent::div/parent::header/a[@class='blinkingText']")).click();
		
		chr1.quit();

	}

}
