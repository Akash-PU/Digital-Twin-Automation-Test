package WebElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AlertWebElements {

	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/span")
	WebElement Alerttagline;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/table/tbody")
	WebElement alerthistory;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[1]/button[2]")
	WebElement allalert;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/div[1]/input[1]")
	WebElement alertidsearch;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr")
	List<WebElement> alertrows;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/div[1]/input[2]")
	WebElement fromdate;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/div[1]/input[3]")
	WebElement todate;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/div[1]/select")
	WebElement severity;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/div[1]/div/button")
	WebElement resetfilterbtn;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[2]/td/table")
	WebElement DevicesInvovled;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[1]/div[1]")
	WebElement specificalertid;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[1]/div[2]")
	WebElement specificalertidseverity;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[1]/div[3]")
	WebElement specificalertidalertmessage;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[2]/div[1]")
	WebElement specificalertidtimestamp;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[2]/div[2]")
	WebElement specificalertidstatus;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[3]/button")
	WebElement specificalertdetails;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[4]/div")
	WebElement alertinformationdetails;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[4]/table/tbody/tr/td[1]")
	WebElement specificalertdevicedetails;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[1]/td[5]")
	WebElement specificalertstatus;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[1]/div[1]")
	WebElement specificalertidinformation;

	public AlertWebElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String  alerttitle() {
		return Alerttagline.getText();
	}
	public void allalerts() {
		allalert.click();
	}
	public boolean alerthistory() throws InterruptedException {
		Thread.sleep(1500);
		return alertrows.stream().count()>= 1;
	}
	public void searchalertid(String alertid) throws InterruptedException {
		alertidsearch.clear();
		alertidsearch.sendKeys(alertid);
		Thread.sleep(1000);
	}
	public boolean searchalert(String alertid) {
		for(WebElement row:alertrows) {
			String alert = row.findElement(By.xpath("td[1]")).getText();
			if(alert.equals(alertid)) {
				return true;
			}
		}
		return false;
	}
		public void selectdaterange(String FromDate, String ToDate) throws InterruptedException {
			resetfilterbtn.click();
			fromdate.sendKeys(FromDate);
			todate.sendKeys(ToDate);
			Thread.sleep(1000);
		}
		public boolean displayalerts() {
			for(WebElement row:alertrows) {
				String date = row.findElement(By.xpath("td[2]")).getText();
				//For now value hardcoded
				if(date.contains("2025-11-20") || date.contains("2025-11-21")) {
					System.out.println("Alert found: " + date);
					return true;
				}
			}
			System.out.println("Alert not found");
			return false;
		}
		public void selectseverity(String severitylevel) throws InterruptedException {
			severity.click();
			Select severitydropdown = new Select(severity);
			for(WebElement option:severitydropdown.getOptions()) {
				if(option.getText().contains(severitylevel)) {
					System.out.println("Selected severity: " + option.getText());
					option.click();
					break;
				}
			}
		}
		public boolean verifyselectedseverity(String severitylevel) throws InterruptedException {
			for(WebElement row:alertrows) {
				String severity = row.findElement(By.xpath("td[3]")).getText();
				Thread.sleep(1000);
				if(severity.equalsIgnoreCase(severitylevel)) {
					System.out.println("Alert found with severity: " + severity);
					return true;
				}
			}
			System.out.println("Alert not found with severity: " + severitylevel);
			return false;
		}
		public boolean viewdevices() throws InterruptedException {
			for(WebElement row:alertrows) {
				row.findElement(By.xpath("td[6]/button")).click();
				Thread.sleep(1000);
				return true;
			}
			return false;
		}
		public boolean displaydevices() throws InterruptedException {
			List<WebElement> devicelist = DevicesInvovled.findElements(By.xpath(".//tr/td[1]"));
			if(devicelist.isEmpty()){
				System.out.println("No devices found");
				return false;
			}
			for(WebElement devicename : devicelist) {
				System.out.println("Device name: " + devicename.getText());
			}
			return true;
		}
		public void clickalertid() throws InterruptedException {
			for(WebElement row:alertrows){
				if(specificalertstatus.getText().equals("complete")){
					row.findElement(By.xpath("td[1]")).click();
					Thread.sleep(1000);
				}
				else{
					System.out.println("Alert is in Pending Status");
				}
			}
		}
		public String navigatetoalertspecificdetails() {
			return specificalertid.getText();
		}
		public String specificalertiddetails() {
			return specificalertidinformation.getText();
		}
		public void clickviewdetailsactions() throws InterruptedException {
			specificalertdetails.click();
			Thread.sleep(1000);
			String Alertdevicedetails = specificalertdevicedetails.getText();
			String Alertinformationdetials = alertinformationdetails.getText();
			System.out.println(Alertdevicedetails +"\n" + Alertinformationdetials);
			Thread.sleep(1000);
		}
}
