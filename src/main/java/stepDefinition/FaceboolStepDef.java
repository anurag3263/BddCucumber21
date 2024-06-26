package stepDefinition;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import com.basePackage.BaseClass;
import com.util.Utils;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FaceboolStepDef extends BaseClass {
	private Scenario scenario;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

//	WebDriver driver;
	ChromeOptions chromeOptions = new ChromeOptions();
	// chromeOptions.addArguments("--headless");

	@Given("open the facebbok url")
	public void open_the_facebbok_url() {

		// WebDriverManager.chromedriver().setup();

//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("start-maximized"); // open Browser in maximized mode
//		options.addArguments("disable-infobars"); // disabling Infobars
//		options.addArguments("--disable-extensions"); // disabling extensions
//		options.addArguments("--disable-gpu"); // applicable to windows os only
//		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//		options.addArguments("--no-sandbox"); // Bypass OS security model
//		WebDriver driver = new ChromeDriver(options);

//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		setDriver("chrome");
		System.out.println(getDriver());
		getDriver().get("https://www.facebook.com/");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@When("navigated to home page")
	public void navigated_to_home_page() {
		String title = getDriver().getTitle();
		System.out.println(title);

		String expected = "Facebook – log in or sign up";
		Assert.assertEquals(expected, title);
		Utils.attach(scenario);
	}

	@Then("enter user name {string} and Passcode {string}")
	public void enter_user_name_and_passcode(String string, String string2) {
		getDriver().findElement(By.name("email")).sendKeys(string);
		getDriver().findElement(By.name("pass")).sendKeys(string2);
		Utils.attach(scenario);
		getDriver().findElement(By.name("login")).click();
	}

	@Then("it should show the error message")
	public void it_should_show_the_error_message() {
		getDriver().findElement(By.xpath("(//a[contains(.,'Forgotten')])[1]")).isDisplayed();
		Utils.attach(scenario);
		getDriver().quit();
	}

}
