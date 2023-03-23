import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {

	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--start-maximized");
		ChromeDriver chr = new ChromeDriver(options);
		DevTools devtools = chr.getDevTools();
		devtools.createSession();
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("latitude",40);
		params.put("longitude",3);
		params.put("accuracy",1);	
		chr.executeCdpCommand("Emulation.setGeolocationOverride", params);
		chr.get("https://www.gps-coordinates.net/my-location");

	}

}
