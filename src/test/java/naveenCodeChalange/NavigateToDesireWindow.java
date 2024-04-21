package naveenCodeChalange;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigateToDesireWindow {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		System.out.println(driver.getTitle());

		String parentWindowHandle = driver.getWindowHandle();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//div/a[contains(@href,'facebook')]")).click();
		driver.findElement(By.xpath("//div/a[contains(@href,'linkedin')]")).click();
		driver.findElement(By.xpath("//div/a[contains(@href,'twitter')]")).click();
		driver.findElement(By.xpath("//div/a[contains(@href,'youtube')]")).click();

		Thread.sleep(3000);

		// String titleExpected= "LinkedIn";
		// String titleExpected= "YouTube";
		String titleExpected = "X";
		// String titleExpected= "Facebook";

		Set<String> windowsHandles = driver.getWindowHandles();

		List<String> list = new ArrayList<String>(windowsHandles);

		// either pass windowsHandles or list in for each loop

		for (String Hli : windowsHandles) {
			driver.switchTo().window(Hli);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			Thread.sleep(5000);
			String title = driver.getTitle();
			if (title.contains(titleExpected)) {
				System.out.println("Right Window has been found--" + driver.getCurrentUrl());
				driver.close();
				break;
			}
		}
		driver.switchTo().window(parentWindowHandle);
		System.out.println("Parent window -- " + driver.getTitle());

		Thread.sleep(5000);
		driver.quit();
	}

}
