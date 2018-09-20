package testproject.qa.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testproject.qa.utils.WaitTime;

public class CategoryPage extends BasePage{
    private WebDriver driver;
    
    @FindBy(xpath = "//ul[@class='nav navbar-nav ng-scope']//i[@class='fa fa-eye']")
	private WebElement legalHoldslink;
    
    @FindBy(xpath = "//button[contains(.,'Add Category')]")
	private WebElement addCategoryBtn;
    
    
    @FindBy(xpath = "//h4[contains(.,'Create Category')]")
	private WebElement modalHeader;
  
    @FindBy(xpath = "//label[contains(.,'Name')]/following-sibling::input")
	private WebElement categoryName;
    
    @FindBy(xpath = " (//div[@class='modal-footer']/button)[2]")
	private WebElement saveBtn;
    
    @FindBy(xpath = "//div[@class='modal-footer']/button[contains(.,'Save Tag')]")
    private WebElement saveTagBtn;
   
    @FindBy(xpath = "(//div[@class='form-group'])[3]/div/button")
    private WebElement clickColor;
    
    @FindBy(xpath = "//button[contains(.,'Add Tag')]")
    private WebElement addTagBtn;
       
      
    @FindBy(xpath = " (//div[@class='form-group']//input)[1]")
    private WebElement addTagName;
    
	public CategoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    
	@Override
	public CategoryPage waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public void clickAddCategory() {
		addCategoryBtn.click();
		Reporter.log("<br>clickAddCategory::", true);
	}
	
	public void enterCategoryName(String str) {
		WebDriverWait wait = new WebDriverWait(driver, WaitTime.LONG_TO);
		wait.until(ExpectedConditions.visibilityOf(modalHeader));
		categoryName.sendKeys(str);
		Reporter.log("<br>enterCategoryName::"+str, true);
	}
    
	public void clickSave() {
		saveBtn.click();
		Reporter.log("<br>clickSave::", true);
	}
	
	public void addNewCategory(String str) {
		clickAddCategory();
		enterCategoryName(str);
		clickSave();
		Reporter.log("<br>addNewCategory::"+str, true);
	}
	
	public DashboardPage clicklegalHolds() {
		legalHoldslink.click();
		Reporter.log("<br>clickAddlegalHolds::", true);
		return new DashboardPage(driver);
	}
	
	public void addNewTag(String str) throws InterruptedException{
		  
		  clickAddTag();
		  enterTagName(str);
		  selectColor();
		  clickTagSave();
		  Reporter.log("<br>addNewTagCategory::"+str, true);
	}
	
	public void clickAddTag() throws InterruptedException {
		  Thread.sleep(5000);
		  addTagBtn.click();
		  Reporter.log("<br>addTagBtn::", true);
	 }
	public void enterTagName(String str) throws InterruptedException {
		  //WebDriverWait wait = new WebDriverWait(driver, WaitTime.LONG_TO);
		  //wait.until(ExpectedConditions.visibilityOf(modalHeader));
		  Thread.sleep(5000);
		  addTagName.sendKeys(str);
		  Reporter.log("<br>enterCategoryName::"+str, true);
	}
	
	public void selectColor() throws InterruptedException {
		  clickColor.click();
		  Reporter.log("<br>selectColor::", true);
    }
	
	public void clickTagSave(){ 
		 saveTagBtn.click();
		 Reporter.log("<br>clickTagSave::", true);
    }
}
