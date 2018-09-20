package testproject.qa.po;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import junit.framework.Assert;

public class LegalHoldPage extends BasePage{
	
	@FindBy(xpath = "//input[contains(@placeholder,'Search for Legal Hold or Custodian')]")
	private WebElement searchBox;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-primary']")
	private WebElement approveButton; 
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-warning']")
	private WebElement rejectButton;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-danger ng-scope']")
	private WebElement cancelLegalHoldButton;
	
	@FindBy(xpath = "(//button[@class='btn btn-sm btn-primary'])[7]")
	private WebElement ApproveLegalHoldButton;
	
	@FindBy(xpath ="(//input[@class='form-control ng-pristine ng-invalid ng-invalid-required'])[5]")
	private WebElement rejectLegalHoldComment;
	
	@FindBy(xpath ="(//button[@class='btn btn-sm btn-danger'])[5]")
	private WebElement rejectLegalHoldButton;
	
	@FindBy(xpath ="//div[@class='panel-heading']/div[@class='pull-right']/span")
	private WebElement activatedByText;
	
	@FindBy(xpath ="(//button[@class='btn btn-sm btn-danger'])[6]")
	private WebElement endLegalHoldButton;
	
	@FindBy(xpath ="(//button[@class='btn btn-sm btn-danger'])[7]")
	private WebElement yesIAmSureButton;
	
	

	public LegalHoldPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public void searchLegalHold(String str) {
		searchBox.sendKeys(str);
		searchBox.sendKeys(Keys.ENTER);
		Reporter.log("<br>searchLegalHold::"+str, true);
	}
   
	@Override
	public LegalHoldPage waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}

	public void clickLegalHoldName(String caseName) {
		String str = "//strong[contains(.,'"+caseName+"')]";
		driver.findElement(By.xpath(str)).click();
		Reporter.log("<br>clickLegalHoldName::"+str, true);
	}
	
	public void clickApproveButton() {
		approveButton.click();
		Reporter.log("<br>clickApproveButton::", true);
	}
	
	public void clickRejectButton() {
		rejectButton.click();
		Reporter.log("<br>clickRejectButton::", true);
	}
	
	public void clickCancelLegalHoldButton() {
		cancelLegalHoldButton.click();
		Reporter.log("<br>clickCancelLegalHoldButton::", true);
	}
	
	public void clickLegalHoldApproveButton() {
		ApproveLegalHoldButton.click();
		Reporter.log("<br>clickApproveButton::", true);
	}
	
	public void writeRejectComment(String str) {
		rejectLegalHoldComment.sendKeys(str);
		Reporter.log("<br>writeRejectComment::", true);
	}
	
	public void clickLegalHoldRejectButton() {
		rejectLegalHoldButton.click();
		Reporter.log("<br>clickLegalHoldRejectButton::", true);
	}
	
	public String verifyConfirmationText() {
		String str=activatedByText.getText();
		Reporter.log("<br>verifyConfirmationText::"+str, true);
		return str;
	}
	
	public String verifyRejectedByText() {
		String str=activatedByText.getText();
		System.out.println("text is::++"+str);
		Reporter.log("<br>verifyRejectedByText::", true);
		return str;
	}
	
	public void clickEndLegalHoldButton() {
		endLegalHoldButton.click();
		Reporter.log("<br>clickEndLegalHoldButton::", true);
	}
	
	public void clickYesIAmSureButtonButton() {
		yesIAmSureButton.click();
		Reporter.log("<br>clickYesIAmSureButtonButton::", true);
	}
	
	
	

}
