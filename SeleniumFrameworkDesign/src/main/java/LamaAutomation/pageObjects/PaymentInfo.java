package LamaAutomation.pageObjects;

import org.testng.AssertJUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LamaAutomation.AbstractComponent.AbstractComponent;


public class PaymentInfo extends AbstractComponent {
	WebDriver driver;
	@FindBy(tagName="b") WebElement product;
	
	public PaymentInfo(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']") WebElement inputCountry;
	@FindBy(xpath="//a[contains(text(),'Place Order')]") WebElement placeOrderBtn;
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']") List<WebElement> countryList;
	By countryListBy = By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']");
	
	public void selectCountry(String shortText, String fullCOuntryName) {
		inputCountry.sendKeys(shortText);
		waitForElementToAppear(countryListBy);
		for (WebElement n : countryList) {
			if (n.getText().equalsIgnoreCase(fullCOuntryName)) {
				System.out.println("Selected country: " + n.getText());
				n.click();
				break;
			}
		}		
	}
	
	public void selectCountry2(String shortText, String fullCOuntryName) {
		inputCountry.sendKeys(shortText);
		waitForElementToAppear(countryListBy);
		countryList.stream().filter(p->p.getText().equalsIgnoreCase(fullCOuntryName)).findFirst().orElse(null).click();
	}
	
	public SubmitSuccess placeOrder() {
		placeOrderBtn.click();
		return new SubmitSuccess(driver);
	}
	
}