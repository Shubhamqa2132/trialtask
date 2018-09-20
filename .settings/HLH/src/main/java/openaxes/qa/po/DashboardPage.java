package testproject.qa.po;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class DashboardPage extends BasePage{
	@FindBy(xpath = "//a[contains(.,'My Legal Holds')]")
	private WebElement headerlink;
	
	@FindBy(xpath = "//label[contains(.,'Legal Holds')]")
	private WebElement legalHoldlink;
	
	@FindBy(xpath = "//ul[@class='nav navbar-nav ng-scope']/li[2]")
	private WebElement Users;
	
	@FindBy(xpath = "//a[contains(.,'Config')]")
	private WebElement configlink;
	
	@FindBy(xpath = "//a[contains(.,'Categories')]")
	private WebElement categorylink;
	
	@FindBy(xpath = "//button[contains(.,'Create Legal Hold')]")
	private WebElement createLegalHoldBtn;
	
	@FindBy(xpath = "//h4[contains(.,'Create Legal Hold')]")
	private WebElement modalHeader;
	
	@FindBy(xpath = "//label[contains(.,'Category')]/following-sibling::select")
	private WebElement selectCategory;
	
	@FindBy(xpath = "//label[contains(.,'Case Name')]/following-sibling::input")
	private WebElement caseName;
	
	@FindBy(xpath = "//label[contains(.,'File Date')]/following-sibling::input")
	private WebElement fillDate;
	
	@FindBy(xpath = "//label[contains(.,'Description')]/following-sibling::textarea")
	private WebElement description;
	
	@FindBy(xpath = "(//div[@class='modal-footer']//i)[2]")
	private WebElement createButton;
	
	@FindBy(xpath = "//a[contains(.,'Tags')]")
	 private WebElement tagLink;
	
	public DashboardPage(WebDriver driver) {
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
	
	public UsersPage clickUsers() {
		Users.click();
		Reporter.log("<br>clicking Users in Header");
		return new UsersPage(driver);
	}
	
	public void clickConfig() {
		configlink.click();
		Reporter.log("<br>clickConfig::", true);
	}
	
	public CategoryPage clickCategory() {
		categorylink.click();
		Reporter.log("<br>clickCategory::", true);
		return new CategoryPage(driver);
	}
	
	public CategoryPage clickCreateLegalHold() {
		createLegalHoldBtn.click();
		Reporter.log("<br>clickCreateLegalHold::", true);
		return new CategoryPage(driver);
	}
	public CategoryPage selectCatogryLegalHold(String str) {
	//	selectCategory.click();		
		Select category = new Select(selectCategory);
		System.out.println(category);	
		category.selectByVisibleText(str);;
	    Reporter.log("<br>selectCategoryLegalHold::"+str, true);
		return new CategoryPage(driver);
	}
	
	
	public CategoryPage enterCasename(String str) {
		caseName.sendKeys(str);
		Reporter.log("<br>enterCasename::"+str, true);
		return new CategoryPage(driver);
	}
	   
	public CategoryPage enterDate(String date) {
		fillDate.sendKeys(date);
		Reporter.log("<br>enterDate::"+date, true);
		return new CategoryPage(driver);
	}
	
	public CategoryPage enterDiscription(String str) {
		description.sendKeys("kdsjbcosacdsakjbdcisajbdcksajbcdkajbdckajb"+str);
		Reporter.log("<br>enterDiscription", true);
		return new CategoryPage(driver);
	}
	
	
	public CustodianPage clickCreateBttn() {
		createButton.click();
		Reporter.log("<br>enterDiscription", true);
		return new CustodianPage(driver);
	}
	
	public CategoryPage clickTag() {
		  tagLink.click();
		  Reporter.log("<br>clickTag::", true);
		  return new CategoryPage(driver);
		 }
	
	
	public LegalHoldPage clickLegalHold() {
		legalHoldlink.click();
		Reporter.log("<br>clickLegalHold::", true);
		return new LegalHoldPage(driver);
	}

}
