package com.basePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverManager {

	private static volatile WebDriverManager instance;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	private WebDriverManager() {
		// constructor of singleton class pattern
	}

	private void initDriver(String browser) {
		switch (browser) {
		case "chrome":
			tlDriver.set(new ChromeDriver());
			break;

		case "firefox":
			tlDriver.set(new FirefoxDriver());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser type : " + browser);
		}
	}

	public static WebDriverManager getInstance(String browser) {
		if (instance == null) {
			synchronized (WebDriverManager.class) {
				if (instance == null) {
					instance = new WebDriverManager();
				}
			}
		}
		if (tlDriver.get() == null) {
			instance.initDriver(browser);
		}
		return instance;
	}

	public WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void quiteBrowser() {
		if (tlDriver.get() != null) {
			tlDriver.get().close();;
			tlDriver.remove();
		}
	}

}
