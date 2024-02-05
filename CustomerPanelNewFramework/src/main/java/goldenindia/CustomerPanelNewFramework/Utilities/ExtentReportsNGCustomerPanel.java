package goldenindia.CustomerPanelNewFramework.Utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNGCustomerPanel {

	public static ExtentReports reportGenerating() {
		File file = new File(System.getProperty("user.dir") + "//Reports");
		ExtentSparkReporter extent = new ExtentSparkReporter(file);
		extent.config().setDocumentTitle("");
		extent.config().setReportName("");

		ExtentReports report = new ExtentReports();
		report.attachReporter(extent);
		return report;

	}
}
