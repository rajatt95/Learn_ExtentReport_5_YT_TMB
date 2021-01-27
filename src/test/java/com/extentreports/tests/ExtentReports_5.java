package com.extentreports.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*http://www.extentreports.com/docs/versions/5/java/index.html
*/

public class ExtentReports_5 {

	@Test
	public void extentTest() {

		// Extent Reports Team - Removed debug, fatal, error

		ExtentReports extent = new ExtentReports();
		// ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
		ExtentSparkReporter spark = new ExtentSparkReporter("reports/Spark.html");
		// spark.config().setTheme(Theme.DARK);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation_Status_Report");
		spark.config().setReportName("Extent_Reports_5_Demo");

		extent.attachReporter(spark);

		ExtentTest test1 = extent.createTest("Login Tests"); // Create a test node in the Report

		test1.assignAuthor("Rajat").assignCategory("Smoke").assignCategory("Regression").assignDevice("Chrome")
				.assignDevice("FireFox").assignDevice("Safari");
		test1.pass("Login test started successfully");// Create a test step node in the Report
		test1.info("URL is loaded");
		test1.info("Credentials entered");
		test1.pass("Login test completed successfully");

		ExtentTest test2 = extent.createTest("HomePage Tests"); // Create a test node in the Report
		test2.assignAuthor("Neha").assignCategory("Sanity").assignCategory("Regression").assignDevice("Chrome")
				.assignDevice("Edge");
		test2.pass("Hompage test started successfully");// Create a test step node in the Report
		test2.info("URL is loaded");
		test2.info("Credentials entered");
		test2.fail("Login test failed miserably");

		// extent.createTest("Login Tests").log(Status.PASS, "This is a logging event
		// for MyFirstTest, and it passed!");

		extent.flush();
	}

}
