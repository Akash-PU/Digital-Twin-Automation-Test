package WebElements;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.Scenario;

public class ConfigurationWebElements {

	WebDriver driver;
	Date currentdate = new Date();
	
	@FindBy(xpath = "//span[text()='🖥️ Device List']")
	WebElement Dlistopt;
	
	@FindBy(xpath = "//span[text()='Device List']")
	WebElement Dlist;
	
	@FindBy(xpath = "/html//table[contains(@class,'devicelist-table')]/tbody")
	WebElement Devices;
	
	@FindBy(xpath = "//button[@id='settingheader']")
	WebElement ConfgOpt;

	@FindBy(xpath = "//div//h3[@class='title']")
	WebElement ConfgDvcname;

	@FindBy(xpath = "//button[@class='add-btn']")
	WebElement Addopt;
	
	@FindBy(xpath = "//*[@id=\"start_time\"]")
	WebElement Confstarttime;
	
	@FindBy(xpath = "//*[@id=\"end_time\"]")
	WebElement Confendtime;
	
	@FindBy(xpath = "//*[@id=\"status\"]")
	WebElement Confstatus;
	
	@FindBy(xpath = "//button[@class='btn add']")
	WebElement SaveAdd;
	
	@FindBy(xpath = "//span[@class='status enabled']")
	WebElement Confadded;
	
	@FindBy(xpath = "//div[text()='End Time must be greater than Start Time.']")
	WebElement morestartime;
	
	@FindBy(xpath = "//div[text()='Invalid time format (HH:MM:SS).']")
	WebElement startexp;
	
	@FindBy(xpath = "//div[text()='Invalid time format (HH:MM:SS).']")
	WebElement endexp;
	
	@FindBy(xpath = "//div[text()='Please select a Status.']")
	WebElement statusexp;
	
	@FindBy(xpath = "//div[text()='Start Time is required.']")
	WebElement starttimeblank;
	
	@FindBy(xpath = "//div[text()='End Time is required.']")
	WebElement endtimeblank;
	
	@FindBy(xpath = "//span[@class='status disabled']")
	WebElement disableadded;
	
	public ConfigurationWebElements(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void Dlistopt() {
		Dlistopt.click();
	}
	public void Devicelistpage() {
		System.out.println("User at " + Dlist.getText() + " Page");
	}
	public String Dlist() {
		System.out.println("List of Devices: ");
		List<WebElement> devices = Devices.findElements(By.tagName("tr"));
		for(int r=1; r<=devices.size(); r++) {
			String devicename = Devices.findElement(By.xpath("./tr["+ r +"]/td[1]")).getText();
			System.out.println(devicename);
		}
		String NofDevices = "Number of Devices: " + Integer.toString(devices.size());
		return NofDevices;
	}
	public boolean ConfigurationSetting() {
		return ConfgOpt.isEnabled();
	}
	public void confbtn() {
		ConfgOpt.click();
	}
	public void ConfgOpt() {
		ConfgOpt.click();
	}
	public String ConfgDvcname() {
		return ConfgDvcname.getText();
	}
	public void Addopt() {
		Addopt.click();
	}
	public void configurationtime(String starttime, String endtime) {
		Confstarttime.sendKeys(starttime);
		Confendtime.sendKeys(endtime);
	}
	public void Confstarttime(String starttime) {
		Confstarttime.sendKeys(starttime);
	}
	public void Confendtime(String endtime) {
		Confendtime.sendKeys(endtime);
	}
	public boolean activedevice() throws InterruptedException{
		List<WebElement> rows = Devices.findElements(By.tagName("tr"));
		for(int r=1; r<=rows.size(); r++) {
			String ActiveDevice = Devices.findElement(By.xpath("./tr["+ r +"]/td[3]")).getText();
			Thread.sleep(1000);
			if(ActiveDevice.equalsIgnoreCase("Active")) {
				return true;
			}
		}
		return false;
	}
	public boolean OrderedDevice(String DeviceName) throws InterruptedException {
		List<WebElement> rows = Devices.findElements(By.tagName("tr"));
		for(WebElement row : rows) {
			String nameofdevice = row.findElement(By.xpath("./td[1]")).getText();
			if(DeviceName.equalsIgnoreCase(nameofdevice)){
	           String activeStatusCell = row.findElement(By.xpath("./td[3]")).getText();
			   System.out.println("Found Device: " + nameofdevice + ", Status: " + activeStatusCell);
			   if(activeStatusCell.equalsIgnoreCase("Active")) {
				   System.out.println("Device " + nameofdevice + " is Active");
				   return true;
			   } else {
				   System.out.println("Device " + nameofdevice + " is not Active");
				   return false;
			   }
			}
		}
		System.out.println("Device " + DeviceName + " is not found");
		return false;
	}
	public boolean Confstatus(String Status) throws InterruptedException  {
		Confstatus.click();
		Select selectobject = new Select(Confstatus);
		List<WebElement> Statustype = selectobject.getOptions();
		Thread.sleep(1000);
		for(WebElement options : Statustype) {
			String status = options.getText();
			if(status.equalsIgnoreCase(Status)) {
				options.click();
				return true;
			}
		}
		return false;
	}
	public void Disableconfstatus() throws InterruptedException  {
		Confstatus.click();
		Select selectobject = new Select(Confstatus);
		List<WebElement> Statustype = selectobject.getOptions();
		Thread.sleep(1000);
		
		for(WebElement options : Statustype) {
			String status = options.getText();
			
			if(status.equalsIgnoreCase("Disabled")) {
				options.click();
			}
		}
	}
	public void Saveadd() {
		SaveAdd.click();
	}
	public String Confadded() {
		return Confadded.getText();
	}
	
	public String morestartime() {
		return morestartime.getText();
	}
	
	public String startimeformatexp() {
		return startexp.getText();
	}
	public String endtimeformatexp() {
		return endexp.getText();
	}
	public String statusexp() {
		return statusexp.getText();
	}
	public String startimeblank() {
		return starttimeblank.getText();
	}
	public String endtimebalnk() {
		return endtimeblank.getText();
	}
	public String Disableadd() {
		return disableadded.getText();
	}

	public void TakeScreenshot(WebDriver driver, Scenario scenario, String stepName) throws IOException {
		byte[] screenshot =((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
         scenario.attach(screenshot, "image/png", stepName);
	}
}
