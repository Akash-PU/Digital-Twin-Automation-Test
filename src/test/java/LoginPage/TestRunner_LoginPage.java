package LoginPage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features="src/test/resources/Features", 
glue={"LoginPage"},
plugin = {"pretty", "html:target/HtmlReports/LoginReport.html"},
tags =("@Validcredentials or @InvalidUsername or @InvalidPassword or @EmptyCredentials or @UsernamelessRange or @PasswordlessRange or @UsernamemoreRange or @PasswordmoreRange or @Casesensitivitycheck"))
public class TestRunner_LoginPage {

}
