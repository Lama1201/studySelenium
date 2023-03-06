package testSuite1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Module2 {
	@Test(dataProvider="getData")
	public void testcase1(String userName, int pw) {
		System.out.println(userName + " " + pw);	
	}
	
	@Test
	public void testcase2() {
		System.out.println("Test case 2 in module 2");	
	}
	
	@Test
	public void testcase3() {
		System.out.println("Test case 3 in module 2");	
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[3][2];
		data[0][0] = "hai";
		data[0][1] = 123;
		data[1][0] = "anh";
		data[1][1] = 456;
		data[2][0] = "luu";
		data[2][1] = 789;
		return data;
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
