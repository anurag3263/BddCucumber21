package com.basePackage;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppTest {

	private WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
    	WebDriverManager.getInstance().setBrowser(browser);
        driver = WebDriverManager.getInstance().getDriver();
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com/");
        System.out.println(Thread.currentThread().getName() + "; " + driver.getTitle());
        assertTrue(driver.getTitle().contains("Google"));
    }

    @AfterClass
    public void tearDown() {
        WebDriverManager.getInstance().quitDriver();
    }

}
