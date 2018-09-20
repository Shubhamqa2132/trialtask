package testproject.qa.po.widget;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;


public class FileUpload {
	
	@FindBy(xpath = "//label[contains(.,'Select files to upload')]/following-sibling::div/input")
	private WebElement uploadFile;
	
	@FindBy(xpath = "//button[contains(.,'Select files')]")
	private WebElement selectFiles;	
	
	@FindBy(xpath = "//button[contains(.,'Select folder')]")
	private WebElement selectFolder;	
	
	private WebDriver driver;

	public FileUpload(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public FileUpload upload(String file) throws Exception {
		Thread.sleep(3000);
		selectFiles.click();
		StringSelection ss = new StringSelection(file);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		//uploadFile.sendKeys(file);
		Reporter.log("<br>upload::" + file, true);
		//new WebDriverWait(driver, WaitTime.SHORT_TO).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='uploads_more_stats_bar']/div[1]")));
		//Reporter.log("<br> Resumes uploaded::"+ driver.findElement(By.xpath("//*[@id='uploads_more_stats_bar']/div[1]")).getText(), true);
		return new FileUpload(driver);
	}
	
	public FileUpload uploadFolder(String file) throws Exception {
		selectFolder.click();
		StringSelection ss = new StringSelection(file);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Reporter.log("<br>upload folder::" + file, true);
		return new FileUpload(driver);
	}
}
