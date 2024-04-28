package naveenCodeChalange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetCountryName {
	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://www.w3schools.com/html/html_tables.asp");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		List<WebElement> elements=driver.findElements(By.xpath("//*[@id='customers']//tr/td[2]"));
		for(WebElement ele: elements) {
			System.out.println(ele.getText());
		}

	}

}
