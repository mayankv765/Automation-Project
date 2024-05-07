package EcomSite.Resorces;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class EcomExtentReports {

	public static ExtentReports getExtentReportObject() {
	String filePath = System.getProperty("user.dir")+"//reports// .png";
	ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
	reporter.config().setDocumentTitle("Test Report");
	reporter.config().setReportName("Ecom Test Report");
	
	ExtentReports extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "mayank");
	return extent;
	
	}
	

}
