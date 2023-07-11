package naveenCodeChalange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectHighestPriceProductStream {
	
	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/v1/inventory.html");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : "+driver.getTitle());
		List<WebElement> pricelist=driver.findElements(By.className("inventory_item_price"));
		
		double maxPrice=pricelist.stream()
				                          .mapToDouble(e -> Double.parseDouble(e.getText().trim().replace("$", "")))
				                          .max()
				                          .getAsDouble();
		
		System.out.println("Maximum Price : "+maxPrice);
		
	//	WebElement xpathElement=driver.findElement(By.xpath("//div[contains(.,'$49.99')]/following-sibling::button[.='ADD TO CART']"));
		
	WebElement maxPriceAddToCart=driver.findElement(By.xpath("//div[normalize-space()='$"+maxPrice+"']/following-sibling::button[.='ADD TO CART']"));	
		
	maxPriceAddToCart.click();
	    
	    System.out.println("Highest price Item Added to cart");
	
	    System.out.println("-----------------------------------");
		
		double minPrice=pricelist.stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().trim().replace("$", "")))
                .min()
                .getAsDouble();
		
        System.out.println("Minimum Price : "+minPrice);
        
    WebElement minPriceAddToCart=driver.findElement(By.xpath("//div[normalize-space()='$"+minPrice+"']/following-sibling::button[.='ADD TO CART']"));	
		
        minPriceAddToCart.click();
	    
	    System.out.println("Minimum price Item Added to cart");
	    
	    driver.close();
	}

}
