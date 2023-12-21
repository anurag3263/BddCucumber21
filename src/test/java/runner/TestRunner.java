package runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import plugIn.JvmReportingPlugin;

@RunWith(Cucumber.class) //required if you are using JUnit

@CucumberOptions(
		features="src/main/java/Features", //the path of the feature files
		glue={"stepDefinition"}, //the path of the step definition files
		//format= {"pretty","html:test-outout", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"},//to generate different types of reporting
    	monochrome = true, //display the console output in a proper readable format
		//strict = true, //it will check if any step is not defined in step definition file
    	publish = true,
		dryRun = false ,
		tags = "@facebook or ~@login",
    	plugin = {"pretty" , 
				"html:target/htmlReport.html",
				"html:target/site/cucumber-pretty" , "json:target/Results/Cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				//"json:/target/jsonReport.json",
				//"junit:target/junitReport.junit"
				}
				
		)//to check the mapping is proper between feature file and step def file
         // new way to run multiple tags at once and simultaneously ignore scenario based on tags
         // tags = "~@facebook or @login",
         // when you use ~ before any tags it will ignore that scenario
		//tags = {"~@SmokeTest" , "~@RegressionTest", "~@End2End"}
     
		

public class TestRunner{
	@AfterClass         
	  public static void generateReport() throws Throwable {
		JvmReportingPlugin.generateJVMReport();
	}
}
