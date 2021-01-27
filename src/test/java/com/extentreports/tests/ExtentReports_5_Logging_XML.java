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

public class ExtentReports_5_Logging_XML {

	private static final String ReportNmae = "Extent_Reports_5_Demo - Loggin XML";
	private static final String Document_Title = "Automation - Logging XML";
	private static final String REPORTS_SPARK_LOGGING_JSON_IN_REPORT_HTML = "reports/Spark_Logging_XML_In_Report.html";

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

		String xmlData = "<?xml version=\"1.0\"?>\r\n" + "<Tests xmlns=\"http://www.adatum.com\">\r\n"
				+ "  <Test TestId=\"0001\" TestType=\"CMD\">\r\n" + "    <Name>Convert number to string</Name>\r\n"
				+ "    <CommandLine>Examp1.EXE</CommandLine>\r\n" + "    <Input>1</Input>\r\n"
				+ "    <Output>One</Output>\r\n" + "  </Test>\r\n" + "  <Test TestId=\"0002\" TestType=\"CMD\">\r\n"
				+ "    <Name>Find succeeding characters</Name>\r\n" + "    <CommandLine>Examp2.EXE</CommandLine>\r\n"
				+ "    <Input>abc</Input>\r\n" + "    <Output>def</Output>\r\n" + "  </Test>\r\n"
				+ "  <Test TestId=\"0003\" TestType=\"GUI\">\r\n"
				+ "    <Name>Convert multiple numbers to strings</Name>\r\n"
				+ "    <CommandLine>Examp2.EXE /Verbose</CommandLine>\r\n" + "    <Input>123</Input>\r\n"
				+ "    <Output>One Two Three</Output>\r\n" + "  </Test>\r\n"
				+ "  <Test TestId=\"0004\" TestType=\"GUI\">\r\n" + "    <Name>Find correlated key</Name>\r\n"
				+ "    <CommandLine>Examp3.EXE</CommandLine>\r\n" + "    <Input>a1</Input>\r\n"
				+ "    <Output>b1</Output>\r\n" + "  </Test>\r\n" + "  <Test TestId=\"0005\" TestType=\"GUI\">\r\n"
				+ "    <Name>Count characters</Name>\r\n" + "    <CommandLine>FinalExamp.EXE</CommandLine>\r\n"
				+ "    <Input>This is a test</Input>\r\n" + "    <Output>14</Output>\r\n" + "  </Test>\r\n"
				+ "  <Test TestId=\"0006\" TestType=\"GUI\">\r\n" + "    <Name>Another Test</Name>\r\n"
				+ "    <CommandLine>Examp2.EXE</CommandLine>\r\n" + "    <Input>Test Input</Input>\r\n"
				+ "    <Output>10</Output>\r\n" + "  </Test>\r\n" + "</Tests>";

		// test1.info("Older way: " + xmlData);
		test1.info(MarkupHelper.createCodeBlock(xmlData, CodeLanguage.XML));
		// rtest1.info("<pre>" + jsonCode.replace("\n", "<br>") + "</pre>");
		test1.pass("Login test completed successfully");

		extent.flush();

		Desktop.getDesktop().browse(new File(REPORTS_SPARK_LOGGING_JSON_IN_REPORT_HTML).toURI());

	}

}
