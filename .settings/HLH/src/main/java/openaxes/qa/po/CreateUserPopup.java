package testproject.qa.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CreateUserPopup extends BasePage{
	
	@FindBy(xpath = "//a[contains(.,'My Legal Holds')]")
	private WebElement headerlink;
	
	@FindBy(xpath = "//button[@class='btn btn-success ng-scope']")
	private WebElement createUserButton;
	
	@FindBy(xpath = "//input[@placeholder='Full Name']")
	private WebElement enterUsername;
	
	@FindBy(xpath = "//input[@placeholder='Email address']")
	private WebElement enterEmail;
	
	@FindBy(xpath = "//input[@value='Admin']")
	private WebElement radioButtonITAdministrator;
	
	@FindBy(xpath = "//input[@value='LegalManager']")
	private WebElement radioButtonLegalManager;
	
	@FindBy(xpath = "//input[@value='Standard']")
	private WebElement radioButtonStandard;
	
	@FindBy(xpath = "//input[@value='Requestor']")
	private WebElement radioButtonRequestor;
	
	@FindBy(xpath = "//input[@value='Custodian']")
	private WebElement radioButtonCustodian;
	
	@FindBy(xpath = "//input[@name='oa-user-active']")
	private WebElement activeUserCheckbox;
	
	
	

	
	private WebDriver driver;
	
	public CreateUserPopup(WebDriver driver) {
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
	
	public void enterName(){
		enterUsername.sendKeys("Test ITAdmin");
	}
	
	public void enterEnterEmailAddress(String Email){
		enterEmail.sendKeys(Email);
	}
	
	public void clickITAdministrator(){
		radioButtonITAdministrator.click();
		Reporter.log("<br>selectingITAdministratorRadioButton");
	}
	
	public void clickLegalManager(){
		radioButtonLegalManager.click();
		Reporter.log("<br>selectingLegalManagerRadioButton");
	}
	
	public void clickStandard(){
		radioButtonStandard.click();
		Reporter.log("<br>selectingStandardRadioButton");
	}
	
	public void clickRequestor(){
		radioButtonRequestor.click();
		Reporter.log("<br>selectingRequestorRadioButton");
	}
	
	public void clickCustodian(){
		radioButtonCustodian.click();
		Reporter.log("<br>selectingCustodianRadioButton");
	}
	
	public void createUserButton(){
		createUserButton.click();
		Reporter.log("<br>openingCreateUserPopup");
	}
	
	public void setUserActive(){
		activeUserCheckbox.click();
		Reporter.log("<br>clickingActiveUserCheckbox");
	}
	
}
