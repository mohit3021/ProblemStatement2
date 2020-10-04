package co.id.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/usr/features/api" }, glue = {
		"co.id.stepdefinations" }, plugin = {
				"com.cucumber.listener.ExtentCucumberFormatter:target/result/cucumber-reports/report.json",
				"html:target/result/cucumber-reports/cucumber.html",
				"com.cucumber.listener.ExtentCucumberFormatter:target/result/cucumber-parallel/report.json",
				"json:target/result/cucumber-parallel/cucumber.json",
				"html:target/result/cucumber-parallel/cucumber.html" }, tags = "@apiTest", monochrome = true, strict = true, dryRun = false)
public class ProblemStatement2TestRunner {


}
