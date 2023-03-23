import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.network.model.Request;
import org.openqa.selenium.devtools.v108.network.model.Response;
import org.openqa.selenium.devtools.v108.network.Network;


public class NetworkLogActivities {

	public static void main(String[] args) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--start-maximized");
		ChromeDriver chr = new ChromeDriver(options);
		DevTools devtools = chr.getDevTools();
		devtools.createSession();
		
		devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devtools.addListener(Network.requestWillBeSent(), request ->{
			Request req =request.getRequest();
			System.out.println("Request: "+req.getUrl());
		});
		devtools.addListener(Network.responseReceived(), response ->{
			Response res = response.getResponse();			
			if(res.getStatus().toString().startsWith("4")) {
				System.out.println(res.getUrl()+ " failed with status code "+res.getStatus());
			}			
		});
		chr.get("https://rahulshettyacademy.com/angularAppdemo");
		chr.findElement(By.cssSelector("button[routerlink*='library']")).click();

	}

}
