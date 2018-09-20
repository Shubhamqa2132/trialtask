package testproject.qa.po;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testproject.qa.utils.WaitTime;

public class SignupPage extends BasePage{
    private WebDriver driver;
    
    @FindBy(xpath = "//label[contains(.,'Company Name')]/following-sibling::input")
	private WebElement compName;
    
    @FindBy(xpath = "//label[text()='Name']/following-sibling::input")
	private WebElement name;
    
    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
	private WebElement email;
    
    @FindBy(xpath = "//label[text()='Company Domain']/following-sibling::input")
	private WebElement CompanyDomain;
    
    @FindBy(xpath = "//label[text()='No of Employees']/following-sibling::select")
	private WebElement employeesNumber;
    
    @FindBy(xpath = "//label[text()='Title']/following-sibling::input")
	private WebElement title;
    
    @FindBy(xpath = "//label[contains(.,'terms and conditions')]/input")
	private WebElement temsConditions;
    
    @FindBy(xpath = "//div[@class='recaptcha-checkbox-borderAnimation']")
	private WebElement captcha;
    
    @FindBy(xpath = "//h4[contains(.,'OPENAXES SOFTWARE LICENSE AGREEMENT')]")
	private WebElement agreement;
  
    @FindBy(xpath = "//div[@class='modal-footer']/button[@class='btn btn-sm btn-success']")
	private WebElement agreeBtn;
    
    @FindBy(xpath = "//button[contains(.,'Register')]")
	private WebElement registerBtn;  
  
  
  
    
	public SignupPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    
	public void enterCompanyName(String str) {
		compName.sendKeys(str);
		Reporter.log("<br>enterCompanyName::"+str, true);
		
	}
	
	public void enterName(String str) {
		name.sendKeys(str);
		Reporter.log("<br>enterName::"+str, true);
		
	}
	
	public void enterEmail(String str) {
		email.sendKeys(str);
		Reporter.log("<br>enterEmail::"+str, true);
		
	}
	
	public void enterTitle(String str) {
		title.sendKeys(str);
		Reporter.log("<br>enterTitle::"+str, true);
		
	}
	
	public void enterCompDomain(String str) {
		CompanyDomain.sendKeys(str);
		Reporter.log("<br>enterCompDomain::"+str, true);
		
	}
	
	public void selectNumberofEmployees(String str) {
		new Select(employeesNumber).selectByValue(str);
		Reporter.log("<br>selectNumberofEmployees::" + str, true);
		
	}
	
	public void checkTermsConditions() {
		temsConditions.click();
		Reporter.log("<br>checkTermsConditions::", true);
	}
	
	public void checkCaptcha() {
		captcha.click();
		Reporter.log("<br>checkCaptcha::", true);
	}
	
	public void acceptTerms() {
		WebDriverWait wait = new WebDriverWait(driver, WaitTime.LONG_TO);
		wait.until(ExpectedConditions.visibilityOf(agreement));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", agreeBtn);
		agreeBtn.click();
		Reporter.log("<br>acceptTerms::", true);
	}
	
	public RegistrationSuccessPage clickRegister() {
		registerBtn.click();
		Reporter.log("<br>clickRegister::", true);
		return new RegistrationSuccessPage(driver);
	}
	
	@Override
	public SignupPage waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}

}
