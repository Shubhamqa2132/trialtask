package testproject.qa.po;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testproject.qa.utils.WaitTime;

public class LitigationHoldNotice extends BasePage {
    
	@FindBy(xpath = "//button[contains(.,'Yes, I Understand')]")
	private WebElement yesBtn;
	
	@FindBy(xpath = "(//div[contains(.,'Did you receive the Litigation Hold Notification')]/following-sibling::div/div/label)[1]")
	private WebElement ques1;
	
	@FindBy(xpath = "//button[contains(.,'Save Answers')]")
	private WebElement saveAnsBtn;
	
	@FindBy(xpath = "//button[contains(.,'Activate account')]")
	private WebElement ActivateBtn;
	
	@FindBy(xpath = "//label[contains(.,'Password')]/following-sibling::input")
	private WebElement password;
	
	@FindBy(xpath = "//label[contains(.,'Confirm')]/following-sibling::input")
	private WebElement confirmPassword;
	
	@FindBy(xpath = "//div[@class='modal-footer']/button[contains(.,'Activate')]")
	private WebElement activateBtn;
	
	
	
	
	public LitigationHoldNotice(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	public void clickUnderstandYesBtn() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", yesBtn);
		yesBtn.click();
		Reporter.log("<br>clickUnderstandYesBtn::", true);
	}
	
	public void addQuestionareAnswer()
	{
		ques1.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", saveAnsBtn);
		saveAnsBtn.click();
		Reporter.log("<br>addQuestionareAnswer::", true);
	}
	
	public void activateAccount() throws InterruptedException
	{   
		Thread.sleep(5000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", ActivateBtn);
		ActivateBtn.click();
		Reporter.log("<br>activateAccount::", true);
		
	}
	
	public void addPasswordDetails(String str) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, WaitTime.LONG_TO);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(.,'Activate Account')]")));
		  password.sendKeys(str);
		  Thread.sleep(3000);
		  confirmPassword.sendKeys(str);
		  activateBtn.click();
		  Reporter.log("<br>addPasswordDetails::", true);
	}

	@Override
	public LitigationHoldNotice waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}

}
