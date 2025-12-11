package WebElements;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginWebElements {

	WebDriver driver;
	Date currentdate = new Date();

	@FindBy(id = "InputUsername")
	WebElement txt_username;

	@FindBy(id = "InputPassword")
	WebElement txt_password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btn_Signin;

	@FindBy(xpath = "//button//img[@class='sidebar-toggle-icon']")
	WebElement opt_menu;

	@FindBy(xpath = "//button//i[contains(@class,'bi-box-arrow-right')]")
	WebElement opt_logout;

	@FindBy(xpath = "//div[text()='Invalid Username or Password.']")
	WebElement InvalidCredentials;
	
	@FindBy(xpath = "//div[text()='Username is required']")
	WebElement emptyusername;
	
	@FindBy(xpath = "//div[text()='Password is required']")
	WebElement emptypassword;

	@FindBy(xpath = "//div[text()='Username must be at least 3 characters']")
	WebElement lessuser;
	
	@FindBy(xpath = "//div[text()='Password must be at least 6 characters']")
	WebElement lesspassword;
	
	@FindBy(xpath = "//div[text()='Username must be no more than 20 characters']")
	WebElement moreuser;
	
	@FindBy(xpath = "//span[text()='📦 Device Order']")
	WebElement deviceorder;
	
	@FindBy(xpath = "//input[@class='device-order-input']")
	WebElement devicename;
	
	@FindBy(xpath = "//select[@class='device-order-input']")
	WebElement dropdown;
	
	@FindBy(xpath = "//button[text() ='📥 Submit']")
	WebElement order;
	
	@FindBy(xpath = "//div[@class ='device-order-error']")
	WebElement samename;
	
	@FindBy(css = ".device-order-button")
	WebElement button;
	
	public LoginWebElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void devicetype() throws InterruptedException {
		dropdown.click();
		Select selectobject = new Select(dropdown);
		List<WebElement> Dtype = selectobject.getOptions();
		Thread.sleep(2000);
		for(WebElement options : Dtype) {
			if(options.getText().equalsIgnoreCase("Camera")) {
				options.click();
				System.out.println("Device Type: Camera selected");
			}
		}		
	}

	public void Credentials(String username, String password) {

		txt_username.sendKeys(username);
		txt_password.sendKeys(password);
	}
	public void username(String username) {
		txt_username.sendKeys(username);
	}
	public void password(String password) {
		txt_password.sendKeys(password);
	}
	public void devicename(String DeviceName) {
		devicename.sendKeys(DeviceName);
	}
	public void signin() {
		btn_Signin.click();
	}

	public void menu() {
		opt_menu.click();
	}

	public void logout() {
		opt_logout.click();
	}
	
	public void deviceorder() {
		deviceorder.click();
	}
	
	public void order() {
		order.click();
	}
	
	public String samename() {
		return samename.getText();
	}
	
	public boolean InvalidCredentials() {
		String errormsg = "Invalid Username or Password";
		String pageerrormsg = driver.getPageSource().toString();
		if(pageerrormsg.contains(errormsg)){
			System.out.println("Invalid Username or Password.");
			return true;
		}
		System.out.println("Test Case failed");
		return false;
	}
	
	public String emptyusername() {
		return emptyusername.getText();
	}
	public String emptypassword() {
		return emptypassword.getText();
	}
	public String lessuser() {
		return lessuser.getText();
	}
	public String lesspassword() {
		return lesspassword.getText();
	}
	public String moreuser() {
		return moreuser.getText();
	}
	public String button() {
		return button.getCssValue("opacity");
	}
	
	public void TakeScreenshot() throws IOException {
		String ScreenshotName = currentdate.toString().replace(":", "_").replace(" ", "_") + ".png";
		File TakeScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(TakeScreenshot, new File("target/HtmlReports/Screenshots/Login/" + ScreenshotName));
	}
}
