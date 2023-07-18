package naveenCodeChalange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicWebtable {

	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://cosmocode.io/automation-practice-webtable/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());

		String xapath = "//table[@id='countries']/tbody/tr/td[contains(.,'')]";

		//String country = "Tirane";

		//WebElement checkbox=driver.findElement(By.xpath("//table[@id='countries']/tbody/tr/td[contains(.,'"+country+"')]/../td[1]/input"));
		// actions.moveToElement(checkbox).perform();
		// actions.click();

		List<WebElement> checkbox2 = driver.findElements(By.xpath("//tbody/tr/td[contains(.,'')]/../td[1]/input"));
		// checkbox2.parallelStream().forEach(e -> e.click());
		checkbox2.stream().forEach(e -> e.click());

		List<WebElement> listText = driver.findElements(By.xpath(xapath));

		listText.stream().forEach(e -> System.out.println("Text Value : " + e.getText()));

		/*
		 * Actions actions=new Actions(driver); for(WebElement element : listText) {
		 * actions.moveToElement(element).perform();
		 * System.out.println("Text value : "+element.getText());
		 * 
		 * }
		 */

		driver.quit();

	}

}
