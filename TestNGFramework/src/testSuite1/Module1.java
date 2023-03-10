package testSuite1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Module1 {
	@AfterTest   // Method có tag này sẽ được chạy trước khi chạy các test case
	public void ExecuteLatest() {
		System.out.println("====================================");
		System.out.println("It should run later");
	}
	
	@Parameters({"url","email","pw"})
	@Test
	public void openWebsite(String url, String email, String pw) throws InterruptedException {
		WebDriver chr = new ChromeDriver();	
		chr.get(url);
		chr.findElement(By.name("email")).sendKeys(email);
		chr.findElement(By.name("password")).sendKeys(pw);
		chr.findElement(By.tagName("button")).click();
		Thread.sleep(2000);
		chr.close();
	}
	
	@Test(dependsOnMethods= {"testcase3"}) // dependsOnMethods sẽ chạy method testcase3 trước khhi chạy testcase1
	public void testcase1() {
		System.out.println("Test case 1 in module 1");		
	}
	
	@Test(timeOut=40000) // đợi 40s nếu testcase fail, tương tự như explicit wait
	public void testcase2() {
		System.out.println("Test case 2 in module 1");	
	}
	
	@Test
	public void testcase3() {
		System.out.println("Test case 3 in module 1");	
		Assert.assertTrue(false);
	}
	
	@BeforeTest   // Method có tag này sẽ được chạy sau khi chạy các test case
	public void doSomeThingFirst() {
		System.out.println("It should run before any test");
		System.out.println("====================================");
	}

}
