package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginStepDef {
	
	WebDriver driver;
	
	@Given("^User is already on login page$")
	
	public void user_already_on_login_page()
	{
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
	}
	@When("^title of login page is Free CRM$")
	
	public void title_of_login_page_is_Free_CRM()
	{
		String title = driver.getTitle();
		System.out.println(title);
		
		String expected="vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(expected, title);
	}
	@Then("^user enters username and password$")
	public void user_enters_username_and_password() 
	{
	   driver.findElement(By.name("user_name")).sendKeys("admin");
	   driver.findElement(By.name("user_password")).sendKeys("admin");
	    
	}
	@Then("^user click on login button$")
	public void user_click_on_login_button() 
	{
	    driver.findElement(By.id("submitButton")).click();
	}
	@Then("^user is on the Home Page$")
	public void user_is_on_the_Home_Page() 
	{
	    String ActualhomeTitle=driver.getTitle();
	    Assert.assertEquals(" Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM", ActualhomeTitle);
	}

}
