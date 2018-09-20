package testproject.qa.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LoginPage extends BasePage{
	@FindBy(xpath = "//button/span/span[text()='Login']")
	private WebElement loginBtn;
	
	@FindBy(id = "username")
	private WebElement Username;
	
	@FindBy(id = "password")
	private WebElement Password;
    
    private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
    super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void enterUserName(String str) {
		Username.sendKeys(str);
		Reporter.log("<br>enterUserName::" + str, true);
    }
	
	public void enterPassword(String str) {
		Password.sendKeys(str);
		Reporter.log("<br>enterPassword::" + str, true);
    }
	
	public HomePage clickLoginBtn() {
    	loginBtn.click();
		Reporter.log("<br>clickLoginBtn::" , true);
		return new HomePage(driver);
    }

	@Override
	public BasePage waitForPage() {
		// TODO Auto-generated method stub
		return null;
	}

		
}
