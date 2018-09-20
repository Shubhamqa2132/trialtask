package testproject.qa.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testproject.qa.utils.WaitTime;

public class HomePage extends BasePage {
    private WebDriver driver;
    
    @FindBy(xpath = "//a[contains(.,'LOGIN')]")
	private WebElement loginLink;
    
    @FindBy(xpath = "//a[contains(.,'SIGN UP')]")
	private WebElement signUpLink;
    
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public LoginPage clickLogin() {
		new WebDriverWait(driver, WaitTime.LONG_TO).until(ExpectedConditions.visibilityOf(loginLink));
		loginLink.click();
		Reporter.log("<br>clickLogin", true);
		return new LoginPage(driver);
	}
	
	public SignupPage clickSignUP() {
		signUpLink.click();
		Reporter.log("<br>clickSignup", true);
		return new SignupPage(driver);
	}

	@Override
	public HomePage waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}

}
