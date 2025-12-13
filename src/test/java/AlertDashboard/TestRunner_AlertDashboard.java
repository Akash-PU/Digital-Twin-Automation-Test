package AlertDashboard;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features",
		glue = {"AlertDashboard"},
		plugin = {
    				"pretty",
    				"json:target/JsonReports/alertdashboard.json",
    				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/extent"},
		tags = ("@TC_01 or @TC_02 or @TC_03 or @TC_04 or @TC_05 or @TC_06 or @TC_07 or @TC_08")
		)

public class TestRunner_AlertDashboard {

}
