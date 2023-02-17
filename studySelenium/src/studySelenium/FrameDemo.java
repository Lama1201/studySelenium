package studySelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FrameDemo {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://jqueryui.com/droppable/");
		chr.manage().window().maximize();
		
		// Tìm có tất cả bao nhiêu frame và dùng index đễ chuyển tới frame
		//System.out.println(chr.findElements(By.tagName("iframe")).size());
		//chr.switchTo().frame(0);
		
		chr.switchTo().frame(chr.findElement(By.xpath("//iframe[@class='demo-frame']")));
		Actions a= new Actions(chr);
		WebElement smallSquare = chr.findElement(By.id("draggable"));
		WebElement bigSquare = chr.findElement(By.id("droppable"));
		a.dragAndDrop(smallSquare,bigSquare).perform();
		chr.quit();
	}

}
