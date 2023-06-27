package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FaceboolStepDef {
	WebDriver driver;
	ChromeOptions chromeOptions = new ChromeOptions();  
	//chromeOptions.addArguments("--headless");
	
	@Given("open the facebbok url")
	public void open_the_facebbok_url() {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}
	@When("navigated to home page")
	public void navigated_to_home_page() {
		String title = driver.getTitle();
		System.out.println(title);
		
		String expected="Facebook - log in or sign up";
		Assert.assertEquals(expected, title);
	}
	@Then("enter user name {string} and Passcode {string}")
	public void enter_user_name_and_passcode(String string, String string2)  {
		driver.findElement(By.name("email")).sendKeys(string);
		   driver.findElement(By.name("pass")).sendKeys(string2);
		   driver.findElement(By.name("login")).click();
	}
	@Then("it should show the error message")
	public void it_should_show_the_error_message() {
	    driver.findElement(By.xpath("(//a[contains(.,'Forgotten')])[1]")).isDisplayed();
	    driver.quit();
	}

}
