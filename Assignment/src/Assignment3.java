import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment3 {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://rahulshettyacademy.com/loginpagePractise/#");
		chr.manage().window().maximize();
		
		// get username and password
		String userName = chr.findElement(By.cssSelector("p.text-center.text-white")).getText().split("is")[1].trim().split("and")[0].trim();
		String pw = chr.findElement(By.cssSelector("p.text-center.text-white")).getText().split("and")[1].trim().split("is")[1].replace(")","").trim();
		System.out.println(userName + " " + pw);
		
		// fill the form and click sign in
		chr.findElement(By.id("username")).sendKeys(userName);
		chr.findElement(By.id("password")).sendKeys(pw);
		chr.findElement(By.xpath("//input[@id='usertype' and @value='user']")).click();
		WebDriverWait w = new WebDriverWait(chr,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(chr.findElement(By.id("okayBtn"))));
		chr.findElement(By.id("okayBtn")).click();		
		Select role = new Select(chr.findElement(By.xpath("//select")));
		role.selectByValue("consult");
		chr.findElement(By.id("terms")).click();
		chr.findElement(By.id("signInBtn")).click();
		
		// Add all item to cart and checkout
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.btn.btn-info")));
		List<WebElement> buttons = chr.findElements(By.cssSelector("button.btn.btn-info"));
		for(WebElement n : buttons) {
		n.click();
		}
		chr.findElement(By.xpath("//a[contains(.,'Checkout')]")).click();
	}

}
