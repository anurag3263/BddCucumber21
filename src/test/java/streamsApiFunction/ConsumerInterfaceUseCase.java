package streamsApiFunction;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConsumerInterfaceUseCase {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/select-menu");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());
		WebElement list = driver.findElement(By.id("oldSelectMenu"));

		List<WebElement> option = new Select(driver.findElement(By.id("cars"))).getOptions();
		// with conventional way
//		selectValueFromDropdown(list, "Green", "text");
//		Thread.sleep(3000);
//		selectValueFromDropdown(list, "4", "value");
//		Thread.sleep(3000);
		selectValueFromDropdown(list, "9", "index");
		Thread.sleep(3000);

		// with consumer interface
		selectFromDropdown(e -> e.selectByVisibleText("Green"), list);
		Thread.sleep(3000);
		selectFromDropdown(e -> e.selectByValue("4"), list);
		Thread.sleep(3000);
		selectFromDropdown(e -> e.selectByIndex(6), list);

		// option.forEach(e ->e.click());
		option.stream().skip(2).forEach(e -> e.click()); // if you want to skip first 2
		System.out.println("All executed Successfully");
		driver.quit();
	}

	private static void selectValueFromDropdown(WebElement element, String textValueOrIndex, String strategy) {
		Select select = new Select(element);
		if (strategy.equalsIgnoreCase("text")) {
			select.selectByVisibleText(textValueOrIndex);
		} else if (strategy.equalsIgnoreCase("value")) {
			select.selectByValue(textValueOrIndex);
		} else if (strategy.equalsIgnoreCase("index")) {
			select.selectByIndex(Integer.parseInt(textValueOrIndex));
		} else {
			throw new UnsupportedOperationException("Choose correct strategy");
		}

	}

	private static void selectFromDropdown(Consumer<Select> consumer, WebElement element) {
		consumer.accept(new Select(element));
	}

}
