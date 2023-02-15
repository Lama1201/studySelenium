package studySelenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class RahulAcademy {

	public static void main(String[] args) throws InterruptedException {
		WebDriver chr1 = new ChromeDriver();		
		chr1.manage().window().maximize(); // Phóng to cửa sổ
		
		String name = "Lama_in_da_house";
				
		chr1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //Đợi 5s sau mỗi cho mỗi element nếu nó chưa xuất hiện
		chr1.get("https://rahulshettyacademy.com/locatorspractice/");
		chr1.findElement(By.id("inputUsername")).sendKeys(name);
		chr1.findElement(By.name("inputPassword")).sendKeys("randomPW");
		chr1.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println(chr1.findElement(By.cssSelector("p.error")).getText());
		chr1.findElement(By.linkText("Forgot your password?")).click();		
		
		Thread.sleep(1000); // Đợi cho animation kết thúc và các element vào trạng thái ổn định
		chr1.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(name);
		chr1.findElement(By.xpath("//input[@type='text'][2]")).sendKeys("Lama@gmail.com"); // Sử dụng index trong  trường hợp ko có attribute mang tính duy nhất
		chr1.findElement(By.xpath("//form/input[@placeholder='Phone Number']")).sendKeys("023964254"); // Sử dụng parent/child xpath
		chr1.findElement(By.cssSelector(".reset-pwd-btn")).click();
		System.out.println(chr1.findElement(By.cssSelector(".infoMsg")).getText());
		
		String pw = getPassword.getText(chr1.findElement(By.cssSelector(".infoMsg")).getText()); // Lấy password từ trong thông báo
		
		chr1.findElement(By.cssSelector("button.go-to-login-btn")).click();
		//chr1.findElement(By.xpath("//div/button[@class='go-to-login-btn']")).click();
		
		Thread.sleep(1000);
		chr1.findElement(By.id("inputUsername")).sendKeys(name);		
		chr1.findElement(By.cssSelector("input[type*='pass']")).sendKeys(pw); // Sử dụng partial text bằng cách dùng dấu * đối với cssSelector
		chr1.findElement(By.xpath("//input[@value='rmbrUsername']")).click();		
		chr1.findElement(By.xpath("//button[contains(@class,'submit')]")).click(); // Sử dụng partial text đối với xpath
		Thread.sleep(2000);
		System.out.println(chr1.findElement(By.cssSelector("p")).getText());
		// Dùng Assertion để kiểm tra đoạn text xuất hiện đúng như mong đợi
		Assert.assertEquals(chr1.findElement(By.cssSelector("p")).getText(), "You are successfully logged in.");
		Assert.assertEquals(chr1.findElement(By.xpath("//div[contains(@class,'login-container')]/h2")).getText(), "Hello "+name+",");
		chr1.findElement(By.className("logout-btn")).click();		
		
		//chr1.findElement(By.xpath("//button[text()='Log Out']")).click(); // Sử dụng button text trong xpath
				
		//chr1.findElement(By.xpath("//*[text()='Log Out']")).click(); // Sử dung text cho element bất kì
		
		Thread.sleep(1000);
		chr1.quit();
		
	}

}
