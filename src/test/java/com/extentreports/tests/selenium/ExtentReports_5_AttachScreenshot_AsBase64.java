package com.extentreports.tests.selenium;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentreports.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

/*http://www.extentreports.com/docs/versions/5/java/index.html
*/

public class ExtentReports_5_AttachScreenshot_AsBase64 {

	private static final String SRC_TEST_RESOURCES_CONFIG_XML = ".\\src\\test\\resources\\config.xml";
	private static final String REPORTS_SPARK_ATTACH_LOGO_USING_XML_HTML = "reports/Spark_AttachScreenshot_AsBase64.html";

	ExtentReports extent;
	WebDriver driver;

	@BeforeSuite
	public void setup() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(REPORTS_SPARK_ATTACH_LOGO_USING_XML_HTML);
		final File file = new File(SRC_TEST_RESOURCES_CONFIG_XML);
		try {
			spark.loadXMLConfig(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extent.attachReporter(spark);

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();// Important to add the Logs in report
		driver.quit();
		// This will open the File after execution
		try {
			Desktop.getDesktop().browse(new File(REPORTS_SPARK_ATTACH_LOGO_USING_XML_HTML).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void extent_attachScreenshotTest() {

		ExtentTest test1 = extent.createTest("First Test"); // Create a test node in the Report

		test1.pass("Browser opened");

		driver.get("https://www.google.com/");
		driver.manage().window().maximize();

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.name("q")).sendKeys("Full Stack Test Automation Engineer");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		try {
			test1.pass("Value Entered: ", MediaEntityBuilder
					.createScreenCaptureFromPath(Utilities.getScreenshotPath_AsBase64(driver)).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test1.pass("Login test completed successfully");

		test1.assignAuthor("Rajat").assignCategory("Smoke").assignCategory("Regression").assignDevice("Chrome")
				.assignDevice("FireFox").assignDevice("Safari");

	}

}
