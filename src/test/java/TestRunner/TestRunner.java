package TestRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/test/resources/features"}, // Path to feature files
        glue = {"stepdefs"}, // Package containing step definitions
        plugin = {"pretty", "html:target/htmlReport.html"} // Reporting options
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // No additional code needed
}
