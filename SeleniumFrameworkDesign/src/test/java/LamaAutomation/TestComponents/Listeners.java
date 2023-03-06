package LamaAutomation.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import LamaAutomation.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); 
	
	@Override
	public void onTestStart(ITestResult result) {	
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // assign unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		test.log(Status.PASS, "Test Passed"); // run test single thread
		extentTest.get().log(Status.PASS, "Test Passed"); // run test parallel
	}

	@Override
	public void onTestFailure(ITestResult result) {
//		test.fail(result.getThrowable());  // run test single thread
		extentTest.get().fail(result.getThrowable()); // run test parallel
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filePath = null;
			try {
				filePath = takeScreenshot(result.getMethod().getMethodName(), driver);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
