package studySelenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Section14 {/*
	@Test
	public void Test1() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Hai");
		names.add("Bao");
		names.add("Hanh");
		names.add("Tuan");
		names.add("An");
		names.add("Nguyen");
		names.add("Binh");
		// Sử dụng phương thức 'collect' để gán giá trị vào chuỗi mới
		List<String> nameHa = names.stream().filter(n->n.startsWith("Ha")).collect(Collectors.toList());	
		System.out.println(nameHa);
	}
	
	@Test
	public void Test2() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Hai");
		names.add("Bao");
		names.add("Hanh");
		names.add("Tuan");
		names.add("An");
		names.add("Nguyen");
		names.add("Binh");
		//Sử dụng phương thức 'forEach' để gán giá trị
		List<String> nameLongerThan3 = new ArrayList<String>();
		names.stream().filter(n->n.length()>3).forEach(nameLongerThan3::add);	
		//names.stream().filter(n->n.length()>3).forEach(n -> nameLongerThan3.add(n));
		System.out.println(nameLongerThan3);
	}
	
	@Test
	public void Test3() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Hai");
		names.add("Bao");
		names.add("Hanh");
		names.add("Tuan");
		names.add("An");
		names.add("Nguyen");
		names.add("Binh");
		// Sử dụng phương thức 'toArray' để gán
		String[] nameShorterThan4 = names.stream().filter(n -> n.length() < 4).toArray(String[]::new);
		List<String> listnameShorterThan4 = Arrays.asList(nameShorterThan4);
		System.out.println(listnameShorterThan4);
	}
	
	@Test
	public void Test4() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Hai");
		names.add("Bao");
		names.add("Hanh");
		names.add("Tuan");
		names.add("An");
		names.add("Nguyen");
		names.add("Binh");
		//Sử dụng Map và sort
		names.stream().filter(s->s.endsWith("h")).sorted().map(s -> s.toUpperCase()).forEach(System.out::println);
		//names.stream().filter(s->s.endsWith("h")).map(s -> s.toUpperCase()).forEach(s ->System.out.println(s));
	}
	
	@Test
	public void Test5() {
		// Loại bỏ những số trùng lặp, sắp xếp lại và chỉ lấy số lẻ
		List<Integer> number = Arrays.asList(3,4,6,5,7,1,2,3,5,9,8,0);
		List<Integer> num =number.stream().distinct().sorted().filter(n->n%2==1).toList();
		System.out.println(num);		
	}
*/
	@Test
	public void Test6() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); 
		WebDriver chr = new ChromeDriver(options);
		chr.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		verifySortedProducts(chr);	
		
		// Tìm giá của rice
		System.out.println("Price of Apple is " +getPriceInAllPages(chr,"Rice"));
		//System.out.println("Price of Apple is " +getPriceInAllPages1(chr,"Rice"));
		verifySearch(chr,"che");
		verifySearchByStream(chr,"che");
		chr.quit();
	}
	public void verifySortedProducts(WebDriver chr){
		chr.findElement(By.xpath("//tr//th[1]")).click();
		List<WebElement> products = chr.findElements(By.xpath("//tr//td[1]"));	
		List<String> prdNames = products.stream().map(p->p.getText()).toList();
		List<String> sorted = products.stream().map(n->n.getText()).sorted().toList();
		Assert.assertEquals(prdNames, sorted);			
	}
	
	public String getPriceInAllPages(WebDriver chr,String product) {
		boolean hasProduct = false;
		do {
			List<WebElement> products = chr.findElements(By.xpath("//tr//td[1]"));			
			for (WebElement p : products) {
				if(p.getText().contentEquals(product)) {
					hasProduct = true;
					break;
				}			
			}
			if(hasProduct == false) {
				
				chr.findElement(By.xpath("//a[@aria-label='Next']")).click();
			}			
		}
		while(hasProduct == false);		
		return chr.findElement(By.xpath("//tr//td[1][contains(text(),'"+product+"')]/following-sibling::td[1]")).getText();
	}
	
	public String getPriceInAllPages1(WebDriver chr,String product) {
		String price = null;
		do {
			List<WebElement> products = chr.findElements(By.xpath("//tr//td[1]"));	
			for (WebElement p : products) {
				if(p.getText().contentEquals(product)) {				
					price = p.findElement(By.xpath("./following-sibling::td[1]")).getText();
					break;
				}			
			}
			if(price == null) {
				chr.findElement(By.xpath("//a[@aria-label='Next']")).click();
			}			
		}
		while(price==null);
		return price;
	}
	
	public void verifySearch(WebDriver chr, String keyword) {
		chr.findElement(By.id("search-field")).sendKeys(keyword);
		List<WebElement> result = chr.findElements(By.xpath("//tr//td[1]"));
		if(result.size()>0) {
			for(WebElement n: result) {
				Assert.assertTrue(n.getText().toLowerCase().contains(keyword.toLowerCase()));			
			}
		}	
		chr.findElement(By.id("search-field")).clear();
	}
	
	public void verifySearchByStream(WebDriver chr, String keyword) {
		chr.findElement(By.id("search-field")).sendKeys(keyword);
		List<WebElement> result = chr.findElements(By.xpath("//tr//td[1]"));
		if(result.size()>0) {
			List<WebElement> verify = result.stream().filter(n->n.getText().toLowerCase().contains(keyword.toLowerCase())).toList();
			Assert.assertEquals(result.size(), verify.size());
		}
		chr.findElement(By.id("search-field")).sendKeys(keyword);
	}
	
}
