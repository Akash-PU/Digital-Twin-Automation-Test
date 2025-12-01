package WebElements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginWebElements {

	WebDriver driver;

	@FindBy(id = "InputUsername")
	WebElement txt_username;

	@FindBy(id = "InputPassword")
	WebElement txt_password;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/form/div[3]/div")
	WebElement btn_Signin;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/button")
	WebElement opt_menu;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/button/i")
	WebElement opt_logout;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/form/div[2]/div[2]")
	WebElement InvalidCredentials;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/form/div[1]/div")
	WebElement emptyusername;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/form/div[2]/div[2]")
	WebElement emptypassword;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/form/div[1]/div")
	WebElement lessuser;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/form/div[2]/div[2]")
	WebElement lesspassword;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/form/div[1]/div")
	WebElement moreuser;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/ul/li[2]/span")
	WebElement deviceorder;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input")
	WebElement devicename;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/select")
	WebElement dropdown;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/button")
	WebElement order;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[1]")
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
	
	public String InvalidCredentials() {
		return InvalidCredentials.getText();
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
	
}
