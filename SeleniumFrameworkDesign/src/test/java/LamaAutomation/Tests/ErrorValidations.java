package LamaAutomation.Tests;

import java.io.IOException;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import LamaAutomation.TestComponents.BaseTest;
import LamaAutomation.pageObjects.MyCart;
import LamaAutomation.pageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{
	public String productName;
	
//	@Test(priority =1, groups="ErrorValidatation", retryAnalyzer=LamaAutomation.TestComponents.Retry.class)
	@Test(priority =1, groups="ErrorValidatation")
	public void validationLogin() throws IOException {			
		landingPage.Login("faker@email.com", "Password1@11");
		Assert.assertTrue(landingPage.getErrorMessage().equalsIgnoreCase("Incorrect email or password."));
	}
	
	@Test(priority =2,  groups="ErrorValidatation")
	public void validationProductNotExist() throws IOException, InterruptedException {		
		String productName ="zara coat 3";
		
		ProductCatalogue productCatalogue = landingPage.Login("faker@email.com", "Password1@");		
		productCatalogue.addProductToCart(productName);			
		MyCart myCart = productCatalogue.openCart();
		Assert.assertFalse(myCart.checkAddedProduct("Any product"));
	}
	
}
