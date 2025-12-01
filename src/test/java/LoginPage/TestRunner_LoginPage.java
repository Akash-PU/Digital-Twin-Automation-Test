package LoginPage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features="src/test/resources/Features", 
glue={"LoginPage"},
plugin = {"pretty", "html:target/HtmlReports/LoginReport.html"},
tags = ("@Validcredentials or @InvalidUsername or @InvalidPassword or @EmptyCredentials or @UsernamelessRange or @PasswordlessRange or @UsernamemoreRange or @PasswordmoreRange or @Casesensitivitycheck")) 
//monochrome = true )
public class TestRunner_LoginPage {
	
//public static void reportpath()
//	{
//		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//		String reportpath = "target/HtmlReports/Report_" + timestamp;
//		System.setProperty("report.path", reportpath);
//		System.out.println(" Report will be generated at: " + reportpath);
//	}
//	
}
