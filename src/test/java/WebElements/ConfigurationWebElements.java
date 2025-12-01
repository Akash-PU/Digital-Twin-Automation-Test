package WebElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ConfigurationWebElements {

	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/ul/li[3]/span")
	WebElement Dlistopt;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/span")
	WebElement Dlist;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/table/tbody/tr[1]/td[3]/span")
	WebElement DvcActive;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/table/tbody")
	WebElement Devices;
	
	@FindBy(xpath = "//*[@id=\"settingheader\"]")
	WebElement ConfgOpt;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[1]/button")
	WebElement Addopt;
	
	@FindBy(xpath = "//*[@id=\"start_time\"]")
	WebElement Confstarttime;
	
	@FindBy(xpath = "//*[@id=\"end_time\"]")
	WebElement Confendtime;
	
	@FindBy(xpath = "//*[@id=\"status\"]")
	WebElement Confstatus;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[5]/button[1]")
	WebElement SaveAdd;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/table/tbody/tr/td[3]/span")
	WebElement Confadded;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[3]/div")
	WebElement morestartime;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[2]/div")
	WebElement startexp;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[3]/div")
	WebElement endexp;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[4]/div")
	WebElement statusexp;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[2]/div")
	WebElement starttimeblank;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[3]/div")
	WebElement endtimeblank;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/table/tbody/tr[2]/td[3]/span")
	WebElement disableadded;
	
	public ConfigurationWebElements(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void Dlistopt() {
		Dlistopt.click();
	}
	public String Dlist() {
		return Dlist.getText();
	}
	public String DvcActive() {
		return DvcActive.getText();
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
	public boolean OrderedDevice() throws InterruptedException {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/table/tbody/tr"));
		for(int r=1; r<=rows.size(); r++) {
			String ActiveDevice = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/table/tbody/tr["+ r +"]/td[3]")).getText();
			Thread.sleep(1000);
			if(ActiveDevice.equalsIgnoreCase("Active")) {
				return true;
			}
		}
		return false;
	}
	public void Confstatus() throws InterruptedException  {
		Confstatus.click();
		Select selectobject = new Select(Confstatus);
		List<WebElement> Statustype = selectobject.getOptions();
		Thread.sleep(1000);
		
		for(WebElement options : Statustype) {
			String status = options.getText();
			
			if(status.equalsIgnoreCase("Enabled")) {
				options.click();
			}
		}
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
}
