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
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.junit.Assert;

public class DeviceList {

	WebDriver driver;
	LoginWebElements login;
	ConfigurationWebElements configure;
	
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
				System.out.println("User is at Device List page ");
				break;
			}
		}
		Thread.sleep(1000);
		configure.TakeScreenshot();
	}
//***************************************************************
		//@validscenario
	
		@And("user able to see list of Devices")
		public void Dlist() {
			String DeviceListpage = configure.Dlist();
			System.out.println(DeviceListpage);
		}
		@Then("user able to see Active Devices")
		public void Active() throws InterruptedException {
			boolean result = configure.OrderedDevice();
			if(!result) {
				new AssertionError("No Active Device found");
			}
			System.out.println("User able to see Active Devices");
		}
		//***************************************************************	
		//@Configurationoption
		
		@And("user able to see configuration option only for Active Devices")
		public void Cngopt() throws InterruptedException {
			boolean ActiveDevice = configure.OrderedDevice();
			boolean ConfigurationOpt = configure.ConfigurationSetting();
			if(ActiveDevice == ConfigurationOpt) {
				System.out.println("Only Active Device able to Configure Object Detection");
			}
			else {
				System.out.println("Devices are not Active");
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
					configure.TakeScreenshot();
					break;
				}
			}
		}
//		//***************************************************************
//		//@Deviceconfiguration	
		
		@And("user click settings option for Active Device")
		public void settings() throws InterruptedException, IOException {
			boolean activedevice = configure.OrderedDevice();
			boolean confopt = configure.ConfigurationSetting();
			if(activedevice == confopt) {
				configure.confbtn();
				Thread.sleep(1000);
				configure.TakeScreenshot();
				System.out.println("User clicked Setting option");
			}
		}
		@And("user click Add option")
			public void Addoption() throws InterruptedException, IOException {
				configure.Addopt();
				Thread.sleep(1000);
				configure.TakeScreenshot();
			}
		@And("user entered {string} and {string}")
		public void Dtime(String starttime, String endtime) throws InterruptedException {
			configure.configurationtime(starttime, endtime);
			Thread.sleep(1500);
		}
		@Then("select configuration status Enable")
		public void Dstatus() throws InterruptedException, IOException {
			configure.Confstatus();
			Thread.sleep(1000);
			configure.TakeScreenshot();
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
				configure.TakeScreenshot();
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
		
		@And("user click settings option of Active Device")
		public void ActDvcsetting() throws InterruptedException {
			boolean activedevice = configure.OrderedDevice();
			boolean confdvc = configure.ConfigurationSetting();
			if(activedevice == confdvc) {
				configure.confbtn();
				System.out.println("User clicked Setting option");
			}
		}
		@Then("click Add option")
		public void addoption(){
			configure.Addopt();
		}
		@And("entered {string} more then {string}")
		public void invalidDtime(String starttime, String endtime) throws InterruptedException {
			configure.configurationtime(starttime, endtime);
			Thread.sleep(1500);
		}
		@Then("selected Enable status of configuration")
		public void Dvcstatus() throws InterruptedException {
			configure.Confstatus();
		}
		@And("clicked Add option")
		public void Dsave() throws InterruptedException {
			configure.Saveadd();
			Thread.sleep(1000);
		}
		@Then("It should throw exception Start time cannot be more than End time")
		public void expt_time() throws InterruptedException, IOException {
			String startime_exp = configure.morestartime();
			Assert.assertEquals("End Time must be greater than Start Time.", startime_exp);
			Thread.sleep(1500);
			configure.TakeScreenshot();
			System.out.println(startime_exp);
		}
//		//***************************************************************
//		//@Invalidtimeformat
	
		@And("user selected configuration option")
		public void settingsopt() throws InterruptedException {
			boolean activedvc = configure.OrderedDevice();
			boolean settingopt = configure.ConfigurationSetting();
			if(activedvc == settingopt) {
				configure.confbtn();
				System.out.println("User clicked Setting option");
			}
		}
		@Then("user select Add option")
		public void adopt() {
			configure.Addopt();
		}
		@And("user entered alphabet at {string} and {string}")
		public void less_time(String starttime, String endtime) throws InterruptedException {
			configure.configurationtime(starttime, endtime);
			Thread.sleep(1500);
		}
		@Then("selected Enable status at configuration")
		public void cng_status() throws InterruptedException {
			configure.Confstatus();
			Thread.sleep(1000);
		}
		@And("user selected Add option")
		public void Dvcsave() throws InterruptedException, IOException {
			configure.Saveadd();
			Thread.sleep(1000);
			configure.TakeScreenshot();
		}
		@Then("It should throw exception Enter proper time format")
		public void exception_time() throws InterruptedException, IOException {
			String expectedtimeexp = "Invalid time format (HH:MM:SS).";
			String starttimeexp = configure.startimeformatexp();
			String endtimeexp = configure.endtimeformatexp();
			if(expectedtimeexp.equalsIgnoreCase(starttimeexp) && expectedtimeexp.equalsIgnoreCase(endtimeexp)) {
				System.out.println("Enter proper time format (HH:MM:SS)");
			}
		}
		//***************************************************************
		//@Mandatorydeviceconfiguration
		
		@And("selected configuration option")
		public void confopt() throws InterruptedException {
			boolean activedvc = configure.OrderedDevice();
			boolean settingopt = configure.ConfigurationSetting();
			if(activedvc == settingopt) {
				configure.confbtn();
			}
		}
		@Then("select Add option")
		public void addopt() throws InterruptedException {
			configure.Addopt();
			Thread.sleep(1000);
		}
		@And("entered {string} and {string}")
		public void timing(String starttime, String endtime) throws InterruptedException{
			configure.configurationtime(starttime, endtime);
			Thread.sleep(1000);
		}
		@Then("selected Add option")
		public void saveadd() throws InterruptedException, IOException {
			configure.Saveadd();
			Thread.sleep(1000);
			configure.TakeScreenshot();
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
		
		@And("user clicked configuration setting option")
		public void confsettingopt() throws InterruptedException {
			boolean activedvc = configure.OrderedDevice();
			boolean confsetbtn = configure.ConfigurationSetting();
			if(activedvc == confsetbtn) {
				configure.confbtn();
			}
		}
		@Then("user clicked Add button")
		public void addbtn() {
			configure.Addopt();
		}
		@And("user not entered {string} and {string}")
		public void timenull(String starttime, String endtime) {
			configure.configurationtime(starttime, endtime);
		}
		@Then("select Device status Enable")
		public void settingstatus() throws InterruptedException {
			configure.Confstatus();
		}
		@And("user click Save button")
		public void savebtn() throws InterruptedException, IOException {
			configure.Saveadd();
			Thread.sleep(1000);
			configure.TakeScreenshot();
		}
		@Then("It should throw mandatory exception start time and end time are required")
		public void blanktimeexp() throws InterruptedException {
			String starttimeblank = "Start Time is required.";
			String endtimebalnk = "End Time is required.";
			String displayedstartimeexp = configure.startimeblank();
			String displayedendtimeexp = configure.endtimebalnk();
			if(starttimeblank.equalsIgnoreCase(displayedstartimeexp) && endtimebalnk.equalsIgnoreCase(displayedendtimeexp)) {
				System.out.println(displayedstartimeexp);
				System.out.println(displayedendtimeexp);
				Thread.sleep(1000);
			}
		}
//		*******************************************************************************
//		@Disableconfiguration

		@And("Configuration settings button clicked")
		public void settingbtn() throws InterruptedException {
			boolean activedvc = configure.OrderedDevice();
			boolean confbtn = configure.ConfigurationSetting();
			if(activedvc == confbtn) {
				configure.confbtn();
				Thread.sleep(1000);
			}
		}
		@Then("user click Add button")
		public void addbutton() {
			configure.Addopt();
		}
		@And("user provide {string} and {string}")
		public void startendtime(String starttime, String endtime) throws InterruptedException {
			configure.configurationtime(starttime, endtime);
			Thread.sleep(2000);
		}
		@Then("select configuration status Disable")
		public void statusopt() throws InterruptedException, IOException {
			configure.Disableconfstatus();
				System.out.println("Disable Option selected");
				Thread.sleep(1000);
				configure.TakeScreenshot();
			}
		@And("user clicked Save button")
		public void settingsbtn() {
			configure.Saveadd();
		}
		@Then("configured time saved in Device Configuration page")
		public void DvcConfpage() throws InterruptedException, IOException {
			String Dvcconf = configure.Disableadd();
			if(Dvcconf.equalsIgnoreCase("Disabled")) {
				System.out.println(Dvcconf);
				Thread.sleep(1000);
				configure.TakeScreenshot();
			}
		}
		@And("user redirected back to main dashboard")
		public void maindashboard() throws InterruptedException {
			String expectedurl = "https://appliedsni01/alert-dashboard";
			for(String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
				String currenturl = driver.getCurrentUrl();
				if(expectedurl.equalsIgnoreCase(currenturl)) {
					System.out.println("User navigated to main dashboard");
					Thread.sleep(1000);
				}
			}
		}
}
