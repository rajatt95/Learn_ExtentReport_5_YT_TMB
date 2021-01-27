package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.StatusFilter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*http://www.extentreports.com/docs/versions/5/java/index.html
*/

public class ExtentReports_5_GenerateMoreThanOneReport {

	private static final String REPORTS_SPARK_FAIL_HTML = "reports/Spark_Fail.html";
	private static final String REPORTS_SPARK_SKIP_HTML = "reports/Spark_Skip.html";
	private static final String REPORTS_SPARK_ALL_HTML = "reports/Spark_All.html";

	@Test
	public void extentTest() {

		// Extent Reports Team - Removed debug, fatal, error

		ExtentReports extent = new ExtentReports();

		ExtentSparkReporter spark = doConfigurationsForReport_ALL();

		ExtentSparkReporter failedSpark = doConfigurationsForReport_FAIL();

		ExtentSparkReporter skippedSpark = doConfigurationsForReport_SKIP();

		extent.attachReporter(spark, failedSpark, skippedSpark);

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

		ExtentTest test3 = extent.createTest("HomePage Tests"); // Create a test node in the Report
		test3.assignAuthor("Neha").assignCategory("Sanity").assignCategory("Regression").assignDevice("Chrome")
				.assignDevice("Edge");
		test3.pass("Hompage test started successfully");// Create a test step node in the Report
		test3.info("URL is loaded");
		test3.info("Credentials entered");
		test3.skip("Login test failed miserably");

		// extent.createTest("Login Tests").log(Status.PASS, "This is a logging event
		// for MyFirstTest, and it passed!");

		extent.flush();

		try {
			Desktop.getDesktop().browse(new File(REPORTS_SPARK_ALL_HTML).toURI());
			Desktop.getDesktop().browse(new File(REPORTS_SPARK_FAIL_HTML).toURI());
			Desktop.getDesktop().browse(new File(REPORTS_SPARK_SKIP_HTML).toURI());
			// This will open the File after execution

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ExtentSparkReporter doConfigurationsForReport_FAIL() {
		ExtentSparkReporter failedSpark = new ExtentSparkReporter(REPORTS_SPARK_FAIL_HTML).filter().statusFilter()
				.as(new Status[] { Status.FAIL }).apply();
		// Status.FAIL,Status.SKIP }).apply());

		failedSpark.config().setTheme(Theme.DARK);
		failedSpark.config().setDocumentTitle("Automation - Only failed TCs");
		failedSpark.config().setReportName("Extent_Reports_5_Demo - Only failed TCs");
		return failedSpark;
	}

	private ExtentSparkReporter doConfigurationsForReport_SKIP() {
		ExtentSparkReporter failedSpark = new ExtentSparkReporter(REPORTS_SPARK_SKIP_HTML).filter().statusFilter()
				.as(new Status[] { Status.SKIP }).apply();
		// Status.FAIL,Status.SKIP }).apply());

		failedSpark.config().setTheme(Theme.STANDARD);
		failedSpark.config().setDocumentTitle("Automation - Only skipped TCs");
		failedSpark.config().setReportName("Extent_Reports_5_Demo - Only skipped TCs");
		return failedSpark;
	}

	private ExtentSparkReporter doConfigurationsForReport_ALL() {
		ExtentSparkReporter spark = new ExtentSparkReporter(REPORTS_SPARK_ALL_HTML);
		// spark.config().setTheme(Theme.DARK);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation - All");
		spark.config().setReportName("Extent_Reports_5_Demo");
		return spark;
	}

}
