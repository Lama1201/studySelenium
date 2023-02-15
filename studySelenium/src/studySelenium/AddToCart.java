package studySelenium;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AddToCart {

	public static void main(String[] args) throws InterruptedException {
		WebDriver chr = new ChromeDriver();
		chr.get("https://rahulshettyacademy.com/seleniumPractise/");
		chr.manage().window().maximize();
		String[] vegetable = { "Brocolli", "Cauliflower", "Cucumber", "Beetroot", "Carrot", "Tomato", "Beans",
				"Brinjal", "Capsicum", "Apple", "Grapes", "Mango", "Strawberry", "Walnuts", "Pista" };
		String[] vegetable2 = {"Apple","Mango","Walnuts",};

		AddProduct2(chr, vegetable[(int) (Math.random() * vegetable.length)]); 
		AddProduct1(chr, vegetable[(int) (Math.random() * vegetable.length)]);  
		AddProduct3(chr, vegetable[(int) (Math.random() * vegetable.length)]);
		AddProduct3(chr, vegetable[(int) (Math.random() * vegetable.length)]); 
		//AddProduct4(chr,vegetable2); 

		chr.findElement(By.className("cart-icon")).click();
		Thread.sleep(6000);
		chr.quit();

	}
	// Sử dụng vòng for thông thường để quét list product
	public static void AddProduct1(WebDriver chr, String product) throws InterruptedException {
		chr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> products = chr.findElements(By.className("product-name"));
		System.out.println("Selected product: " + product);

		for (int i = 0; i < products.size(); i++) {			
			if (products.get(i).getText().contains(product)) {
				Actions actions = new Actions(chr);
				actions.moveToElement(products.get(i).findElement(By.xpath("./following-sibling::div/button[text()='ADD TO CART']"))).perform();
				products.get(i).findElement(By.xpath("./following-sibling::div/button[text()='ADD TO CART']")).click();
				//chr.findElements(By.className("product-action")).get(i).click();
				break;
			}
		}		
		
	}
	// Sử dụng xpath gọi trực tiếp
	public static void AddProduct2(WebDriver chr, String product) {
		System.out.println("Selected product: " + product);
		chr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		chr.findElement(By.xpath("//h4[contains(text(),'" + product + "')]/following-sibling::div[2]")).click();
	}
	// Sử dụng vòng for cho mảng để quét list product
	public static void AddProduct3(WebDriver chr, String product) throws InterruptedException {
		chr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> products = chr.findElements(By.className("product-name"));
		System.out.println("Selected product: " + product);
		for (WebElement n : products) {
			if (n.getText().contains(product)) {				
				Actions action = new Actions(chr);
				action.moveToElement(n).perform();				
				n.findElement(By.xpath("./following-sibling::div/button[text()='ADD TO CART']")).click();
				break;
			}
		}
	}
	// Chuyển mảng được nhập thành List để quét với danh sách product
	public static void AddProduct4(WebDriver driver, String[] vegetable)
	{
		int j = 0;
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));// Lấy list tất cả các product
		for (int i = 0; i < products.size(); i++)
		{
			String name = products.get(i).getText().split("-")[0].trim(); // Loại bỏ text thừa và chỉ lấy đúng tên product trong mảng
			System.out.println("Name of product: "+ name);
			List<String> itemsNeededList = Arrays.asList(vegetable); // chuyển mảng được truyền thành dạng list
			if (itemsNeededList.contains(name)) // Tìm product có tồn tại trong list
			{				
				System.out.println("Matched: "+ itemsNeededList.contains(name));
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i)).perform();
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				j++;
				if (j == vegetable.length)
				{
					break;
				}
			}
		}
	}
	// Sử dung vòng lặp forEach
	public static void AddProduct5(WebDriver chr, String product) throws InterruptedException {
	    chr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    List<WebElement> products = chr.findElements(By.className("product-name"));
	    System.out.println("Selected product: " + product);

	    products.forEach((WebElement element) -> {
	        if (element.getText().contains(product)) {
	            Actions actions = new Actions(chr);
	            actions.moveToElement(element.findElement(By.xpath("./following-sibling::div/button[text()='ADD TO CART']"))).perform();
	            element.findElement(By.xpath("./following-sibling::div/button[text()='ADD TO CART']")).click();
	        }
	    });
	}
}
