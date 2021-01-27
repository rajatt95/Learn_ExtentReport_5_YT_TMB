package com.extentreports.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.reporter.configuration.util.IOUtil;

public class Utilities {

	public static String getScreenshotPath_AsFile(WebDriver driver) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		String screenshotName = date.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		String path = Constants.reportingPath_AsFile + screenshotName;
		FileUtils.copyFile(srcFile, new File(path));
		return path;
	}

	public static String getScreenshotPath_AsBase64(WebDriver driver) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		String screenshotName = date.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		String path = Constants.reportingPath_AsBase64 + screenshotName;
		FileUtils.copyFile(srcFile, new File(path));
		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
		return Base64.getEncoder().encodeToString(imageBytes);

	}

	
	public static String getBase64(WebDriver driver) throws IOException {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			}
}
