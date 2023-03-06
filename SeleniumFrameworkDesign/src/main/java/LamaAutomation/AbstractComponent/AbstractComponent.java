package LamaAutomation.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LamaAutomation.pageObjects.MyCart;
import LamaAutomation.pageObjects.OrderPage;



public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']") 
	WebElement btnCart;
	@FindBy(xpath="//button[contains(text(),'ORDERS')]") 
	WebElement btnOrder;
	
	public MyCart goToCart() {
		btnCart.click();
		MyCart myCart = new MyCart(driver);
		return myCart;
	}
	
	public OrderPage goToOrderPage() {
		btnOrder.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(findBy));
	}
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
		
		//WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
		//w.until(ExpectedConditions.invisibilityOf(element));
		
		//Lấy tên browser 
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = cap.getBrowserName().toLowerCase();
	    int i = browserName.equalsIgnoreCase("firefox") ? 2000 : 1000;
	    Thread.sleep(i);
	}

}
