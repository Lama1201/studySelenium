import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment7 {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://qaclickacademy.com/practice.php");
		chr.manage().window().maximize();
		
		// Number of row
		System.out.println("Number of row: " + chr.findElements(By.xpath("//table[@name='courses']//tr")).size());
		//Number of col
		System.out.println("Number of column: " + chr.findElements(By.xpath("(//table[@name='courses']//tr)[1]//th")).size());
		// content of row No.2
		 List<WebElement> row2 = chr.findElements(By.xpath("//table[@name='courses']//tr")).get(2).findElements(By.tagName("td"));
		 for(WebElement col: row2) {
			 System.out.print(col.getText()+" ");
		 }
	}
}
