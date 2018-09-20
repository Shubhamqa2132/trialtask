package testproject.qa.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


import testproject.qa.po.widget.FileUpload;
import testproject.qa.utils.WaitTime;

public class CustodianPage extends BasePage{
	
	@FindBy(xpath = "//tr/td/div/input")
	private WebElement custodianEamilAddr;

	@FindBy(xpath = "(//tr/td/input)[1]")
	private WebElement name;
	
	
	
	@FindBy(xpath = "(//tr/td/input)[2]")
	private WebElement alsoNotify;
	
	
	@FindBy(xpath = "(//tr/td/button)[2]")
	private WebElement saveBttn;
	
	
	@FindBy(xpath = "//ul[@class='nav nav-tabs']/li[2]")
	private WebElement clickQuestionaire;
	
	@FindBy(xpath = "//ul[@class='nav nav-tabs']/li[4]")
	private WebElement clickFiles;
	
	
			
	@FindBy(xpath = "//button[contains(.,'Upload Files')]")
	private WebElement uploadFiles;		
	
	
	
	@FindBy(xpath = "//a[contains(.,'Document collection')]")
	private WebElement selectTemplate;
	
	@FindBy(xpath = "//span[text()='Apply template']")
	private WebElement applyTemplate;
	
	@FindBy(xpath = "//input[@name='uploadType'][@value='files']/following-sibling::i")
	private WebElement fileuploadType;
	
	@FindBy(xpath = "//h4[contains(.,'Upload files to legal hold')]")
	private WebElement fileuploadModalHeader;
	
	@FindBy(xpath = "//select[@name='documentType']")
	private WebElement documentType;
	
	
	@FindBy(xpath = "//div[contains(@class,'modal-footer')]/button[contains(.,'Upload')]")
	private WebElement uploadBtn;
	
	@FindBy(xpath = "//div[contains(.,'Common')]/i[contains(@class,'fa-plus')]")
	private WebElement expandCommonDirectory;
	
	@FindBy(xpath = "//div[@class='files-tree-item ng-scope']/i/following-sibling::span")
	private WebElement UploadedLegalCourtFile;
	
	@FindBy(xpath = "//div[@class='modal-footer']/button[contains(.,'Activate')]")
	private WebElement clickPopupActivate;

	@FindBy(xpath = "//span/button")
	private WebElement clickActivate;
	
	@FindBy(xpath = "(//div[@class='panel-heading']/div/button)[2]")
	 private WebElement clickCreateNewTag;

	@FindBy(xpath = "//h4[contains(.,'Create Tag')]/parent::div/following-sibling::div//div/input")
	 private WebElement addTagName;

	@FindBy(xpath = "(//div[@class='form-group'])[10]/div/button[1]")
	 private WebElement clickColor;

	@FindBy(xpath = " //div[@class='modal-footer']/button[contains(.,'Save Tag')]")
	private WebElement saveTagBttn;
	
	@FindBy(xpath = "//button[contains(.,'Submit for approval')]")
	 private WebElement submitApprovalBtn;
	
	@FindBy(xpath = "//div[@class='modal-footer']/button[contains(.,'Submit')]")
	 private WebElement submitBtn;
	
	
	
	
	
	public CustodianPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
		}
	
	public FileUpload fileUpload() {
		return new FileUpload(driver);
	}

	@Override
	public BasePage waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}
	public void enterEmailAddr(String str) {
		custodianEamilAddr.sendKeys(str);
		Reporter.log("<br>enterEmailAddr::"+str, true);
		
	}
	public void enterName(String str) {
		name.sendKeys("Master"+str);
		Reporter.log("<br>enterName::"+str, true);
		
	}
	
	public void enterAlsoNotify(String str) {
		alsoNotify.sendKeys(str);
		Reporter.log("<br>enterAlsoNotify::"+str, true);
		
	}
	
	public void clickSaveBttn() {
		saveBttn.click();
		Reporter.log("<br>clickSaveBttn::", true);
		
	}
	
	public void clickQuestionaire() {
		clickQuestionaire.click();
		Reporter.log("<br>clickQuestionaire::", true);
		
	}
	
	public void clickApplyTemplate() {
		applyTemplate.click();
	    selectTemplate.click();
		Reporter.log("<br>clickApplyTemplate::", true);
		
	}
	
	public void clickFiles() {
		clickFiles.click();
		Reporter.log("<br>clickFiles::", true);
		
	}
	public void clickUploadFiles() {
		uploadFiles.click();
		Reporter.log("<br>clickuploadFiles::", true);
		
	}

	public void selectFilesUploadType() {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(fileuploadModalHeader));
		fileuploadType.click();
		Reporter.log("<br>selectFilesUploadType::", true);
	}
	
	public void selectDocumentType(String str) {
		new Select(documentType).selectByVisibleText(str);
		Reporter.log("<br>selectDocumentType::"+str, true);
	}

	public void clickUploadBtn() {
		
		uploadBtn.click();
		Reporter.log("<br>clickUploadBtn::", true);
		// TODO Auto-generated method stub
		
	}
	
	public void expandCommonDirectory() {
		expandCommonDirectory.click();
		Reporter.log("<br>expandCommonDirectory::", true);
	}
	
	public String getUploadedLegalCourtFileName() {
		String str =UploadedLegalCourtFile.getText();
		Reporter.log("<br>getUploadedLegalCourtFileName::"+str, true);
		return str;
	}
	
	public void clickActivate(){
		  clickActivate.click();
		  Reporter.log("<br>clickActivate", true);
		  
    }
	
	public void clickPopActivate(){
		  clickPopupActivate.click();
		  Reporter.log("<br>clickPopActivate", true);
		  
    }
	
	public void createNewTag(String str) throws InterruptedException{
		  clickAddTag();		  
		  enterTagName(str);
		  selectColor();
		  clickTagSave();
		  Reporter.log("<br>createNewTag::", true);
	}
	
	public void clickAddTag(){
		  clickCreateNewTag.click();
		  Reporter.log("<br>clickAddTag", true);
		  
	}

	public void enterTagName(String str) throws InterruptedException {
		  WebDriverWait wait = new WebDriverWait(driver, WaitTime.LONG_TO);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(.,'Create Tag')]")));
		  //Thread.sleep(5000);
		  addTagName.sendKeys(str);
		  Reporter.log("<br>enterTagName::"+str, true);
	}

	public void selectColor() throws InterruptedException {
		  clickColor.click();
		  Reporter.log("<br>selectColor::", true);
	}
    
	public void clickTagSave(){ 
		   saveTagBttn.click();
		   Reporter.log("<br>clickTagSave::", true);
    }

	public void clickSubmitForApproval() {
		submitApprovalBtn.click();
		Reporter.log("<br>clickSubmitForApproval::", true);
	}	
	
	public void clickSubmit() {
		submitBtn.click();
		Reporter.log("<br>clickSubmit::", true);
	}
   
	
}
