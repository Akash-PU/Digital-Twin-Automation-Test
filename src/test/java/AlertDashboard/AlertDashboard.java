package AlertDashboard;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import WebElements.AlertWebElements;
import WebElements.LoginWebElements;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlertDashboard {

	WebDriver driver;
	LoginWebElements login;
	AlertWebElements alert;

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
		Thread.sleep(1000);
	}

	@After
	public void closebrowser() {
		System.out.println("Test case Executed");
		driver.close();
		driver.quit();
	}

	@Given("user is on login page")
	public void loginpage() {
		login = new LoginWebElements(driver);
		System.out.println("User is at Login Page");
	}

	@And("entered {string} and {string}")
	public void credentials(String username, String password) throws InterruptedException {
		login.Credentials(username, password);
		Thread.sleep(1500);
	}

	@Then("clicked signin button")
	public void loggedin() {
		login.signin();
		System.out.println("Logged In");
	}

	@And("clicked All Alerts tabs")
	public void allalertspage() throws InterruptedException, IOException {
		alert = new AlertWebElements(driver);
		alert.allalerts();
		Thread.sleep(1000);
		alert.Takescreenshot();
	}

	// @TC_01
	// Scenario: Verify that the dashboard loads successfully
	@When("user logged in it should display Alert Monitoring Dashboard title")
	public void alertdashboard() {
		// alert = new AlertWebElements(driver);
		String dashboard = alert.alerttitle();
		System.out.println("Dashboard title: " + dashboard);
	}

	@And("All Alerts tabs should display Alert history")
	public void allalerts() throws InterruptedException {
		if (alert.alerthistory()) {
			System.out.println("User found list of alerts");
		} else {
			System.out.println("User did not found alerts");
		}
	}

	// @TC_02
	// Scenario Outline: Verify the search functionality in the alert dashboard
	@When("user enters a valid {string} in the search bar")
	public void enteralertid(String alertid) throws InterruptedException, IOException {
		alert.searchalertid(alertid);
		Thread.sleep(1000);
		alert.Takescreenshot();
	}

	@Then("the system should display the corresponding alert details {string}")
	public void validatealert(String alertid) {
		alert.searchalert(alertid);
	}

	// @TC_03
	// Scenario Outline: Verify the search functionality in the alert dashboard
	@When("user enters a Invalid {string} in the search bar")
	public void enterinvalidalertid(String alertid) throws InterruptedException {
		alert.searchalertid(alertid);
		Thread.sleep(1000);
	}

	@Then("the system should display message No Alerts Found for provided {string}")
	public void noalerts(String alertid) {
		alert.searchalert(alertid);
	}

	// @TC_04
	// Scenario Outline: Verify the alert list by selecting valid date range
	@When("user selects a valid date range from {string} to {string}")
	public void selectdaterange(String FromDate, String ToDate) throws InterruptedException, IOException {
		// Code to select date range
		alert.selectdaterange(FromDate, ToDate);
		Thread.sleep(1000);
		alert.Takescreenshot();
	}

	@Then("the system should display the alerts within the selected date range")
	public void displayalerts() {
		// Code to verify displayed alerts
		Assert.assertTrue("Alerts found within the date range ", alert.displayalerts());
	}

	// @TC_05
	// Scenario Outline: Verify the alert list by selecting invalid date range
	@When("user selects an invalid date range from {string} to {string}")
	public void selectinvaliddaterange(String FromDate, String ToDate) throws InterruptedException, IOException {
		// Code to select date range
		alert.selectdaterange(FromDate, ToDate);
		Thread.sleep(1000);
		alert.Takescreenshot();
	}

	@Then("the system should display no alerts for the selected date range")
	public void displayerrormessage() {
		// Code to verify error message
		alert.displayalerts();
	}

	// @TC_06
	// Scenario Outline: Verify the alert list by selecting severity
	@When("user selects a severity of alert {string}")
	public void selectseverity(String severitylevel) throws InterruptedException, IOException {
		// Code to select severity
		alert.selectseverity(severitylevel);
		Thread.sleep(1000);
		alert.Takescreenshot();
	}

	@Then("the system should display the alerts for the selected severity level {string}")
	public void displayalerts(String severitylevel) throws InterruptedException {
		// Code to verify displayed alerts
		alert.verifyselectedseverity(severitylevel);
	}

	// @TC_07
	// Scenario Outline: Devices that capture video reference for raised alerts
	@When("user enters {string} and clicked View Devices option")
	public void viewdevices(String alertid) throws InterruptedException, IOException {
		// Code to view devices
		alert.searchalertid(alertid);
		if (!alert.searchalert(alertid)) {
			Assume.assumeTrue("Alert not found, skipping test", true);
			return;
		}
		alert.viewdevices();
		Thread.sleep(1000);
		alert.Takescreenshot();
	}

	@Then("the system should display the devices that are invovled in raised alert")
	public void displaydevices() throws InterruptedException {
		// Code to verify displayed devices
		String alertdevices = driver.getPageSource();
		if (alertdevices.contains("No records found")) {
			Assume.assumeTrue("Alert not found, skipping test", true);
			return;
		}
		Assert.assertTrue("Devices found invovled in raised alert", alert.displaydevices());
	}

	// @TC_08
	// Scenario Outline: Verify the specific alert details
	@When("user clicked specific {string}")
	public void clickedalertid(String alertid) throws InterruptedException, IOException {
		// Code to click specific alert ID
		alert.searchalertid(alertid);
		if (!alert.searchalert(alertid)) {
			throw new AssertionError("Alert not found, skipping test");
		}
		alert.clickalertid();
		Thread.sleep(1000);
		alert.Takescreenshot();
	}

	@Then("user navigate to the specific alert {string} details page")
	public void alertspecificdetails(String alertid) throws InterruptedException, IOException {
		// Code to verify navigation to alert specific details page
		String alertdevices = driver.getPageSource();
		if (alertdevices.contains("No records found")){
			Assume.assumeTrue("Alert not found, skipping test", true);
			return;
		}
		String expectedurl = "https://appliedsni01/alert-details?alertId=" + alertid;
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			Thread.sleep(1000);
			alert.Takescreenshot();
			String actualurl = driver.getCurrentUrl();
			if (actualurl.contains(expectedurl)) {
				System.out.println("User navigated to alert specific details page " + alert.navigatetoalertspecificdetails());
			}
		}
	}
	@And("Details section should display the following information AlertID, Severity, Alertmessage, Status, Timestamp")
	public void displayalertinformation() throws InterruptedException, IOException {
		// Code to verify displayed alert information
		System.out.println("Alert Information: " + alert.specificalertiddetails());
		Thread.sleep(1000);
		alert.Takescreenshot();
	}

	@And("View Details & Actions button should be clickable and it should display Device Name, Inference Analysis and Status")
	public void clickviewdetailsactions() throws InterruptedException, IOException {
		// Code to verify clickable View Details & Actions button
		login.menu();
		Thread.sleep(500);
		alert.clickviewdetailsactions();
		Thread.sleep(1000);
		alert.Takescreenshot();
	}
}
