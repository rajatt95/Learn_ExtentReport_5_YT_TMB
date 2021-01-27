package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

/*http://www.extentreports.com/docs/versions/5/java/index.html
*/

public class ExtentReports_5_Customised_And_Map {

	private static final String REPORTS_SPARK_CUSTOMISED_HTML = "reports/Spark_Customised_Map.html";

	/**
	 * 1. Viewing Order 2. Remove some menu 3. Highlight the logs 4. Log -> List of
	 * String 5. Log -> Map<String, String>
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void extentTest() throws IOException {

		// Extent Reports Team - Removed debug, fatal, error

		ExtentReports extent = new ExtentReports();
		// ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(REPORTS_SPARK_CUSTOMISED_HTML).viewConfigurer().viewOrder()
				.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY }).apply();
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

		Map<String, String> profile = new HashMap();
		profile.put("Name", "Rajat Verma");
		profile.put("Position", "Senior Software Engineer");
		profile.put("Location", "Hyderabad, India");

		profile.forEach((k, v) -> test1.pass(k + " : " + v));

		test1.pass(MarkupHelper.createOrderedList(profile).getMarkup());
		test1.pass(MarkupHelper.createUnorderedList(profile).getMarkup());

		// test1.pass("Login test completed successfully");
		test1.pass(MarkupHelper.createLabel("Login test completed successfully", ExtentColor.GREEN));

		ExtentTest test2 = extent.createTest("HomePage Tests"); // Create a test node in the Report
		test2.assignAuthor("Neha").assignCategory("Sanity").assignCategory("Regression").assignDevice("Chrome")
				.assignDevice("Edge");
		test2.pass("Hompage test started successfully");// Create a test step node in the Report
		test2.info("URL is loaded");
		test2.info("Credentials entered");
		// test2.fail("Login test failed miserably");

		test2.pass(MarkupHelper.createLabel("Login test failed miserably", ExtentColor.RED));
		// extent.createTest("Login Tests").log(Status.PASS, "This is a logging event
		// for MyFirstTest, and it passed!");

		extent.flush();
		Desktop.getDesktop().browse(new File(REPORTS_SPARK_CUSTOMISED_HTML).toURI());

	}

}
