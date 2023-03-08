package cucumber;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
					features="./src/test/java/cucumber",
					glue="LamaAutomation.stepDefinitions", 
					monochrome=true,
					tags ="@Regression", // work same as group in testNg or profile in maven terminal
					plugin={"html:target/cucumber.html"}
				)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{


}
