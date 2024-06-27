package stepDefinition;

import java.time.Duration;

import com.basePackage.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonStepDef extends BaseClass {
	@Given("open the amazon url")
	public void open_the_amazon_url() {
		setDriver("chrome");
		System.out.println(getDriver());
		getDriver().get("https://www.amazon.in/");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@When("navigated to home page and close")
	public void navigated_to_home_page_and_close() {
		System.out.println("   "+Thread.currentThread().getId());
		System.out.println(getDriver().getCurrentUrl());
	}

	@Then("perform action")
	public void perform_action() {
		System.out.println(getCurrentThreadId());
		System.out.println(getDriver().getTitle());
		tearDown();
	}
}
