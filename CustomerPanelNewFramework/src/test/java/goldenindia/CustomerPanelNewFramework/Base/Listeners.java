package goldenindia.CustomerPanelNewFramework.Base;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import goldenindia.CustomerPanelNewFramework.Utilities.ExtentReportsNGCustomerPanel;

public class Listeners extends CustomerPanelBaseTest implements ITestListener {


	ExtentTest test;
	ExtentReports extent = ExtentReportsNGCustomerPanel.reportGenerating();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();// It is taking the individual Thread for each
	// test

	@Override
	public void onTestStart(ITestResult result) {
		// Delete previous failure screenshots
		deletePreviousFailureScreenshots(result.getMethod().getMethodName());
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);// We are setting variable into the extentTest
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed: " + result.getName());
		test.log(Status.PASS, "Test Passed");
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = takingPageScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed: " + result.getName());
		test.fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = takingPageScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Not implemented in this example
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test suite started: " + context.getName());

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test suite finished: " + context.getName());
		extent.flush();

	}
}
