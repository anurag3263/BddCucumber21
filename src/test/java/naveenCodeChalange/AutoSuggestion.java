package naveenCodeChalange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoSuggestion {

	static WebDriver  driver;
	public static void main(String[] args) throws InterruptedException {
		//.clearDriverCache() //use if driver cache is corrupted
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.co.in/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : "+driver.getTitle());
		driver.findElement(By.xpath("//*[@aria-label='Search']")).sendKeys("webdriver");
		Thread.sleep(3000);
		List<WebElement> list =driver.findElements(By.xpath("//*[@id='Alh6id']/descendant::li"));
		for(WebElement element : list) {
			System.out.println(element.getText());
		}
		list.get(list.size()-1).click();
		System.out.println("Title of first element : "+driver.getTitle());
		driver.quit();
		
	}

}
