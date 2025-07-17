package streamsApiFunction;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SupplierIntUse {
	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());
		List<WebElement> list = driver.findElements(By.tagName("a"));

		System.out.println("Number of Link Available : " + list.size());

		/**
		 * Want to know how much time it's taking to execute
		 */
		long startTime = System.nanoTime(); // Start timing

		list.stream().map(e -> e.getText()).filter(text -> !text.isBlank()).sorted().map(s -> "Link Text : " + s.trim())
				.forEach(System.out::println);

		long endTime = System.nanoTime(); // End timing

		long durationInNano = endTime - startTime;
		double durationInMillis = durationInNano / 1_000_000.0;

		System.out.println("Execution Time: " + durationInMillis + " ms");
		driver.quit();
	}

}
