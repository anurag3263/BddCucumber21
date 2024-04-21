package naveenCodeChalange;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus_Calendar {
	static WebDriver driver;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().clearDriverCache().setup();
		ChromeOptions options = new ChromeOptions();
//			options.setBrowserVersion("Stable");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());
		getHolidaysAndWeekendsDate("Apr 2024");
		
		////div[contains(.,'Mar 2024') and @class='DayNavigator__IconBlock-qj8jdz-2 iZpveD']
		////div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD']/*[name()='svg']
		//div#onwardCal
		////div[contains(.,'Mar 2024') and @class='DayNavigator__IconBlock-qj8jdz-2 iZpveD']/div
	}
	
	public static String getHolidaysAndWeekendsDate(String monthAndYear) {
		driver.findElement(By.cssSelector("div#onwardCal")).click();
		String monthString=driver.findElement(By.xpath("//div[contains(.,'"+monthAndYear+"') and @class='DayNavigator__IconBlock-qj8jdz-2 iZpveD']")).getText();
		
		if (monthString.contains(monthAndYear)==true) {
			System.out.println(monthString);
		//	System.out.println(driver.findElement(By.xpath("//div[contains(.,'Mar 2024') and @class='DayNavigator__IconBlock-qj8jdz-2 iZpveD']/div")).getText());
		}else {
			driver.findElement(By.xpath("//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD']/*[name()='svg']/..")).click();
		}
		
		
		
		return null;
		
		
	}

}
