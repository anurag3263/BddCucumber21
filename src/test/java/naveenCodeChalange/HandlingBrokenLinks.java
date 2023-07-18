package naveenCodeChalange;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingBrokenLinks {

	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());

		List<WebElement> links = driver.findElements(By.tagName("a"));

		System.err.println("Number of links currently present : " + links.size());

		List<String> urlList = new ArrayList<>();

		for (WebElement e : links) {
			String url = e.getAttribute("href");
			urlList.add(url);
		}
		long startTime=System.currentTimeMillis();
		urlList.parallelStream().forEach(e -> CheckUrlLinks(e));
		long endTime=System.currentTimeMillis();
		
		System.out.println("Total time taken for execution ---> "+(endTime-startTime));
		
		driver.quit();

	}
	
	
	public static void CheckUrlLinks(String linkUrl) {
		try {
			URL url=new URL(linkUrl);
			HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.connect();
			
			if(httpURLConnection.getResponseCode() >= 400) {
				System.out.println(linkUrl + "  ---->  "+httpURLConnection.getResponseMessage() +" is a broken link");
			}
			else {
				System.out.println(linkUrl +" ----> "+httpURLConnection.getResponseMessage());
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
