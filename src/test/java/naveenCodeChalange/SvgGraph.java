package naveenCodeChalange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SvgGraph {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.statista.com/statistics/1104054/india-coronavirus-covid-19-daily-confirmed-recovered-death-cases/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());
		
		WebElement element=driver.findElement(By.xpath("//div[@id='statisticContainer']"));
		
		Actions actions= new Actions(driver);
		actions.moveToElement(element).perform();
		
		List<WebElement> graphlist= driver.findElements(By.
				xpath("//*[local-name()='g' and @class='highcharts-series-group']/*[name()='g'][5]/*[name()='rect']"));
		
		List<WebElement> tooltipText= driver.findElements(By.
				xpath("//div[@class='highcharts-label highcharts-tooltip highcharts-color-undefined']//table//tr/td[@align='right']"));
		
		for(WebElement element2 : graphlist) {
			actions.moveToElement(element2).perform();
			Thread.sleep(500);
			for(WebElement ele3 : tooltipText) {
				actions.moveToElement(ele3).perform();
				System.out.println("Value : "+ele3.getText());
			}
		}
		
		driver.quit();

	}

}
