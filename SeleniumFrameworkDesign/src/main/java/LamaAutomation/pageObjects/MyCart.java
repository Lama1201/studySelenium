package LamaAutomation.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LamaAutomation.AbstractComponent.AbstractComponent;


public class MyCart extends AbstractComponent {
	WebDriver driver;
	@FindBy(tagName="b") WebElement product;
	
	public MyCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='infoWrap']//h3") List<WebElement> products;
	@FindBy(xpath="//button[text()='Checkout']") WebElement checkoutBtn;
	By productNameBy = By.xpath("//div[@class='infoWrap']//h3");


	public boolean checkAddedProduct(String productName) {
		waitForElementToAppear(productNameBy);		
		return products.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		
	}
	
	public PaymentInfo checkout() {
		checkoutBtn.click();
		return new PaymentInfo(driver);
	}
}