package goldenindia.CustomerPanelNewFramework.Base;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends CustomerPanelBaseTest implements ITestListener {

    // This method is invoked before the test suite starts.
    public void onStart(ITestContext context) {
        System.out.println("Test Suite is starting...");
    }

    // This method is invoked after the test suite finishes.
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending...");
    }

    // This method is invoked before a test method starts.
    public void onTestStart(ITestResult result) {
        System.out.println("Test Method " + result.getMethod().getMethodName() + " is starting...");
    }

    // This method is invoked after a test method finishes successfully.
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Method " + result.getMethod().getMethodName() + " passed successfully.");
    }

    // This method is invoked after a test method fails.
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Method " + result.getMethod().getMethodName() + " failed!");
    }

    // This method is invoked after a test method is skipped.
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Method " + result.getMethod().getMethodName() + " is skipped.");
    }

    // This method is invoked after a test method is finished, regardless of the outcome.
    public void onTestFinish(ITestResult result) {
        System.out.println("Test Method " + result.getMethod().getMethodName() + " is finished.");
    }

    // This method is invoked when a test is about to start running.
    public void onStart(ITestResult result) {
        System.out.println("Test is about to start running...");
    }

    // This method is invoked when a test is about to finish running.
    public void onFinish(ITestResult result) {
        System.out.println("Test is about to finish running...");
    }
}
