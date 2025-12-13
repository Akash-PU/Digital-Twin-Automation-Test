package LoginPage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features="src/test/resources/Features", 
glue={"LoginPage"},
plugin = {
    				"pretty",
    				"json:target/JsonReports/LoginPage.json",
    				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/extent"},
tags =("@Validcredentials or @InvalidUsername or @InvalidPassword or @EmptyCredentials or @UsernamelessRange or @PasswordlessRange or @UsernamemoreRange or @PasswordmoreRange or @Casesensitivitycheck"))
public class TestRunner_LoginPage {

}
