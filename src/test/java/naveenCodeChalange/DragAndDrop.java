package naveenCodeChalange;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDrop {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		Thread.sleep(5000);

		dragCapitals("Stockholm", "Sweden");
		dragCapitals("Copenhagen", "Denmark");
		dragCapitals("Washington", "United States");
		dragCapitals("Oslo", "Norway");
		dragCapitals("Rome", "Italy");
		dragCapitals("Seoul", "South Korea");
		dragCapitals("Madrid", "Spain");

		System.out.println(driver.findElement(By.xpath("//div[@id='box102' and contains(.,'Sweden')]/div"))
				.getCssValue("background-color"));
		WebElement ele3 = driver.findElement(By.xpath("//div[@id='box102' and contains(.,'Sweden')]/div"));

		// obtain color in rgba

		String s = ele3.getCssValue("color");

		// convert rgba to hex

		String c = Color.fromString(s).asHex();
		System.out.println("Color is :" + s);
		System.out.println("Hex code for color:" + c);

		moveCapitalsBack();
		System.out.println("Capitals are moved back succesfully");
		driver.quit();
	}

	public static void dragCapitals(String capitals, String toCity) throws InterruptedException {
		WebElement ele1 = driver
				.findElement(By.xpath("//div[@class='dragableBox' and contains(.,'" + capitals + "')][2]"));
		WebElement ele2 = driver
				.findElement(By.xpath("//div[@class='dragableBoxRight' and contains(.,'" + toCity + "')]"));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(ele1, ele2).build().perform();

	}

	public static void moveCapitalsBack() throws InterruptedException {
		List<WebElement> ele1 = driver.findElements(By.xpath("//div[@class='dragableBox' and contains(@id,'box')]"));
		WebElement ele2 = driver.findElement(By.id("dropContent"));

		for (WebElement element : ele1) {
			Actions actions = new Actions(driver);
			actions.dragAndDrop(element, ele2).build().perform();
			Thread.sleep(3000);
		}

	}

}
