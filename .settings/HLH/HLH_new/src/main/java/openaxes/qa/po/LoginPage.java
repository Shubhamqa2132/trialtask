/**
 * 
 */
package testproject.qa.po;
import testproject.qa.utils.WaitTime;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;



/**
 * @author shubham
 *
 */
public class LoginPage extends BasePage {

	private WebDriver driver;

	@FindBy(xpath = "//input[@name='username']")
	private WebElement userName;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath = "//a[contains(text(), 'Forgot your password?')]")
	private WebElement ForgotPassword;


	

	@FindBy(xpath = "//button[contains(.,'Log In')]")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickForgotPassword(){
		ForgotPassword.click();
		Reporter.log("<br>openingForgotPasswordPage");
	}

	public void enterUserName(String str)
	{
		userName.sendKeys(str);
		Reporter.log("<br>enterUserName", true);
	}
	
	public void enterPassword(String str)
	{
		password.sendKeys(str);
		Reporter.log("<br>enterPassword", true);
	}
	
	public DashboardPage clickLoginBtn() {
		loginButton.click();
		Reporter.log("<br>clickLoginBtn", true);
		return new DashboardPage(driver);
	}


	@Override
	public LoginPage waitForPage() {
		//this.waitForBlueLine(WaitTime.MEDIUM_TO);
		return this;
	}

}
