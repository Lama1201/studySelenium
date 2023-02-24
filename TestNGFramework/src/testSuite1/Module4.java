package testSuite1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Module4 {
	@Test(groups="smoke")   
	public void test1() {
		System.out.println("test 1 group smoke");
	}
	
	@Test(groups="smoke") 
	public void test2() {
		System.out.println("test 2 group smoke");
	}
	
	@Test(groups="body")   
	public void test3() {
		System.out.println("test 3 group body");
	}
	
	@Test(groups="body")   
	public void test4() {
		System.out.println("test 4 group body");
	}
	
	@Test(groups="body")   
	public void test5() {
		System.out.println("test 5 group body");
	}
	
	@Test(groups="header")   
	public void test6() {
		System.out.println("test 6 group header");
	}
	
	@Test(groups="header")   
	public void test7() {
		System.out.println("test 7 group header");
	}


}
