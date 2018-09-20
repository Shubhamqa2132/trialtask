package testproject.qa.po;

import java.awt.AWTException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class RentalApplication extends BasePage{
	//Locators
	@FindBy(id = "desiredStartDate")
	private WebElement desiredSrtDate;
	
	@FindBy(xpath = "(//button/span[contains(.,'30')])[2]")
	private WebElement desireDate;
	
	@FindBy(xpath = "//button/span[contains(.,'OK')]")
	private WebElement okBtn;
	
	@FindBy(id = "firstName")
	private WebElement FName;
	
	@FindBy(id = "lastName")
	private WebElement LName;
	
	@FindBy(id = "email")
	private WebElement Email;
	
	@FindBy(xpath = "//label[contains(.,'Phone number')]/following-sibling::div/input")
	private WebElement Phone;
	
	@FindBy(id = "street")
	private WebElement Street;
	
	@FindBy(id = "number")
	private WebElement StreetNumber;
	
	@FindBy(id = "zipCode")
	private WebElement ZIP;
	
	@FindBy(id = "city")
	private WebElement City;
	
	@FindBy(id = "employerName")
	private WebElement EmployerName;
	
	@FindBy(id = "employerAddress")
	private WebElement EmpAddress;
	
	@FindBy(id = "occupation")
	private WebElement Occupation;
	
	@FindBy(id = "netMonthlyIncome")
	private WebElement MonthlyIncome;
	
	@FindBy(xpath = "//div/span[contains(.,'smoking')]/following-sibling::div/div[contains(.,'No')]")
	private WebElement smokingToggle_No;
	
	@FindBy(xpath = "//div/span[contains(.,'pet')]/following-sibling::div/div[contains(.,'No')]")
	private WebElement petToggle_No;
	
	@FindBy(xpath = "//div/span[contains(.,'delinquent')]/following-sibling::div/div[contains(.,'No')]")
	private WebElement RentToggle_No;
	
	@FindBy(xpath = "//div/span[contains(.,'evicted')]/following-sibling::div/div[contains(.,'No')]")
	private WebElement evictedToggle_No;
	
	@FindBy(xpath = "//canvas")
	private WebElement signature;
	
	@FindBy(id = "password")
	private WebElement Password;
	
	@FindBy(id = "repeatPassword")
	private WebElement ConfirmPassword;
	
	@FindBy(xpath = "(//span[contains(.,'I agree')])/preceding-sibling::span/span/input")
	private WebElement agreementCheck;
	
	@FindBy(xpath = "(//button[contains(.,'Register & Submit rental application')])[1]")
	private WebElement registerBtn;
	
	@FindBy(xpath = "//p/span")
	private WebElement validationError;
	
	@FindBy(xpath = "//div[contains(@class,'ExposeHeaderImage_actionButtonsContainer')]")
	private WebElement exposeBtn;
    
	@FindBy(xpath = "(//span[contains(.,'Login')])[1]")
	private WebElement loginLink;
	

	
	private WebDriver driver;

	public RentalApplication(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterDesireddate() throws InterruptedException  {
		
		desiredSrtDate.click();
		Thread.sleep(5000);
		desireDate.click();
		okBtn.click();
		Thread.sleep(5000);
		Reporter.log("<br>enterDesireddate::", true);
		
	}
    
	public void enterFirstName(String str) {
		FName.sendKeys(str);
		Reporter.log("<br>enterFirstName::" + str, true);
    }

    public void enterLastName(String str) {
		LName.sendKeys(str);
		Reporter.log("<br>enterLastName::" + str, true);
    }
    
    public void enterPhone(String str) {
		Phone.sendKeys(str);
		Phone.sendKeys(Keys.TAB);
		Reporter.log("<br>enterPhone::" + str, true);
    }
    
    public void enterEmail(String str) {
		Email.sendKeys(str);
		Reporter.log("<br>enterEmail::" + str, true);
    }
    
    public void enterStreetName(String str) {
		Street.sendKeys(str);
		Reporter.log("<br>enterStreetName::" + str, true);
    }
    
    public void enterStreetNumber(String str) {
		StreetNumber.sendKeys(str);
		Reporter.log("<br>enterStreetNumber::" + str, true);
    }
    
    public void enterZipCode(String str) {
		ZIP.sendKeys(str);
		Reporter.log("<br>enterZipCode::" + str, true);
    }
    
    public void enterCity(String str) {
		City.sendKeys(str);
		Reporter.log("<br>enterCity::" + str, true);
    }
    
    public void enterEmployername(String str) {
    	EmployerName.sendKeys(str);
		Reporter.log("<br>enterEmployername::" + str, true);
    }
    
    public void enterEmployerAdd(String str) {
    	EmpAddress.sendKeys(str);
		Reporter.log("<br>enterEmployerAdd::" + str, true);
    }
    
    public void enterOccupation(String str) {
    	Occupation.sendKeys(str);
		Reporter.log("<br>enterOccupation::" + str, true);
    }
    
    public void enterMonthlyIncome(String str) {
		MonthlyIncome.sendKeys(str);
		Reporter.log("<br>enterMonthlyIncome::" + str, true);
    }
    
    public void enterPassword(String str) {
		Password.sendKeys(str);
		Reporter.log("<br>enterPassword::" + str, true);
    }
    
    public void enterConfirmPassword(String str) {
		ConfirmPassword.sendKeys(str);
		Reporter.log("<br>enterConfirmPassword::" + str, true);
    }
    
    public void clickSmokingToggle() {
    	smokingToggle_No.click();
		Reporter.log("<br>clickSmokingToggle::" , true);
    }
    
    public void clickPetToggle() {
    	petToggle_No.click();
		Reporter.log("<br>clickPetToggle::" , true);
    }
    
    public void clickRentToggle() {
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	je.executeScript("arguments[0].scrollIntoView(true);",RentToggle_No);
    	RentToggle_No.click();
		Reporter.log("<br>clickPetToggle::" , true);
    }
    public void clickEvictedToggle() {
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	je.executeScript("arguments[0].scrollIntoView(true);",evictedToggle_No);
    	evictedToggle_No.click();
		Reporter.log("<br>clickEvictedToggle::" , true);
    }
    
    public HomePage clickRegisterBtn() {
    	registerBtn.click();
		Reporter.log("<br>clickRegisterBtn::" , true);
		return new HomePage(driver);
    }
    
    public void selectAgreement() {
    	agreementCheck.click();
		Reporter.log("<br>selectAgreement::" , true);
    }
    
    public void enterSignature() {
    	signature.click();
    	Reporter.log("<br>enterSignature::" , true);
    }
    
    
    
	@Override
	public BasePage waitForPage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValidationError() {
		// TODO Auto-generated method stub
		String str =validationError.getText();
		Reporter.log("<br>enterSignature::" , true);
		return str;
	}
	
	public String getExposeBtnText() {
		// TODO Auto-generated method stub
		String str =exposeBtn.getText();
		Reporter.log("<br>getExposeBtnText::"+str , true);
		return str;
	}
	
	public String getExposeBtnCSS() {
		String str =exposeBtn.getCssValue("font-size");
		Reporter.log("<br>getExposeBtnCSS::"+str , true);
		return str;
	}

	public LoginPage clickLogin() {
		// TODO Auto-generated method stub
		loginLink.click();
		return new LoginPage(driver);
	}

}
