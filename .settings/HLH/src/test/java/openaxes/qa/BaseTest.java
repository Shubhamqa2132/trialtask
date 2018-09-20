package testproject.qa;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import testproject.qa.utils.MailAPIUtils;
import testproject.qa.utils.XMLUtils;

public abstract class BaseTest {

	private enum DriverType {
		Firefox, IE, Chrome
	}

	public static WebDriver driver;
	public XMLUtils xmlUtils;
	public Map<String, Object> jobDetails;
	public String applyURL;

	private String originalHandle;

	@BeforeClass
	@Parameters({ "driverType", "url" })
	public void beforeMainClass(String driverType, String url)
			throws URISyntaxException {
		
		//Cleaning inbox for faster search.
		new MailAPIUtils().inboxCleanUp();
		
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");

		if (DriverType.Firefox.toString().equals(driverType)) {
			String pathToDriver = new File(this.getClass().getResource("/geckodriver.exe").toURI()).toString();
			System.setProperty("webdriver.firefox.marionette", pathToDriver);
			driver = new FirefoxDriver();
		} else if (DriverType.IE.toString().equals(driverType)) {
			String pathToDriver = new File(this.getClass().getResource("/IEDriverServer.exe").toURI()).toString();
			System.setProperty("webdriver.ie.driver", pathToDriver);
			driver = new InternetExplorerDriver();
		} else if (DriverType.Chrome.toString().equals(driverType)) {
			String pathToDriver = new File(this.getClass().getResource("/chromedriver.exe").toURI()).toString();
			System.out.println("pathToDriver" + pathToDriver);
			System.setProperty("webdriver.chrome.driver", pathToDriver);
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("disable-infobars");
			driver = new ChromeDriver(ops);
		} else {
			String pathToDriver = new File(this.getClass().getResource("/geckodriver.exe").toURI()).toString();
			System.setProperty("webdriver.firefox.marionette", pathToDriver);
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
        
		//driver.get(url);
		applyURL = url;
		originalHandle = driver.getWindowHandle();
		

	}

	@AfterClass
	public void afterMainClass() {
		//driver.quit();
	}

	@BeforeTest
	public void beforeMainTest() {
		// this.beforeTest();
	}

	@AfterTest
	public void afterMainTest() {

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		Reporter.log("<br>****************************************************************************************",
				true);
		Reporter.log("<br>****************************************************************************************",
				true);
		Reporter.log("<br>$$$$$$$$$$$$$$$$$$$        " + method.getName() + "        $$$$$$$$$$$$$$$$$$$$$$$", true);
		Reporter.log("<br>****************************************************************************************",
				true);
		Reporter.log("<br>****************************************************************************************",
				true);
	}

	@AfterMethod
	public void afterMethod(Method method) {
		afterTest();
		Reporter.log("<br>XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX",
				true);
		Reporter.log("<br>", true);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		driver.switchTo().window(originalHandle);
	}
	

	public Map<String, Object> pupulateUniqueData(Map<String, Object> data) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String uid = "";
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char c = alphabet.charAt(random.nextInt(26));
			uid += c;
		}
		for (Entry<String, Object> entry : data.entrySet()) {
			if (entry.getValue().toString().contains("UNIQUE")) {
				data.put(entry.getKey(), entry.getValue().toString().replaceAll("UNIQUE", uid));
			} else {
				data.put(entry.getKey(), entry.getValue().toString());
			}
		}
		return data;
	}

	public static WebDriver getDriver(){
		return driver;
	}

	//public abstract void afterTest();

	public void beforeClass() throws InterruptedException, Exception {
		// TODO Auto-generated method stub
		
	}

	public abstract void afterTest(); 
	

	
	// public abstract void beforeTest();

	// public abstract void afterTest();
}
