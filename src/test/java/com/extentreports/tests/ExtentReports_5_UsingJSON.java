package com.extentreports.tests;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*http://www.extentreports.com/docs/versions/5/java/index.html
*/

public class ExtentReports_5_UsingJSON {

	@Test
	public void extentTest() throws IOException {

		// Extent Reports Team - Removed debug, fatal, error

		ExtentReports extent = new ExtentReports();
		// ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
		ExtentSparkReporter spark = new ExtentSparkReporter("reports/Spark_Using_JSON.html");

		final File file = new File(".\\src\\test\\resources\\config.json");
		spark.loadJSONConfig(file);

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
