package naveenCodeChalange;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightCalender {

	static WebDriver driver;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.makemytrip.com/");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());

		driver.switchTo().frame("notification-frame-173062137");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("webklipper-publisher-widget-container-notification-close-div")));
		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ic_circularclose_grey']")));
		driver.findElement(By.xpath("//span[@class='ic_circularclose_grey']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@class='menu_Flights']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-cy='roundTrip']")));
		driver.findElement(By.xpath("//li[@data-cy='roundTrip']")).click();
		Thread.sleep(2000);

		selectFromAndToCity("New Delhi", "Bangkok");
		String date = GetCurrentDate();
		System.out.println(date);
		selectDate(date, "Jul 14");
	}

	public static void selectFromAndToCity(String fromCity, String ToCity) {
		driver.findElement(By.id("fromCity")).click();
		WebElement ele1 = driver.findElement(By.xpath("//div[@class='calc60' and contains(.,'" + fromCity + "')]"));
		ele1.click();
		driver.findElement(By.id("toCity")).click();
		WebElement ele2 = driver.findElement(By.xpath("//div[@class='calc60' and contains(.,'" + ToCity + "')]"));
		ele2.click();
	}

	public static void selectDate(String departureDate, String ReturnDate) throws InterruptedException {
		Actions actions = new Actions(driver);
	//	driver.findElement(By.xpath("//p[@data-cy='departureDate']")).click();
		Thread.sleep(3000);
		WebElement ele1 = driver.findElement(
				By.xpath("//div[contains(@class,'DayPicker-Day') and contains(@aria-label,'" + departureDate + "')]"));
		actions.moveToElement(ele1).doubleClick().build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[@data-cy='returnDefaultText']")).click();
		WebElement ele2 = driver
				.findElement(By.xpath("//div[contains(@class,'DayPicker-Day') and contains(@aria-label,'"+ReturnDate+"')]"));
		actions.moveToElement(ele2).doubleClick().build().perform();

	}

	public static String GetCurrentDate() {
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
		String date = formatter.format(d);
		String splitter[] = date.split("-");
		String month_year = splitter[1];
		String day = splitter[0];

		String month = month_year.substring(0, 3);
		String dayMonth = month + " " + day;
		System.out.println(month_year);
		System.out.println(day);
		System.out.println(dayMonth);
		return dayMonth;

	}

}
