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
import org.testng.Assert;
import org.testng.Reporter;



/**
 * @author shubham
 *
 */
public class ForgotPassword extends BasePage {

	private WebDriver driver;

	@FindBy(xpath = "//input[@name='user']")
	private WebElement userName;
	
	@FindBy(xpath = "//button[contains(text(), 'Request Password Reset')]")
	private WebElement requestPasswordReset;
	
	@FindBy(xpath = "//div[@class='panel-heading']")
	private WebElement resetPasswordPageHeader;
	
	@FindBy(xpath = "//input[@name='NewPassword']")
	private WebElement newPassword;
	
	@FindBy(xpath = "//input[@name='ConfirmPassword']")
	private WebElement confirmPassword;
	
	@FindBy(xpath = "//button[contains(text(), 'Change Password')]")
	private WebElement changePasswordButton;
	


	public ForgotPassword(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickRequestPasswordReset(){
		requestPasswordReset.click();
		Reporter.log("<br>requestingResetLink");
	}

	public void enterUserName(String str)
	{
		userName.sendKeys(str);
		Reporter.log("<br>enterUserName", true);
	}
	
	public void verifyResetPassword()
	{
		String header=resetPasswordPageHeader.getText();
		Assert.assertEquals(header, "Resetting Password Shubham", "Header text did not match");
		Reporter.log("<br>resetPasswordPageOpened", true);
	}
	
	public void enterNewPassword()
	{
		newPassword.sendKeys("test@123");
	}
	
	public void enterConfirmPassword()
	{
		confirmPassword.sendKeys("test@123");
	}
	
	public void clickChangePassword()
	{
		changePasswordButton.click();
		Reporter.log("<br>clickingChangePassword", true);
	}
	
	/*
	public void enterPassword(String str)
	{
		password.sendKeys(str);
		Reporter.log("<br>enterPassword", true);
	}
	
	public DashboardPage clickLoginBtn() {
		loginButton.click();
		Reporter.log("<br>clickLogin", true);
		return new DashboardPage(driver);
	}
*/

	@Override
	public ForgotPassword waitForPage() {
		//this.waitForBlueLine(WaitTime.MEDIUM_TO);
		return this;
	}

}
