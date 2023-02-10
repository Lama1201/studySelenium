package studySelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;

public class SelIntroduction {

	public static void main(String[] args) {
		
		//System.setProperty("webDriver.chrome.driver", "E:\SturdyCode\Selenium\chromedriver.exe");
		
		// Mở trình duyệt Edge sau đó đóng lại
		//WebDriver edg1 = new EdgeDriver();
		//edg1.get("https://vnexpress.net/");
		//edg1.quit();
		
		// Mở trình duyệt chrome
		WebDriver chr1 = new ChromeDriver();
		//ChromeDriver chr2 = new ChromeDriver();
		chr1.get("https://ekb-staging.arches-global.com/admin");
		//chr2.get("https://facebook.com");
		
		//in title và url của trang web
		System.out.println(chr1.getTitle());
		System.out.println(chr1.getCurrentUrl());
		
		// Đóng tab được mở bởi đoạn code trước đó
		chr1.close();
		// Đóng tất cả các tab
		// chr1.quit();

	}

}