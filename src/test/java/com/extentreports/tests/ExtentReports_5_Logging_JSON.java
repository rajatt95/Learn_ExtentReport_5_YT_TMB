package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*http://www.extentreports.com/docs/versions/5/java/index.html
*/

public class ExtentReports_5_Logging_JSON {

	private static final String ReportNmae = "Extent_Reports_5_Demo - Loggin JSON";
	private static final String Document_Title = "Automation - Logging JSON";
	private static final String REPORTS_SPARK_LOGGING_JSON_IN_REPORT_HTML = "reports/Spark_Logging_JSON_In_Report.html";

	@Test
	public void extentTest() throws IOException {

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(REPORTS_SPARK_LOGGING_JSON_IN_REPORT_HTML);

		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle(Document_Title);
		spark.config().setReportName(ReportNmae);
		extent.attachReporter(spark);

		ExtentTest test1 = extent.createTest("Login Tests"); // Create a test node in the Report

		test1.assignAuthor("Rajat").assignCategory("Smoke").assignCategory("Regression").assignDevice("Chrome")
				.assignDevice("FireFox").assignDevice("Safari");
		test1.pass("Login test started successfully");// Create a test step node in the Report
		test1.info("URL is loaded");
		test1.info("Credentials entered");

		String jsonCode = "[{\r\n" + "  \"id\": 1,\r\n" + "  \"first_name\": \"Jeanette\",\r\n"
				+ "  \"last_name\": \"Penddreth\",\r\n" + "  \"email\": \"jpenddreth0@census.gov\",\r\n"
				+ "  \"gender\": \"Female\",\r\n" + "  \"ip_address\": \"26.58.193.2\"\r\n" + "}, {\r\n"
				+ "  \"id\": 2,\r\n" + "  \"first_name\": \"Giavani\",\r\n" + "  \"last_name\": \"Frediani\",\r\n"
				+ "  \"email\": \"gfrediani1@senate.gov\",\r\n" + "  \"gender\": \"Male\",\r\n"
				+ "  \"ip_address\": \"229.179.4.212\"\r\n" + "}, {\r\n" + "  \"id\": 3,\r\n"
				+ "  \"first_name\": \"Noell\",\r\n" + "  \"last_name\": \"Bea\",\r\n"
				+ "  \"email\": \"nbea2@imageshack.us\",\r\n" + "  \"gender\": \"Female\",\r\n"
				+ "  \"ip_address\": \"180.66.162.255\"\r\n" + "}, {\r\n" + "  \"id\": 4,\r\n"
				+ "  \"first_name\": \"Willard\",\r\n" + "  \"last_name\": \"Valek\",\r\n"
				+ "  \"email\": \"wvalek3@vk.com\",\r\n" + "  \"gender\": \"Male\",\r\n"
				+ "  \"ip_address\": \"67.76.188.26\"\r\n" + "}]";

		test1.info("Older way: " + jsonCode);
		test1.info(MarkupHelper.createCodeBlock(jsonCode, CodeLanguage.JSON));
		// rtest1.info("<pre>" + jsonCode.replace("\n", "<br>") + "</pre>");
		test1.pass("Login test completed successfully");

		extent.flush();
		Desktop.getDesktop().browse(new File(REPORTS_SPARK_LOGGING_JSON_IN_REPORT_HTML).toURI());
	}

}
