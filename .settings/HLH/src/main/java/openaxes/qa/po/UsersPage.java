package testproject.qa.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testproject.qa.utils.WaitTime;

public class UsersPage extends BasePage{
	
	@FindBy(xpath = "//a[contains(.,'My Legal Holds')]")
	private WebElement headerlink;
	
	@FindBy(xpath = "//button[@class='btn btn-sm btn-info']")
	private WebElement createUserButton;
	
	@FindBy(xpath = "(//h4[@class='modal-title'])[1]")
	private WebElement createUserPopUpHeader;
	

	
	private WebDriver driver;
	
	public UsersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
		}

	@Override
	public BasePage waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}

	public String getHeader() {
		String str =headerlink.getText();
		Reporter.log("<br>getHeader::"+str, true);
		return str;
	}
	
	public void clickCreateUser(){
		createUserButton.click();
		Reporter.log("<br>opening CreateUser Popup");
		//return new CreateUserPopup(driver);
	}
	
	public CreateUserPopup switchToCreateUserpopup(){
		WebDriverWait wait = new WebDriverWait(driver, WaitTime.TOO_SHORT_TO);
		wait.until(ExpectedConditions.visibilityOf(createUserPopUpHeader));
		return new CreateUserPopup(driver);
	}
	
	
}
