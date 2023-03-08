package LamaAutomation.pageObjects;

import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LamaAutomation.AbstractComponent.AbstractComponent;


public class SubmitSuccess extends AbstractComponent {
	WebDriver driver;
	@FindBy(tagName="b") WebElement product;
	
	public SubmitSuccess(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName="h1") WebElement txtGreeting;
	By txtGreetingBy = By.tagName("h1");
	
	public void checkOrderSuccess(String successMessage) {
		waitForElementToAppear(txtGreetingBy);
		Assert.assertTrue(txtGreeting.getText().equalsIgnoreCase(successMessage));
	}
	
}