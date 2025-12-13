package WebElements;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.Scenario;

public class AlertWebElements {

	WebDriver driver;
	Date currentdate = new Date();

	@FindBy(xpath = "//span[text()='Alert Monitoring Dashboard']")
	WebElement Alerttagline;

	@FindBy(xpath = "//table[@class='alertuser-table']//tbody")
	WebElement alertlist;

	@FindBy(xpath = "//button[contains(text(), 'All Alerts')]")
	WebElement allalert;

	@FindBy(xpath = "//input[@placeholder='Search by Alert ID']")
	WebElement alertidsearch;

	@FindBy(xpath = "//table[@class = 'alertuser-table']/tbody/tr")
	List<WebElement> alertrows;

	@FindBy(xpath = "//table[@class = 'alertuser-table']/tbody/tr[1]/td[1]")
	WebElement searchedalert;

	@FindBy(xpath = "//div[@class='alertuser-list']")
	List<WebElement> alertlistpage;

	@FindBy(xpath = "(//input[@type='date'])[1]")
	WebElement fromdate;

	@FindBy(xpath = "(//input[@type='date'])[2]")
	WebElement todate;

	@FindBy(xpath = "//div[text()='No records found']")
	WebElement Noalerts;

	@FindBy(xpath = "//select[@class='form-select']")
	WebElement severity;

	@FindBy(xpath = "//button[text()='Reset Filters']")
	WebElement resetfilterbtn;

	@FindBy(xpath = "//table[@class='device-table']//tbody")
	WebElement DevicesInvovled;

	@FindBy(xpath = "//table[@class = 'alertuser-table']/tbody/tr[1]/td[5]")
	WebElement specificalertstatus;
	 
	@FindBy(xpath = "//div[@class = 'alert-id']")
	WebElement specificalertid;

	@FindBy(xpath = "//div[@class='device-card-header']")
	WebElement specificalertidinformation;

	@FindBy(xpath = "//button[@class='accordion-toggle']")
	WebElement viewdetailsactions;

	@FindBy(xpath = "//div[contains(@class, 'alert-video-container')]//video")
	WebElement playablevideossection;

	@FindBy(xpath = "//table[@class='device-tables']//tbody/tr/td[1]")
	WebElement specificalertdevicedetails;

	@FindBy(xpath = "//div[@class='inference-breakdown']")
	WebElement alertinformationdetails;

	public AlertWebElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String alerttitle() {
		return Alerttagline.getText();
	}

	public void allalerts() {
		allalert.click();
	}

	public boolean alerthistory() throws InterruptedException {
		Thread.sleep(1500);
		return alertrows.stream().count() >= 1;
	}

	public void searchalertid(String alertid) throws InterruptedException {
		alertidsearch.clear();
		alertidsearch.sendKeys(alertid);
		Thread.sleep(1000);
	}

	public boolean searchalert(String alertid) {
		String searchelement = driver.getPageSource();
		if (searchelement.contains("No records found")) {
			System.out.println("No Alert found - Please enter valid Alert ID");
			return false;
		} else {
			String alert = searchedalert.getText();
			if (alert.contains(alertid)) {
				System.out.println("Alert found with ID: " + alert);
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
		String daterangealerts = driver.getPageSource();
		if (daterangealerts.contains("No records found")) {
			System.out.println("No Alerts found for the selected date range");
		} else {
			List<WebElement> alertcount = alertlist.findElements(By.xpath("tr"));
			int Numberofalerts = alertcount.size();
			if (Numberofalerts > 0) {
				System.out.println("Number of Alerts found for the selected date range: " + Numberofalerts);
				return true;
			}
		}
		return false;
	}

	public void selectseverity(String severitylevel) throws InterruptedException {
		severity.click();
		Select severitydropdown = new Select(severity);
		for (WebElement option : severitydropdown.getOptions()) {
			if (option.getText().contains(severitylevel)) {
				option.click();
				System.out.println("Selected severity: " + option.getText());
				break;
			}
		}
	}

	public boolean verifyselectedseverity(String severitylevel) throws InterruptedException {
		String selectedseverity = driver.getPageSource();
		if (selectedseverity.contains("No records found")) {
			System.out.println("No Alerts found with selected severity: " + severitylevel);
			return true;
		} else {
			for (WebElement row : alertrows) {
				String severity = row.findElement(By.xpath("td[3]")).getText();
				Thread.sleep(1000);
				if (severity.equalsIgnoreCase(severitylevel)) {
					System.out.println("Alert found with severity: " + severity);
					return true;
				}
			}
		}
		return false;
	}

	public boolean viewdevices() throws InterruptedException {
		for (WebElement row : alertrows) {
			row.findElement(By.xpath("td[6]/button")).click();
			Thread.sleep(1000);
			return true;
		}
		return false;
	}

	public boolean displaydevices() throws InterruptedException {
		List<WebElement> devicelist = DevicesInvovled.findElements(By.xpath(".//tr/td[1]"));
		if (devicelist.isEmpty()) {
			System.out.println("No devices found");
			return false;
		}
		for (WebElement devicename : devicelist) {
			System.out.println("Device name: " + devicename.getText());
		}
		return true;
	}

	public void clickalertid() throws InterruptedException {
			if (specificalertstatus.getText().equals("complete")) {
				searchedalert.click();
				Thread.sleep(1000);
			} 
			else {
				System.out.println("Alert is pending status");
				throw new AssertionError("Alert is in Pending Status");
			}
		}

	public String navigatetoalertspecificdetails() {
		return specificalertid.getText();
	}

	public String specificalertiddetails() {
		return specificalertidinformation.getText();
	}

	public void clickviewdetailsactions() throws InterruptedException {
		String src = playablevideossection.getAttribute("src");
		if (src != null && (src.contains("media") || src.contains("alert_"))) {
			viewdetailsactions.click();
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", alertinformationdetails);
			String Alertdevicedetails = specificalertdevicedetails.getText();
			String Alertinformationdetials = alertinformationdetails.getText();
			System.out.println(Alertdevicedetails + "\n" + Alertinformationdetials);
			Thread.sleep(1000);
		}
	}

	public void Takescreenshot(WebDriver driver, Scenario scenario, String stepName) throws IOException {
		byte[] screenshot =((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
         scenario.attach(screenshot, "image/png", stepName);
	}
}
