package testproject.qa.po;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testproject.qa.utils.WaitTime;

public class HomePage extends BasePage {
    private WebDriver driver;
    
    @FindBy(xpath = "//button/span/span[text()='Submit']")
	private WebElement alertSubmit;
    
    @FindBy(xpath = "//button/span/span[text()='Ok']")
	private WebElement alertOk;
    
    @FindBy(xpath = "//span[contains(.,'Logout')]")
	private WebElement logOut;
    
    @FindBy(xpath = "//h1/span")
	private WebElement homePageHeader;
    
    @FindBy(xpath = "//span[contains(@class,'HeaderDesktopAvatar')]")
	private WebElement profileAvatar;
  
    
    
    
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	

    
	
    public void clickAlertSubmit() {
    	WebDriverWait wait = new WebDriverWait(driver, WaitTime.MEDIUM_TO);
		wait.until(ExpectedConditions.visibilityOf(alertSubmit));
    	alertSubmit.click();
		Reporter.log("<br>clickAlertSubmit::" , true);
    }
    
    public void clickAlertOk() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, WaitTime.MEDIUM_TO);
		wait.until(ExpectedConditions.visibilityOf(alertOk));
    	alertOk.click();
    	Thread.sleep(5000);
		Reporter.log("<br>clickAlertOk::" , true);
    }
    
    public void clickLogout() throws InterruptedException {
    	logOut.click();
    	Thread.sleep(5000);
		Reporter.log("<br>clickLogout::" , true);
    }
    
  
    
    public String getAvatarText() {
    	String str = profileAvatar.getText();
    	Reporter.log("<br>getAvatarText::" +str, true);
    	return str;
    }
    
    public String getHeaderText() {
    	WebDriverWait wait = new WebDriverWait(driver, WaitTime.MEDIUM_TO);
		wait.until(ExpectedConditions.visibilityOf(homePageHeader));
    	String str = homePageHeader.getText();
    	Reporter.log("<br>getHeaderText::" +str, true);
    	return str;
    }
    
    public Boolean isUserLoggedIn() {
    	Boolean flag=false;
    	if(logOut.isDisplayed()) {
    		flag =true;
    	}
    	
    	return flag;
    }

	@Override
	public HomePage waitForPage() {
		// TODO Auto-generated method stub
		return this;
	}


	

}
