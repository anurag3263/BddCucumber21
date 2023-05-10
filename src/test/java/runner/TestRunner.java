package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={".\\src\\main\\java\\Features\\login.feature"}, //the path of the feature files
		glue={"stepDefinition"}, //the path of the step definition files
		//format= {"pretty","html:test-outout", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"},//to generate different types of reporting
    	monochrome = true, //display the console output in a proper readable format
		strict = true, //it will check if any step is not defined in step definition file
		plugin = {"pretty" , 
				"html:target/htmlReport.html",
				//"json:/target/jsonReport.json",
				//"junit:target/junitReport.junit"
				},
		dryRun = false )//to check the mapping is proper between feature file and step def file
		//tags //= {"~@SmokeTest" , "~@RegressionTest", "~@End2End"}			
		

public class TestRunner {

}
