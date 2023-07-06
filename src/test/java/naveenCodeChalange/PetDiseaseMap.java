package naveenCodeChalange;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PetDiseaseMap {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://petdiseasealerts.org/forecast-map/");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println(size);
		driver.switchTo().frame(0);
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='regions']//*[@id]//*[@class='child']"));
		System.out.println(elements.size());
		String string = "New York";
		/*
		 * WebElement ele=driver.findElement(By.
		 * xpath("//*[@id='regions']//*[@id]//*[@class='child' and @name='" + string +
		 * "']")); JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].scrollIntoView();",ele );
		 */
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Thread.sleep(5000);
		getState(string);
		Thread.sleep(3000);
		System.out.println(driver.getCurrentUrl());
		driver.close();
	}

	static void getState(String state) throws InterruptedException {

		WebElement ele1 = driver.findElement(By.xpath(
				"//div[@id='map-component']//*[@id='regions']//*[@id]//*[@class='child' and @name='" + state + "']"));
		// ele1.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(ele1);
		Thread.sleep(15000);
		ele1.click();
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].click();",ele1 );
	}

}
