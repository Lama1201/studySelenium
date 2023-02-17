

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Assignment4 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver chr = new ChromeDriver();
		chr.get("https://the-internet.herokuapp.com");
		chr.manage().window().maximize();
		
		Thread.sleep(3000);
		chr.findElement(By.xpath("//*[text()='Multiple Windows']")).click();
		chr.findElement(By.xpath("//*[text()='Click Here']")).click();
		//Set<String> windows = chr.getWindowHandles();
		//Iterator<String> it = windows.iterator();
		//String parent = it.next();
		//String child = it.next();
		
		ArrayList<String> tabs=new ArrayList<String> (chr.getWindowHandles());
		chr.switchTo().window(tabs.get(1));
		System.out.println(chr.findElement(By.xpath("//h3")).getText());
		chr.switchTo().window(tabs.get(0));
		System.out.println(chr.findElement(By.xpath("//h3")).getText());
		chr.quit();
	}

}
