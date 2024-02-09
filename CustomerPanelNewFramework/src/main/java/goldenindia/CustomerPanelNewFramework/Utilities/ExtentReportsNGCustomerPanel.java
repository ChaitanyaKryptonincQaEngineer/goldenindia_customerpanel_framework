package goldenindia.CustomerPanelNewFramework.Utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNGCustomerPanel {

	public static ExtentReports reportGenerating() {
		File file = new File(System.getProperty("user.dir") + "//Reports");
		ExtentSparkReporter report = new ExtentSparkReporter(file);
		report.config().setDocumentTitle("Customer Panel Report");
		report.config().setReportName("Bug Report");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Developer", "Krunal Prajapthi");
		extent.setSystemInfo("B.A", "Birva Shukla");
		extent.setSystemInfo("Manager", "Abhishek Shah");
		return extent;

	}
}
