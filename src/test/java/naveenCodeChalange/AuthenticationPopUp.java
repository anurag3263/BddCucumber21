package naveenCodeChalange;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v113.network.Network;
import org.openqa.selenium.devtools.v113.network.model.Headers;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AuthenticationPopUp {

	static WebDriver driver;
	private static final String username = "admin";
	private static final String password = "admin";

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.manage().window().maximize();

		// add Selenium java 4.10.0 dependency in POM
		// and Add com.google.guava dependency in POM

		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// send authentication header

		Map<String, Object> header = new HashMap<>();
		String basicAuth = "Basic "
				+ new String(new Base64().encode(String.format("%s:%s", username, password).getBytes()));
		header.put("Authorization", basicAuth);

		devTools.send(org.openqa.selenium.devtools.v113.network.Network.setExtraHTTPHeaders(new Headers(header)));
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");

		// 2nd way pass the credentials in the URL

		// driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");

		// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		driver.close();

	}

}
