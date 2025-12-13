package DeviceOrder;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import WebElements.LoginWebElements;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class DeviceOrder {

	WebDriver driver;
	LoginWebElements login;
	private Scenario scenario;
	Date currentdate = new Date();
	@Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }
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
		Thread.sleep(2000);
	}

	@After
	public void closebrowser() {
		System.out.println("Test case executed");
		driver.close();
		driver.quit();
	}

	@Given("user in on login page")
	public void loginpage() {
		login = new LoginWebElements(driver);
		System.out.println("Login Page");
	}

	@And("entered {string} and {string}")
	public void credentials(String username, String password) {
		login.Credentials(username, password);
	}

	@Then("clicks on sign in button")
	public void signin() {
		login.signin();
	}

	@And("User click menu option")
	public void menu() throws InterruptedException {
		login.menu();
		Thread.sleep(2000);
	}

	@Then("select Device order menu")
	public void deviceordermenu() throws InterruptedException {
		login.deviceorder();
		Thread.sleep(2000);
	}

	@And("user redirected to Device order tab")
	public void Devicetab() throws InterruptedException, IOException {
		String expectedurl = "https://appliedsni01/device-order";
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			String currenturl = driver.getCurrentUrl();
			if (currenturl.equalsIgnoreCase(expectedurl)) {
				System.out.println(currenturl);
				login.TakeScreenshot(driver, scenario, "DeviceOrder");
				break;
			}
		}
		Thread.sleep(2000);
	}
	// -----------------------------------------------------
	// @ValidcaseDeviceOrder
	@Then("user enter {string}")
	public void DvcName(String Devicename) {
		login.devicename(Devicename);
	}

	@And("click Device Type drop down and select the device type")
	public void devicetype() throws InterruptedException {
		login.devicetype();
		Thread.sleep(2000);
	}

	@Then("click Submit button")
	public void order() throws InterruptedException {
		login.order();
		Thread.sleep(2000);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	// --------------------------------------------------------------
	// @SameDeviceName
	// ("Scenario Outline: Testing negative case by providing same device name")

	@And("user enter the exsisting {string}")
	public void Dname(String Devicename) throws InterruptedException {
		login.devicename(Devicename);
		Thread.sleep(2000);
	}

	@Then("user selected Device Type")
	public void DvcTyp() throws InterruptedException {
		login.devicetype();
		Thread.sleep(2000);
	}

	@And("user clicked submit button")
	public void Dbutton() throws InterruptedException, IOException {
		login.order();
		Thread.sleep(2000);
		login.TakeScreenshot(driver, scenario, "SameDeviceName");
	}

	@Then("Device already exsist exception should be displayed")
	public void exception() throws InterruptedException {
		String errormsg = login.samename();
		System.out.println(errormsg);
		Assert.assertEquals("Device with name 'Camera 04' already exists.", errormsg);
	}
	// --------------------------------------------------------------------------------------------
	// @BlankDeviceName
	// Scenario Outline: Testing blank device name will be allowed or not in Device
	// Order

	@And("user click Device Type Drop down and select Device type")
	public void Dtype() throws InterruptedException {
		login.devicetype();
		Thread.sleep(1000);
	}

	@Then("Submit button opacity should be {string}")
	public void visible(String expect_opacity) throws InterruptedException, IOException {
		// String expect_opacity1 =
		// driver.findElement(By.cssSelector("opacity")).toString();
		System.out.println("Opacity value from test step: " + expect_opacity);
		String actualopacity = login.button();
		System.out.println("Opacity value from redirected page: " + actualopacity);
		Thread.sleep(1000);
		login.TakeScreenshot(driver, scenario, "BlankDeviceName");
		Assert.assertEquals(expect_opacity, actualopacity);
	}
	// -----------------------------------------------------------------------------------
	// @DeselectingDeviceType
	// Scenario Outline: Testing Device order without selecting Device Type

	@And("And user enter {string}")
	public void Dvcname(String Devicename) {
		login.devicename(Devicename);
	}

	@Then("User not selected Device Type and Submit button opacity should be {string}")
	public void opacity(String expected_opacity) throws InterruptedException, IOException {
		System.out.println("Opacity value from Test Step: " + expected_opacity);
		String Actual_opacity = login.button();
		Thread.sleep(1000);
		System.out.println("Opacity value from tesing window: " + Actual_opacity);
		login.TakeScreenshot(driver, scenario, "DeselectingDeviceType");
		Assert.assertEquals(expected_opacity, Actual_opacity);
	}
}
