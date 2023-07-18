package naveenCodeChalange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingSvgGraph {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://emicalculator.net/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("emibarchart"))).build().perform();

		Thread.sleep(2000);

		String textpath = "//*[local-name()='svg']//*[name()='g' and contains(@class,'highcharts-tooltip highcharts-color-undefine')]//*[name()='text']";

		List<WebElement> barsList = driver.findElements(By.xpath(
				"//*[local-name()='svg']//*[name()='g' and @class='highcharts-series-group']//*[name()='rect']"));

		System.out.println("Total numbers of bars available : " + barsList.size());

		for (WebElement element : barsList) {
			actions.moveToElement(element).perform();
			Thread.sleep(500);
			String textString = driver.findElement(By.xpath(textpath)).getText();
			System.out.println(textString);
		}

		driver.quit();

	}

}
