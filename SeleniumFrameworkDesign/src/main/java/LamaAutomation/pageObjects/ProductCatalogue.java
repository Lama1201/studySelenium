package LamaAutomation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LamaAutomation.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	@FindBy(tagName="b") WebElement product;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'mb-3')]") List<WebElement> products;
	@FindBy(css =".ng-animating") WebElement animation;
	By productsBy = By.xpath("//div[contains(@class,'mb-3')]");
	By buttonAdd = By.xpath(".//button[2]");
	By toastmess = By.id("toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);		
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream().filter(p->p.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String productName){	
		for(WebElement p: getProductList()) {
			if(p.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)) {
				p.findElement(buttonAdd).click();
			}
		}
	}
	
	public MyCart openCart() throws InterruptedException {
		waitForElementToAppear(toastmess);
		waitForElementToDisappear(animation);
		goToCart() ;
		return new MyCart(driver);
	}
}