package naveenCodeChalange;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorldPopulationCounter {

	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.worldometers.info/world-population/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());
		long start = System.currentTimeMillis();
		long end = start + 3 * 1000;
		while (System.currentTimeMillis() < end) {
			System.out.println("Total Current Population : "
					+ driver.findElement(By.xpath("//span[@rel='current_population']")).getText());
			System.out.println("-----------------------------------");
			System.out
					.println("Birth Today : " + driver.findElement(By.xpath("//span[@rel='births_today']")).getText());
			System.out
					.println("Deaths Today : " + driver.findElement(By.xpath("//span[@rel='dth1s_today']")).getText());
		}

	}

}
