package LamaAutomation.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import LamaAutomation.TestComponents.BaseTest;
import LamaAutomation.pageObjects.MyCart;
import LamaAutomation.pageObjects.OrderPage;
import LamaAutomation.pageObjects.SubmitSuccess;
import LamaAutomation.pageObjects.PaymentInfo;
import LamaAutomation.pageObjects.ProductCatalogue;

public class OrderProducts extends BaseTest{
		
	@Test(dataProvider="getData", groups="Purchase")
	public void buyProducts(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.Login(input.get("email"), input.get("pw"));		
		productCatalogue.addProductToCart(input.get("productName"));			
		MyCart myCart = productCatalogue.openCart();
		Assert.assertTrue(myCart.checkAddedProduct(input.get("productName")));		
		PaymentInfo pay = myCart.checkout();
		pay.selectCountry2("vi", "Vietnam");			
		SubmitSuccess complete = pay.placeOrder();
		complete.checkOrderSuccess();
	}
	
	@Test(dependsOnMethods= {"buyProducts"},dataProvider="getData",groups="Purchase")
	public void checkOrder(HashMap<String,String> input) throws InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.Login(input.get("email"), input.get("pw"));		
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Thread.sleep(5000);
		Assert.assertTrue(orderPage.verifyOrderedProduct(input.get("productName")));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//LamaAutomation//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"faker@email.com","Password1@","adidas original"},{"faker@email.com","Password1@","zara coat 3"}};
//	}
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "faker@email.com");
//		map.put("pw", "Password1@");
//		map.put("productName", "adidas original");
//	
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "faker@email.com");
//		map1.put("pw", "Password1@");
//		map1.put("productName", "zara coat 3");
//	
//		return new Object[][] {{map},{map1}};
//	}
}
