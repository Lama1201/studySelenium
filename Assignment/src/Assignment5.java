import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment5 {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://the-internet.herokuapp.com");
		chr.manage().window().maximize();
		
		chr.findElement(By.linkText("Nested Frames")).click();
		chr.switchTo().frame(0).switchTo().frame("frame-middle");		
		System.out.println(chr.findElement(By.id("content")).getText());		
		chr.switchTo().parentFrame();
		System.out.println(chr.findElements(By.tagName("frame")).size());
		chr.switchTo().parentFrame();
		System.out.println(chr.findElements(By.tagName("frame")).size());
		chr.quit();
	}

}
