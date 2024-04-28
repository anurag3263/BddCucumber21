package naveenCodeChalange;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStack {
	
	public static final String userName = "anuragchaturvedi_3ZzYMf";
	public static final String accessKey = "pjv2aduqvAYvSSDyRGLS";
	public static final String url = "https://" + userName + ":" + accessKey + "@hub-cloud.browserstack";

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os", "Windows");
		caps.setCapability("osVersion", "10");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("browserVersion", "120.0");
		caps.setCapability("browserstackLocal", true);
		caps.setCapability("buildName", "bstack-demo");
		caps.setCapability("projectName", "BrowserStack Sample");
		caps.setCapability("debug", true);
		caps.setCapability("networkLogs", true);
		caps.setCapability("consoleLogs", "info");
		
		WebDriver driver=new RemoteWebDriver(new URL (url), caps);
		driver.get("http://google.com");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("browserStack");
		element.submit();
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
