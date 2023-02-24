package testSuite1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Module2 {
	@Test
	public void testcase1() {
		System.out.println("Test case 1 in module 2");	
	}
	
	@Test
	public void testcase2() {
		System.out.println("Test case 2 in module 2");	
	}
	
	@Test
	public void testcase3() {
		System.out.println("Test case 3 in module 2");	
	}

	@BeforeMethod // Method với tag này sẽ chạy lại trước mỗi test
	public void runBeforeEveryTest() {
		System.out.println("===========================================");
		System.out.println("This will run before every test in module 2");	
	}
	
	@AfterMethod // Method với tag này sẽ chạy lại sau mỗi test
	public void runAfterEveryTest() {
		System.out.println("This will run after every test in module 2");	
		System.out.println("===========================================");
	}
}
