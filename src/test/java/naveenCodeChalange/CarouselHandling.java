package naveenCodeChalange;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CarouselHandling {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.noon.com/uae-en/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());
		
		By corousel_xpath=By.
				xpath("//div[@class='sc-kItwNn ifHGEf']/child::div/following-sibling::div[contains(@class,'swiper-button-next')]");
		WebElement corousel_xpath1=driver.findElement(By.
				xpath("//div[@class='sc-kItwNn ifHGEf']//div[starts-with(@class,'swiper-button-next')]"));
		
		List<WebElement> product_nameS=driver.findElements(By.xpath("//div[@class='sc-cUEIKg cVpbYF']//div[@data-qa='product-name']"));
		
		JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,200)", "");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(corousel_xpath));
		
		
		
		jsExecutor.executeScript("arguments[0].scrollIntoView()", corousel_xpath1);
		Thread.sleep(3000);
		LinkedHashSet<String> set= new LinkedHashSet<>();
		while(corousel_xpath1.isDisplayed()==true) {
			
//			product_nameS.stream().
//			forEach(e -> System.out.println(e.getText()));
			
			for(WebElement pnam :product_nameS ) {
				
				set.add(pnam.getText().trim());
			
				corousel_xpath1.click();
		}
		     System.out.println(set);

	}
	}
}
