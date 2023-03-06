package LamaAutomation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LamaAutomation.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent  {
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail") WebElement inputEmail;
	@FindBy(id="userPassword") WebElement inputPw;
	@FindBy(id="login") WebElement submit;
	@FindBy(xpath="//div[@role='alertdialog']") WebElement errorMessage;
	By errorMessageBy = By.xpath("//div[@role='alertdialog']");
	
	public ProductCatalogue Login(String email, String pw) {
		inputEmail.sendKeys(email);
		inputPw.sendKeys(pw);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessageBy);
		return errorMessage.getText();
	}
}
