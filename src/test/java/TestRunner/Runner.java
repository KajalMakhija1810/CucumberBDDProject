package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {
                "src/test/resources/features/LoginFeature.feature",
                "src/test/resources/features/Customers.feature"
        },
        glue = {"StepDefinition"},
        dryRun = false,
        monochrome = true,
        tags = "@Sanity",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
