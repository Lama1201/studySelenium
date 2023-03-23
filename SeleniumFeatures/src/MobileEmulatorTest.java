import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

public class MobileEmulatorTest {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--start-maximized");
		ChromeDriver chr = new ChromeDriver(options);

		DevTools devtools = chr.getDevTools();
		devtools.createSession();
		devtools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		chr.get("https://rahulshettyacademy.com/angularAppdemo/");
		chr.findElement(By.cssSelector(".navbar-toggler")).click();
		Thread.sleep(3000);
		chr.findElement(By.linkText("Library")).click();
		

	}

}
