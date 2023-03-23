import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

public class CdpCommandsTest {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--start-maximized");
		ChromeDriver chr = new ChromeDriver(options);

		DevTools devtools = chr.getDevTools();
		devtools.createSession();
		Map<String,Object> deviceMetrics = new HashMap<String,Object>();
		deviceMetrics.put("width",600);
		deviceMetrics.put("height",1000);
		deviceMetrics.put("deviceScaleFactor",50);
		deviceMetrics.put("mobile",true);
		chr.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
		chr.get("https://rahulshettyacademy.com/angularAppdemo/");
		chr.findElement(By.cssSelector(".navbar-toggler")).click();
		Thread.sleep(3000);
		chr.findElement(By.linkText("Library")).click();

	}

}
