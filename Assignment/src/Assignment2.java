import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment2 {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://rahulshettyacademy.com/angularpractice/");
		chr.manage().window().maximize();
		
		chr.findElement(By.name("name")).sendKeys("Lama");
		chr.findElement(By.name("email")).sendKeys("Lama@email.com");
		chr.findElement(By.id("exampleInputPassword1")).sendKeys("Password");
		chr.findElement(By.id("exampleCheck1")).click();
		Select gender = new Select(chr.findElement(By.id("exampleFormControlSelect1")));
		gender.selectByVisibleText("Male");
		chr.findElement(By.id("exampleFormControlSelect1")).click();
		chr.findElement(By.name("bday")).sendKeys("12-31-1990");
		chr.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println(chr.findElement(By.xpath("//div[contains(@class,'alert-success')]")).getText());
	}
}
