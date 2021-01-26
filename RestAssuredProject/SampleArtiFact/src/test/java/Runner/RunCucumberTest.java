package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"features"},
        monochrome = true,
        plugin = { "json:target/cucumber.json" },
//        plugin = {"pretty"},
        tags = {"@sample"},
        glue = "stepDefnition"
        )
public class RunCucumberTest {
}
