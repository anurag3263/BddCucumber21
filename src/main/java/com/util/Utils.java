package com.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.basePackage.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

public class Utils extends BaseClass {
//	private static Scenario scenario;
//	@Before
//    public void before(Scenario scenario) {
//        this.scenario = scenario;
//    }

	public static String GetCurrentDate() {
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMMM-dd-HH-mm-ss");
		String date = formatter.format(d);
		System.out.println(date);
		return date;

	}

	public static String takeScreenshot(String path, WebDriver webDriver) {
		String screenshotTaken = null;
		try {

			File srFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srFile, new File(path));
			screenshotTaken = path;
		} catch (Exception e) {
			System.out.println("Exception caught while taking screnshot " + e);
		}

		return screenshotTaken;

	}

	@After
	public void validate(Scenario scenario) {
		if (scenario.isFailed()) {
			// Take a screenshot...
			final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
			// embed it in the report.
			scenario.attach(screenshot, "image/png", scenario.getName() + "_" + GetCurrentDate());
			getDriver().quit();
		}
		if (scenario.getStatus() == Status.PASSED) {
			// Take a screenshot...
			final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
			// embed it in the report.
			scenario.attach(screenshot, "image/png", scenario.getName());
			getDriver().quit();
		}
		

	}
	@Before
	public static void attach(Scenario scenario) {
		
		String pathString=null;
		pathString="target/extentReports/screenshot/"+scenario.getName()+GetCurrentDate()+".png";
		if(pathString!=null) {
		takeScreenshot(pathString, getDriver());
		try {
			MediaEntityBuilder.createScreenCaptureFromPath(pathString).build();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
	}

}
