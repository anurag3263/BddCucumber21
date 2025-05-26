package com.basePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

//	public static WebDriver driver;
//
//	public void setup() {
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		driver = new ChromeDriver(options);
//		// driver=new ChromeDriver();
//		driver.manage().window().maximize();
//
//	}

	private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Long> threadIdThreadLocal = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return webDriverThreadLocal.get();
	}

	public static long getCurrentThreadId() {
		return threadIdThreadLocal.get();
	}

	public static void setDriver(String browser) {
		WebDriver driver = null;
		switch (browser.toLowerCase()) {
		case "chrome":
			// Set up ChromeDriver
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			break;
		case "firefox":
			// Set up FirefoxDriver
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		// Add more cases for other browsers if needed
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		// Set the WebDriver instance to the ThreadLocal
		webDriverThreadLocal.set(driver);
		System.out.println("Current driver thread id --> " + Thread.currentThread().getId());
		threadIdThreadLocal.set(Thread.currentThread().getId());
	}

	public static void quitDriver() {
		WebDriver driver = webDriverThreadLocal.get();
		if (driver != null) {
			driver.quit();
			webDriverThreadLocal.remove();
			// Remove thread ID from ThreadLocal
			threadIdThreadLocal.remove();
		}

	}

	public void tearDown() {
		getDriver().quit();
	}

}
