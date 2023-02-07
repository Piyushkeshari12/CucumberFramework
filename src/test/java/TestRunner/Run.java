package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.Cucu mberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(features = {".//Features/LoginFeature.feature",".//Features/Customers.feature"},
		//features = ".//Features/", 
				
				glue = "StepDefinition", 
				dryRun = false, 
				monochrome = true, 
				tags = "@sanity",
				plugin = {
		"pretty", "junit:target/Cucumber-reports/report_xml.xml", "json:target/Cucumber-reports/report_json.json",
		"html:target/Cucumber-reports/reports1.html" }

)

public class Run extends AbstractTestNGCucumberTests{
// this class will be empty
}
