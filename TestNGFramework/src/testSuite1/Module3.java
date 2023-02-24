package testSuite1;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Module3 {
	@BeforeClass   // Method có tag này sẽ chạy trước khi bất cứ test nào trong class đó được thực thi
	public void BeforeClass() {
		System.out.println("====================================");
		System.out.println("Executing before any method in the class");
	}
	
	@AfterClass   // Method có tag này sẽ được chạy sau cùng khi tất cả các test trong class đó đã được thực thi
	public void AfterClass() {
		System.out.println("====================================");
		System.out.println("Executing after all other methods in class executed");
	}

	
	@Test(dependsOnMethods= {"testcase2","testcase3"}) // dependsOnMethods sẽ chạy những method được nhắc tới trước khi  method này
	public void testcase1() {
		System.out.println("Test case 1 in module 3");	
	}
	
	@Test
	public void testcase2() {
		System.out.println("Test case 2 in module 3");	
	}
	
	@Test
	public void testcase3() {
		System.out.println("Test case 3 in module 3");	
	}
	
	@Test(enabled =false)  //enabled = false sẽ bỏ qua method này khi chạy test case
	public void mobileLogin() {
		System.out.println("Test case mobile login");	
	}
	
	@Test
	public void mobileSignOut() {
		System.out.println("Test case mobile sign out");	
	}
	
	@Test
	public void mobilenRead() {
		System.out.println("Test case mobile read");	
	}
	
	@BeforeSuite // Method có tag này se được chạy đầu tiên trước khi run test suite
	public void ItShouldRunFirst() {
		System.out.println("I am the one");	
		System.out.println("=================================");
	}
	
	@AfterSuite // Method có tag này se được chạy sau cùng sau khi kết thúc run test suite
	public void ItShouldRunLast() {
		System.out.println("=================================");
		System.out.println("I am the Last");			
	}

}
