package DeviceOrder;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions (
		features = "src/test/resources/Features",
		glue = {"DeviceOrder"},
		plugin = {"pretty", "html:target/HtmlReports/DeviceOrderReport.html"},
		tags = ("@ValidcaseDeviceOrder or @SameDeviceName or @BlankDeviceName or @DeselectingDeviceType")
		)

public class TestRunner_DeviceOrder {

}
