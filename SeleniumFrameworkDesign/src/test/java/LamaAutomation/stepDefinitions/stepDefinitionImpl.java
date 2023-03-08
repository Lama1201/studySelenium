package LamaAutomation.stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import LamaAutomation.TestComponents.BaseTest;
import LamaAutomation.pageObjects.LandingPage;
import LamaAutomation.pageObjects.MyCart;
import LamaAutomation.pageObjects.PaymentInfo;
import LamaAutomation.pageObjects.ProductCatalogue;
import LamaAutomation.pageObjects.SubmitSuccess;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public SubmitSuccess complete;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage=launchBrowser();	
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String userName, String password) {
		productCatalogue= landingPage.Login(userName,password);	
	}
	
	@When ("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) {
		productCatalogue.addProductToCart(productName);	
	}
	
	@When ("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		MyCart myCart = productCatalogue.openCart();
		Assert.assertTrue(myCart.checkAddedProduct(productName));		
		PaymentInfo pay = myCart.checkout();
		pay.selectCountry2("vi", "Vietnam");			
		complete = pay.placeOrder();	
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String message) {
		complete.checkOrderSuccess(message);
		driver.close();
	}
	
	@Then ("{string} message is displayed")
	public void message_is_displayed(String message) {
		Assert.assertTrue(landingPage.getErrorMessage().equalsIgnoreCase(message));
		driver.close();
	}


}
