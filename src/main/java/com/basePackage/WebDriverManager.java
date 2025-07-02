package com.basePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverManager {

    private static final WebDriverManager instance = new WebDriverManager();

    // Thread-safe WebDriver storage
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final ThreadLocal<String> tlBrowser = new ThreadLocal<>();

    private WebDriverManager() {
        // private constructor for Singleton pattern
    }

    public static WebDriverManager getInstance() {
        return instance;
    }

    public void setBrowser(String browser) {
        tlBrowser.set(browser.toLowerCase());
    }

    public WebDriver getDriver() {
        if (tlDriver.get() == null) {
            initDriver(tlBrowser.get());
        }
        return tlDriver.get();
    }

    private void initDriver(String browser) {
        if (browser == null) {
            throw new IllegalStateException("Browser type is not set for the current thread.");
        }

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
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
    }

    public void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
            tlBrowser.remove();
        }
    }
}
