package naveenCodeChalange;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartPriceListSorting {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.flipkart.com/");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		Thread.sleep(5000);

//		WebDriverWait wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2KpZ6l _2doB4z']")));
//		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='_2SmNnR']/input")).sendKeys("iphone12");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		assertTrue(driver.findElements(By.xpath("//div[@class='_30jeq3 _1_WHN1']")).get(0).isDisplayed());

		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='_30jeq3 _1_WHN1']"));
		ArrayList<String> priceList = new ArrayList<>();

		for (WebElement Element : ele) {
			priceList.add(Element.getText());
		}
		System.out.println(priceList);
		Collections.sort(priceList);
		System.out.println("After Sorting the Priice : " + priceList);
	}

}
