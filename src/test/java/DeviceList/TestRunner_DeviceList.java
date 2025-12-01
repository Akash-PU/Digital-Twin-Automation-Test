package DeviceList;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/Features", 
glue = {"DeviceList"},
plugin = {"pretty", "html:target/HtmlReports/DeviceList.html"},
tags =("@validscenario or @Configurationoption or @Deviceconfiguration or @Invalidtimeconfiguration or @Invalidtimeformat or @Mandatorydeviceconfiguration or @Blanktimeconfiguration or @Disableconfiguration"))

public class TestRunner_DeviceList {

}
