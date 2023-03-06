package LamaAutomation.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LamaAutomation.AbstractComponent.AbstractComponent;


public class OrderPage extends AbstractComponent {
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table[contains(@class,'table')]//*[@class='ng-star-inserted']") List<WebElement> products;
	//@FindBy(xpath="//button[text()='Checkout']") WebElement checkoutBtn;
	//By productNameBy = By.xpath("//div[@class='infoWrap']//h3");


	public boolean verifyOrderedProduct(String productName) {
		
		return products.stream().anyMatch(p->p.findElement(By.xpath(".//td[2]")).getText().equalsIgnoreCase(productName));		
	}
	
	
}