package co.id.stepdefinations.hooks;

import co.id.runner.ProblemStatement2TestRunner;
import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class Hooks 
{
	public static Scenario scenario;
	private static String scenarioName;
	@Before
	public static void setup(Scenario scenario) {
		Hooks.scenario = scenario;
		scenarioName = scenario.getName();
	}
	

	
	

}
