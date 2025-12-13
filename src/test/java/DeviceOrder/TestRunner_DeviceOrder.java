package DeviceOrder;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions (
		features = "src/test/resources/Features",
		glue = {"DeviceOrder"},
		plugin = {
    				"pretty",
    				"json:target/JsonReports/deviceorder.json",
    				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/extent"},
		tags = ("@ValidcaseDeviceOrder or @SameDeviceName or @BlankDeviceName or @DeselectingDeviceType"))

public class TestRunner_DeviceOrder {
 
}
