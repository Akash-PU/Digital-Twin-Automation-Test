package LoginPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import WebElements.LoginWebElements;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

	WebDriver driver;
	LoginWebElements login;
	
	@Before
	public void openbrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/ASUS/eclipse-workspace/CucumberJava01/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://appliedsni01/");
		driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();
		Thread.sleep(3000);
	}
	
	@After
	public void closebrowser() {
		System.out.println("Test case executed");
		driver.close();
		driver.quit();
	}
//	********************************************
	//@Validcredentials
	@Given("user in on login page")
	public void user_in_on_login_page() throws InterruptedException {

		System.out.println("Login Page");
		login = new LoginWebElements(driver);
	}
	
	@When("^user enters valid credentials (.*) and (.*)$")
	public void user_enters_valid_username_password(String username, String password) throws InterruptedException {
		System.out.println("Valid Credentials entered");
		login.Credentials(username, password);
		Thread.sleep(3000);
	}

	@And("clicks on sign in button")
	public void click_on_signin_button() {
		System.out.println("Clicked Signin option");
		login.signin();
	}

	@Then("user is navigated to main Dashboard")
	public void user_navigated_to_Dashboard() {
		System.out.println("User navigated to Dashboard page");
	}

	@And("user click open menu option")
	public void menu_option() throws InterruptedException {
		System.out.println("Clicked menu option");
		login.menu();
		Thread.sleep(3000);
	}

	@When("user click logout option")
	public void logout_option() throws InterruptedException {
		System.out.println("Clicked logout option");
		login.logout();
		Thread.sleep(1000);
	}

	@Then("user navigated back to Login page")
	public void Login_page() {
		System.out.println("Navigated back to login page");
	}
//***********************************************************************
//	@InvalidUsername
	@When("^user enters Invalid (.*) and (.*)$")
	public void Invalid_Username(String username, String password) throws Exception {
		System.out.println("Invalid username entered");
		login.Credentials(username, password);
		Thread.sleep(2000);
	}

//	And clicks on sign in button

	@Then("Throwing exception Invalid username")
	public void Invalid_exception() throws InterruptedException{
		String errormsg = login.InvalidCredentials();
		Assert.assertEquals("Invalid Username or Password.", errormsg);
		Thread.sleep(2000);
	}
//	**********************************************************************
//	@InvalidPassword
	@When("^user enters (.*) and Invalid (.*)$")
	public void Invalid_Password(String username, String password) throws InterruptedException {
	System.out.println("Invalid password entered");
	login.Credentials(username, password);
	Thread.sleep(2000);
	}
//	And clicks on sign in button
	@Then("Throwing exception Invalid password")
	public void Invalidpwd_exception() throws InterruptedException {
		String errormsg = login.InvalidCredentials();
		Assert.assertEquals("Expectedoutput: Invalid Username or Password.", errormsg);
		Thread.sleep(2000);
	}
//	**********************************************************************
//	@EmptyCredentials
	@When("user enters empty (.*) and empty (.*)")
	public void emptycredentials() throws InterruptedException {
		System.out.println("Empty Username and Password");
		login.Credentials("", "");
		Thread.sleep(1000);
	}
//	And clicks on sign in button
	@Then("Throwing exception Username is required and Password is required")
	public void empty_case() {
		String usr_errormsg = login.emptyusername().toString();
		String pwd_errormsg = login.emptypassword().toString();
		if(usr_errormsg.equals("Username is required") && pwd_errormsg.equals("Password is required"))
				{
					Assert.assertEquals("Username is required", usr_errormsg );
					Assert.assertEquals("Password is required", pwd_errormsg);
					System.out.println(usr_errormsg + " " + pwd_errormsg);
				}
		else {
			Assert.fail("Test Case failed: " + usr_errormsg + pwd_errormsg);
		}
			
	}
	
//	**********************************************************************
//	@UsernamelessRange
	@When("^user enter less than (?:three|3) characters (.*) and valid (.*)$")
	public void lesscharacter_username(String username, String password) throws InterruptedException {
		System.out.println("Entered less character username and valid password");
		login.Credentials(username, password);
		Thread.sleep(2000);
	}
//	And clicks on sign in button
	@Then("Throwing exception Username must be at least 3 characters")
	public void less_username() {
		String less_username = login.lessuser();
		Assert.assertEquals("Username must be at least 3 characters", less_username);
	}

//	**********************************************************************
//	@PasswordlessRange
	@When("^user enter valid (.*) and less than 6 characters in (.*)$")
	public void lesscharacter_password(String username, String password) throws InterruptedException {
		System.out.println("Entered less character password and valid username");
		login.Credentials(username, password);
		Thread.sleep(1000);
	}
//	And clicks on sign in button
	@Then("^Throw exception Password must be at least 6 characters$")
	public void less_pwd() {
		String less_password = login.lesspassword();
		Assert.assertEquals("Password must be at least 6 characters", less_password);
	}

//	**********************************************************************
//	@UsernamemoreRange
	@When("^user enter more than 20 characters in (.*) and valid (.*)$")
	public void morecharacter_username(String username, String password) throws InterruptedException {
		System.out.println("Entered 20 character in username");
		login.Credentials(username, password);
		Thread.sleep(1000);
	}
//	And clicks on sign in button
	@Then("^Throw exception Username must be no more than 20 characters$")
	public void usr_morerange() {
		String usr_morerange = login.moreuser();
		Assert.assertEquals("Username must be no more than 20 characters", usr_morerange);
	}

//	**********************************************************************
//	@PasswordmoreRange
	@When("^user enter valid (.*) and more than 24 characters (.*)$")
	public void morecharacter_password(String username, String password) throws InterruptedException {
		System.out.println("Entered 24 characters in password");
		login.Credentials(username, password);
		Thread.sleep(1000);
	}
	//And clicks on sign in button
	@Then("Throw password must be more than 24 characters exception")
	public void pwd_morerange() {
		String pwd_morerange = login.emptypassword();
		Assert.assertEquals("Password must be no more than 24 characters", pwd_morerange);
	}

//	**********************************************************************
//	@Casesensitivitycheck
	@When("^user enters not casesensitive (.*) and valid (.*)$")
		public void casesensitive(String username, String password) throws InterruptedException {
		System.out.println("Entered username without casesensitive");
		login.Credentials(username, password);
		Thread.sleep(1000);
	}
//	And clicks on sign in button
    @Then("Throwing exception Invalid casesensitive username")
    public void usr_case() {
    	String usr_case = login.InvalidCredentials();
    	Assert.assertEquals("Invalid Username or Password.", usr_case);
    }

}
