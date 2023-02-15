package studySelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ArletHandling {

	public static void main(String[] args) throws InterruptedException {
		WebDriver chr = new ChromeDriver();
		chr.get("https://rahulshettyacademy.com/AutomationPractice/");
		chr.manage().window().maximize();

		NotificationArlet(chr);
		ConfirmationArlet(chr);

		chr.quit();
	}

	public static void NotificationArlet(WebDriver chr) throws InterruptedException {
		chr.findElement(By.id("name")).sendKeys("Lama");
		chr.findElement(By.id("alertbtn")).click();
		Thread.sleep(1000);
		System.out.println(chr.switchTo().alert().getText());// lấy text trên arlet
		System.out.println(chr.switchTo().alert().getText().contains("Lama")); // Kiểm tra text có xuất hiện key được
																				// nhập ko
		chr.switchTo().alert().accept();
	}

	public static void ConfirmationArlet(WebDriver chr) throws InterruptedException {
		chr.findElement(By.id("name")).sendKeys("Lama");
		chr.findElement(By.id("confirmbtn")).click();
		System.out.println(chr.switchTo().alert().getText());// lấy text trên arlet
		chr.switchTo().alert().dismiss(); // Cancel
		// chr.switchTo().alert().accept(); // OK
	}
}
