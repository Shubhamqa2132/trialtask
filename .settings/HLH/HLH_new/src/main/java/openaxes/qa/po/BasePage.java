/**
 * 
 */
package testproject.qa.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author shubham
 **/
public abstract class BasePage {
	
	protected WebDriver driver;
	
	
	public BasePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		
	public abstract BasePage waitForPage();
	
	
}
