package naveenCodeChalange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZooplaPriceSortingAndSelection {
	
	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().clearDriverCache().setup();
		ChromeOptions options=new ChromeOptions();
//		options.setBrowserVersion("Stable");
		options.addArguments("user-data-dir=~/Library/Application Support/Google/Chrome");
		options.addArguments("profile-directory=Profile 1");
		options.addArguments("--remote-allow-origins=*");
	    driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.zoopla.co.uk/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : "+driver.getTitle());
		
		driver.findElement(By.xpath("//*[@aria-label='Enter a location']")).sendKeys("London");
		driver.findElement(By.xpath("//*[@data-testid='search-button']")).click();
	//	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		List<WebElement> pricelist=driver.findElements(By.xpath("//*[@data-testid='listing-price']"));
		
		double maxPrice=0;
		for(WebElement e: pricelist) {
		double	price=Double.parseDouble(e.getText().replace("$", ""));
			
			if(maxPrice < price) {
				maxPrice=price;
			}
		}
		System.out.println("Highest price : "+maxPrice);
	}

}
