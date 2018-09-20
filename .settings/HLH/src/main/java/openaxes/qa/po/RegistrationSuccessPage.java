package testproject.qa.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class RegistrationSuccessPage extends BasePage{
	@FindBy(xpath = "//h1")
	private WebElement successMsg;
	
    private WebDriver driver;
	public RegistrationSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getSuccessMsg() {
		String str = successMsg.getText();
		Reporter.log("<br>getSuccessMsg::", true);
		return str;
	}

	@Override
	public RegistrationSuccessPage waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}

}
