package DeviceList;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import WebElements.ConfigurationWebElements;
import WebElements.LoginWebElements;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.junit.Assert;

public class DeviceList {

	WebDriver driver;
	LoginWebElements login;
	ConfigurationWebElements configure;
	private Scenario scenario;

	@Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }
	
	@Before
	public void openbrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/ASUS/eclipse-workspace/CucumberJava01/src/test/resources/Drivers/chromedriver.exe");
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
		System.out.println("Test case executed");
		driver.close();
		driver.quit();
	}
	
	@Given("user is on login page")
	public void login() {
		login = new LoginWebElements(driver);
		System.out.println("Logged In");
	}
	@And("provided {string} and {string}")
	public void credentials(String username, String password) throws InterruptedException {
		login.Credentials(username, password);
		Thread.sleep(2000);
		System.out.println("Provided credentials");
	}
	@Then("clicked signin button")
	public void signin() throws InterruptedException {
		login.signin();
		Thread.sleep(1000);
		System.out.println("Landed to Main Dashboard");
	}
	@And("select menu option")
	public void menu() throws InterruptedException {
		login.menu();
		Thread.sleep(1000);
		System.out.println("Displayed Menu List");
	}
	@Then("user select Device list option")
	public void Dlistopt() {
		configure = new ConfigurationWebElements(driver);
		configure.Dlistopt();
		System.out.println("Device list option selected");
	}
	@And("redirected to Device List page")
	public void Dlistpage() throws InterruptedException, IOException {
		String expectedurl = "https://appliedsni01/device-list";
		for(String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			String currenturl = driver.getCurrentUrl();
			if(currenturl.equalsIgnoreCase(expectedurl)) {
				configure.Devicelistpage();
				break;
			}
		}
		Thread.sleep(1000);
		configure.TakeScreenshot(driver, scenario, "Device List Page");
	}
//***************************************************************
		//@validscenario
	
		@And("user able to see list of Devices")
		public void Dlist() throws IOException {
			String DeviceListpage = configure.Dlist();
			System.out.println(DeviceListpage);
			configure.TakeScreenshot(driver, scenario, "Device List Page");
		}
		@Then("user able to see Active Devices")
		public boolean Active() throws InterruptedException {
			boolean result = configure.activedevice();
			if(!result) {
				System.out.println("No Active Device found");
				throw new AssertionError("No Active Device found, skipping test");
			}
			System.out.println("User able to see Active Devices");
			return result;
		}
		//***************************************************************	
		//@Configurationoption
		
		@And("user able to see configuration option only for Active {string}")
		public void Cngopt(String DeviceName) throws InterruptedException {
			boolean ActiveDevice = configure.OrderedDevice(DeviceName);
			if(!ActiveDevice) {
				throw new AssertionError("No Active Device found, skipping test");
			}
			boolean ConfigurationOpt = configure.ConfigurationSetting();
			if(ActiveDevice == ConfigurationOpt) {
				System.out.println("Active Device able to show Configuration settings option");
			}
			else {
				System.out.println("Devices are not Active");
				throw new AssertionError("Devices are not Active, skipping test");
			}
		}
		@Then("clicked configuration option")
		public void opt() throws InterruptedException{
			configure.ConfgOpt();
			Thread.sleep(1000);
		}
		@And("user redirected to Device configuration page")
		public void DvcCng() throws InterruptedException, IOException{
			String ExpectedUrl = "https://appliedsni01/device/configuration";
			for(String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
				String CurrentUrl = driver.getCurrentUrl();
				if(CurrentUrl.contains(ExpectedUrl)) {
					Thread.sleep(1000);
					String Dvcname = configure.ConfgDvcname();
					System.out.println("Configuring for Device: " + Dvcname);
					configure.TakeScreenshot(driver, scenario, "Device Configuration Page");
					break;
				}
			}
		}
//		//***************************************************************
//		//@Deviceconfiguration
		@And("user click Add option")
			public void Addoption() throws InterruptedException, IOException {
				configure.Addopt();
				Thread.sleep(1000);
				configure.TakeScreenshot(driver, scenario, "Device Configuration Page");
			}
		@And("user entered {string} and {string}")
		public void Dtime(String starttime, String endtime) throws InterruptedException {
			configure.configurationtime(starttime, endtime);
			System.out.println("Configured Time: " + starttime + " to " + endtime);
			Thread.sleep(1500);
		}
		@Then("select configuration {string}")
		public boolean Dstatus(String Status) throws InterruptedException, IOException {
			boolean result = configure.Confstatus(Status);
			if(!result) {
				System.out.println("Status not selected");
				throw new AssertionError("Status not selected, skipping test");
			}
			Thread.sleep(1000);
			System.out.println("Selected Status: " + Status);
			configure.TakeScreenshot(driver, scenario, "Device Configuration Page");
			return result;
		}
		@And("user clicked Add option")
		public void save() {
			configure.Saveadd();
		}
		@Then("verify configured time saved in Device Configuration")
		public void DeviceCng() throws InterruptedException, IOException {
			if(configure.Confadded().equalsIgnoreCase("Enabled")) {
				System.out.println("Device Configuration Added");
				Thread.sleep(1500);
				configure.TakeScreenshot(driver, scenario, "Device Configuration Page");
			}
		}
		@And("user navigated to main dashboard")
		public void maindsh() throws InterruptedException {
			String expectedurl = "https://appliedsni01/alert-dashboard";
			for(String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
				String currenturl = driver.getCurrentUrl().toString();
				if(currenturl.equalsIgnoreCase(expectedurl)) {
					System.out.println("User navigated to main dashboard");
					Thread.sleep(1500);
				}
			}
		}
//		//***************************************************************
//		//@Invalidtimeconfiguration
		@And("entered {string} more then {string}")
		public void invalidDtime(String starttime, String endtime) throws InterruptedException {
			configure.configurationtime(starttime, endtime);
			Thread.sleep(1500);
		}
		@Then("It should throw exception Start time cannot be more than End time")
		public void expt_time() throws InterruptedException, IOException {
			String startime_exp = configure.morestartime();
			Assert.assertEquals("End Time must be greater than Start Time.", startime_exp);
			Thread.sleep(1500);
			configure.TakeScreenshot(driver, scenario, "Device Configuration Page");
			System.out.println(startime_exp);
		}
//		//***************************************************************
//		//@Invalidtimeformat

		@And("user entered alphabet at {string} and {string}")
		public void less_time(String starttime, String endtime) throws InterruptedException {
			configure.configurationtime(starttime, endtime);
			Thread.sleep(1500);
		}
		@Then("It should throw exception Enter proper time format")
		public void exception_time() throws InterruptedException, IOException {
			String expectedtimeexp = "Invalid time format (HH:MM:SS).";
			String starttimeexp = configure.startimeformatexp();
			String endtimeexp = configure.endtimeformatexp();
			if(expectedtimeexp.equalsIgnoreCase(starttimeexp) || expectedtimeexp.equalsIgnoreCase(endtimeexp)) {
				System.out.println("Enter proper time format (HH:MM:SS)");
			}
		}
		//***************************************************************
		//@Mandatorydeviceconfiguration
		
		@And("entered {string} and {string}")
		public void timing(String starttime, String endtime) throws InterruptedException{
			configure.configurationtime(starttime, endtime);
			Thread.sleep(1000);
		}
		@Then("selected Add option")
		public void saveadd() throws InterruptedException, IOException {
			configure.Saveadd();
			Thread.sleep(1000);
			configure.TakeScreenshot(driver, scenario, "Device Configuration Page");
		}
		@And("It should throw exception Status is mandatory")
		public void confstatus() throws InterruptedException {
			String statusexp = configure.statusexp();
			Assert.assertEquals("Please select a Status.", statusexp);
			System.out.println(statusexp);
			Thread.sleep(1000);
		}
//		//***************************************************************
//		@Blanktimeconfiguration
		
		@And("user not entered {string} and {string}")
		public void timenull(String starttime, String endtime) {
			configure.configurationtime(starttime, endtime);
		}
		@Then("It should throw mandatory exception start time and end time are required")
		public void blanktimeexp() throws InterruptedException, IOException {
			String displayedstartimeexp = "Start Time is required.";
			String displayedendtimeexp = "End Time is required.";
			String starttimeblank = configure.startimeblank();
			String endtimebalnk = configure.endtimebalnk();
			configure.TakeScreenshot(driver, scenario, "Device Configuration Page");
			if(displayedstartimeexp.equalsIgnoreCase(starttimeblank) || displayedendtimeexp.equalsIgnoreCase(endtimebalnk)) {
				System.out.println(starttimeblank);
				System.out.println(endtimebalnk);
				Thread.sleep(1000);
			}
		}
//		*******************************************************************************
//		@Disableconfiguration
		@Then("configured time saved in Device Configuration page")
		public void DvcConfpage() throws InterruptedException, IOException {
			String Dvcconf = configure.Disableadd();
			if(Dvcconf.equalsIgnoreCase("Disabled")) {
				System.out.println(Dvcconf);
				Thread.sleep(1000);
				configure.TakeScreenshot(driver, scenario, "Device Configuration Page");
			}
		}
}
