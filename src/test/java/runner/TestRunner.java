package runner;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import plugIn.JvmReportingPlugin;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/main/java/Features", //the path of the feature files
		glue={"stepDefinition"}, //the path of the step definition files
		//format= {"pretty","html:test-outout", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"},//to generate different types of reporting
    	monochrome = true, //display the console output in a proper readable format
		//strict = true, //it will check if any step is not defined in step definition file
		plugin = {"pretty" , 
				"html:target/htmlReport.html",
				"html:target/site/cucumber-pretty" , "json:target/Results/Cucumber.json"
				//"json:/target/jsonReport.json",
				//"junit:target/junitReport.junit"
				},
				publish = true,
				dryRun = false ,
				tags = "@facebook"
		)//to check the mapping is proper between feature file and step def file
		//tags //= {"~@SmokeTest" , "~@RegressionTest", "~@End2End"}
     
		

public class TestRunner{
	@AfterSuite         
	  public static void generateReport() throws Throwable {
		JvmReportingPlugin.generateJVMReport();
	}
}
