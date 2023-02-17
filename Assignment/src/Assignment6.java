import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment6 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver chr = new ChromeDriver();
		chr.get("https://qaclickacademy.com/practice.php");
		chr.manage().window().maximize();
		
		// 1 + 2. Click on random checkbox		
		String value = RandomChkbox(chr);
		System.out.println(value);		
		// 3. Select dropdown list same value with 1
		DropDown(chr,value);
		// 4 + 5. Enter text in 3 to 
		System.out.println("Check text '"+ value+"' is in messsage: "+VerifyAlert(chr,value));	
		Thread.sleep(3000);
		chr.quit();
	}
	public static String RandomChkbox(WebDriver chr) {
		List<WebElement> chkBox = chr.findElements(By.xpath("//input[@type='checkbox']"));
		int randomId = (int)(Math.random()*(chkBox.size()-1));
		chkBox.get(randomId).click();
		String value = chkBox.get(randomId).findElement(By.xpath("./parent::label")).getText();
		return value;		
	}
	
	public static void DropDown(WebDriver chr, String value) {
		Select dropDown = new Select(chr.findElement(By.id("dropdown-class-example")));
		dropDown.selectByVisibleText(value);
	}
	
	public static boolean VerifyAlert(WebDriver chr, String value) {
		chr.findElement(By.id("name")).sendKeys(value);
		chr.findElement(By.id("alertbtn")).click();
		boolean verify = chr.switchTo().alert().getText().contains(value);
		chr.switchTo().alert().accept();
		return verify;
	}
}
