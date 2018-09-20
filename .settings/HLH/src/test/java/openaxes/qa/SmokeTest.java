package testproject.qa;

import java.io.File;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import testproject.qa.po.CategoryPage;
import testproject.qa.po.CreateUserPopup;
import testproject.qa.po.CustodianPage;
import testproject.qa.po.DashboardPage;
import testproject.qa.po.ForgotPassword;
import testproject.qa.po.HomePage;
import testproject.qa.po.LegalHoldPage;
import testproject.qa.po.LitigationHoldNotice;
import testproject.qa.po.LoginPage;
import testproject.qa.po.RegistrationSuccessPage;
import testproject.qa.po.SignupPage;
import testproject.qa.po.UsersPage;
import testproject.qa.utils.GeocodeClient;
import testproject.qa.utils.MailAPIUtils;
import testproject.qa.utils.WaitTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

	private LoginPage loginPage;
	private ForgotPassword forgotPassword;
	private DashboardPage dashboardPage;
	private UsersPage userPage;
	private CreateUserPopup createUserPopup;
	private SignupPage signupPage;
	private RegistrationSuccessPage registrationSuccessPage;
	private CategoryPage categoryPage;
	private HomePage homePage;
	private CustodianPage custodianPage;
	private LitigationHoldNotice litigationHoldNotice;
	private LegalHoldPage legalHoldPage;
	
	public void createUserITAdmin() throws Exception {
		String adminITemail= "testitadmin" + System.currentTimeMillis() + "@yopmail.com";
		System.out.println("Admin IT Email ---->" + adminITemail);
		createUserPopup.enterName();
		createUserPopup.enterEnterEmailAddress(adminITemail);
		createUserPopup.clickITAdministrator();
		createUserPopup.setUserActive();
		Thread.sleep(3000);
		createUserPopup.createUserButton();
	}
	
	public void createUserLegalManager() throws Exception {
		String legalManagerEmail= "testlegalmanager" + System.currentTimeMillis() + "@yopmail.com";
		System.out.println("Admin IT Email ---->" + legalManagerEmail);
		createUserPopup.enterName();
		createUserPopup.enterEnterEmailAddress(legalManagerEmail);
		createUserPopup.clickLegalManager();
		createUserPopup.setUserActive();
		Thread.sleep(3000);
		createUserPopup.createUserButton();
	}
	
	public void createUserStandard() throws Exception {
		String standardEmail= "teststandard" + System.currentTimeMillis() + "@yopmail.com";
		System.out.println("Admin IT Email ---->" + standardEmail);
		createUserPopup.enterName();
		createUserPopup.enterEnterEmailAddress(standardEmail);
		createUserPopup.clickStandard();
		createUserPopup.setUserActive();
		Thread.sleep(3000);
		createUserPopup.createUserButton();
	}
	
	public void createUserRequestor() throws Exception {
		String requestorEmail= "testrequestor" + System.currentTimeMillis() + "@yopmail.com";
		System.out.println("Admin IT Email ---->" + requestorEmail);
		createUserPopup.enterName();
		createUserPopup.enterEnterEmailAddress(requestorEmail);
		createUserPopup.clickRequestor();  
		createUserPopup.setUserActive();
		Thread.sleep(3000);
		createUserPopup.createUserButton();
	}
	
	public void createUserCustodian() throws Exception {
		String custodianEmail= "testcustodian" + System.currentTimeMillis() + "@yopmail.com";
		System.out.println("Admin IT Email ---->" + custodianEmail);
		createUserPopup.enterName();
		createUserPopup.enterEnterEmailAddress(custodianEmail);
		createUserPopup.clickCustodian();
		createUserPopup.setUserActive();
		Thread.sleep(3000);
		createUserPopup.createUserButton();
	}
	

	//Verify that User is able to login the application.
	//@Test
	public void loginExistingUser() throws Exception {
		//Map<String, Object> userData = ppulateUniqueData(xmlUtils.getXMLNodeValues("userData"));
		driver.get(applyURL);
		homePage = new HomePage(driver);
		//Thread.sleep(3000);
		loginPage = homePage.clickLogin();
		loginPage.enterUserName("testlegaladmin@yopmail.com");
		loginPage.enterPassword("test@123");
		dashboardPage= loginPage.clickLoginBtn();
		Thread.sleep(5000);
		String actual=dashboardPage.getHeader();
		Assert.assertEquals(actual, "My Legal Holds");
		
	}
	
	
       //Verify that user is able to register on the application.	
	   // //@Test
		public void verifySignUp() throws InterruptedException {
			driver.get(applyURL);
			homePage = new HomePage(driver).waitForPage();
			signupPage =homePage.clickSignUP();
			String compName = "openaxes"+ System.currentTimeMillis();
			signupPage.enterCompanyName(compName);
			signupPage.enterName("testName");
			signupPage.enterCompDomain("openaxextest.com");
			String email = "testopenaxes"+System.currentTimeMillis()+"@gmail.com";
			signupPage.enterEmail(email);
			signupPage.selectNumberofEmployees("1001 - 5000");
			signupPage.enterTitle("Test Title");
			Thread.sleep(8000);
			//signupPage.checkCaptcha();
			signupPage.checkTermsConditions();
			//driver.switchTo().alert().accept();
			signupPage.acceptTerms();
			Thread.sleep(5000);
			registrationSuccessPage =signupPage.clickRegister();
			String actualMsg =registrationSuccessPage.getSuccessMsg();
			Assert.assertEquals(actualMsg, "Thanks for registering!");
			
		}
		
		 //Verify that user is able to add new category 
		 //@Test
		 public void addCategory() throws InterruptedException {
	        driver.get(applyURL);
			
			homePage = new HomePage(driver);
			//Thread.sleep(3000);
			loginPage = homePage.clickLogin();
			loginPage.enterUserName("testlegaladmin@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			dashboardPage.clickConfig();
			categoryPage=dashboardPage.clickCategory();
			Thread.sleep(5000);
			categoryPage.clickAddCategory();
			String catName ="Test"+System.currentTimeMillis();
			categoryPage.enterCategoryName(catName);
			Thread.sleep(2000);
			categoryPage.clickSave();
		}
		
	    //Verify that user is able to add new legal hold.
		 //@Test
		 public void addLegalHold() throws InterruptedException {
	        driver.get(applyURL);
			
			homePage = new HomePage(driver);
			loginPage = homePage.clickLogin();
			loginPage.enterUserName("testlegaladmin@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			dashboardPage.clickConfig();
			categoryPage=dashboardPage.clickCategory();
			Thread.sleep(5000);
			String catName ="Test"+System.currentTimeMillis();
			categoryPage.addNewCategory(catName);
			Thread.sleep(5000);
			dashboardPage =categoryPage.clicklegalHolds();
			dashboardPage.clickCreateLegalHold();
			Thread.sleep(5000);
			dashboardPage.selectCatogryLegalHold("canteen");
			dashboardPage.enterCasename(catName);
			Date date =new Date();
			SimpleDateFormat f =new SimpleDateFormat("dd/MM/yyyy");
			String strDate =f.format(date);
			dashboardPage.enterDate(strDate);
			dashboardPage.enterDiscription(catName);
			dashboardPage.clickCreateBttn();
			//Thread.sleep(20000);
		}
	     //Verify that user is able to add custodian to a legal hold
		//@Test
		public void addCustodian() throws InterruptedException {
	        driver.get(applyURL);
			
			homePage = new HomePage(driver).waitForPage();
			
			loginPage = homePage.clickLogin();
			loginPage.enterUserName("testlegaladmin@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			dashboardPage.clickConfig();
			categoryPage=dashboardPage.clickCategory();
			Thread.sleep(5000);
			String catName ="Test"+System.currentTimeMillis();
			categoryPage.addNewCategory(catName);
			Thread.sleep(5000);
			dashboardPage =categoryPage.clicklegalHolds();
			dashboardPage.clickCreateLegalHold();
			Thread.sleep(5000);
			dashboardPage.selectCatogryLegalHold("canteen");
			dashboardPage.enterCasename(catName);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
			Date date = new Date();
			String date1= dateFormat.format(date);
			dashboardPage.enterDate(date1);
			dashboardPage.enterDiscription(catName);
			custodianPage=dashboardPage.clickCreateBttn();
			Thread.sleep(5000);
			String Email ="Test"+System.currentTimeMillis()+"@yopmail.com";
			custodianPage.enterEmailAddr(Email);
			custodianPage.enterName(catName);
			custodianPage.enterAlsoNotify(catName);
			custodianPage.clickSaveBttn();
			
		}
		
		
		        //Verify that user is able to Add questionaire
		        //@Test
				public void addQuestionaire() throws InterruptedException {
			        driver.get(applyURL);
					
					homePage = new HomePage(driver).waitForPage();
					
					loginPage = homePage.clickLogin();
					loginPage.enterUserName("testlegaladmin@yopmail.com");
					loginPage.enterPassword("test@123");
					dashboardPage= loginPage.clickLoginBtn();
					Thread.sleep(5000);
					dashboardPage.clickConfig();
					categoryPage=dashboardPage.clickCategory();
					Thread.sleep(5000);
					String catName ="Test"+System.currentTimeMillis();
					categoryPage.addNewCategory(catName);
					Thread.sleep(5000);
					dashboardPage =categoryPage.clicklegalHolds();
					dashboardPage.clickCreateLegalHold();
					Thread.sleep(5000);
					dashboardPage.selectCatogryLegalHold("canteen");
					dashboardPage.enterCasename(catName);
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
					 Date date = new Date();
					 String date1= dateFormat.format(date);
					dashboardPage.enterDate(date1);
					dashboardPage.enterDiscription(catName);
					custodianPage=dashboardPage.clickCreateBttn();
					Thread.sleep(5000);
					String Email ="Test"+System.currentTimeMillis()+"@yopmail.com";
					custodianPage.enterEmailAddr(Email);
					custodianPage.enterName(catName);
					custodianPage.enterAlsoNotify(catName);
					custodianPage.clickSaveBttn();
					custodianPage.clickQuestionaire();
					custodianPage.clickApplyTemplate();					
					
				}
				
	
		        //Verify that user is able to Upload files
				//@Test
				public void uploadFiles() throws Exception {
			        driver.get(applyURL);
					
					homePage = new HomePage(driver).waitForPage();
					
					loginPage = homePage.clickLogin();
					loginPage.enterUserName("testlegaladmin@yopmail.com");
					loginPage.enterPassword("test@123");
					dashboardPage= loginPage.clickLoginBtn();
					Thread.sleep(5000);
					dashboardPage.clickConfig();
					categoryPage=dashboardPage.clickCategory();
					Thread.sleep(5000);
					String catName ="Test"+System.currentTimeMillis();
					categoryPage.addNewCategory(catName);
					Thread.sleep(5000);
					dashboardPage =categoryPage.clicklegalHolds();
					dashboardPage.clickCreateLegalHold();
					Thread.sleep(5000);
					dashboardPage.selectCatogryLegalHold("canteen");
					dashboardPage.enterCasename(catName);
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
					Date date = new Date();
					String date1= dateFormat.format(date);
					dashboardPage.enterDate(date1);
					dashboardPage.enterDiscription(catName);
					custodianPage=dashboardPage.clickCreateBttn();
					Thread.sleep(5000);
					String Email ="Test"+System.currentTimeMillis()+"@yopmail.com";
					custodianPage.enterEmailAddr(Email);
					custodianPage.enterName(catName);
					custodianPage.enterAlsoNotify(catName);
					custodianPage.clickSaveBttn();
					custodianPage.clickQuestionaire();
					custodianPage.clickApplyTemplate();
					custodianPage.clickFiles();
					custodianPage.clickUploadFiles();
					custodianPage.selectFilesUploadType();
					Thread.sleep(5000);
					String file = new File(this.getClass().getResource("/LegalHoldClosed.docx").toURI()).toString();
					custodianPage.fileUpload().upload(file);
					custodianPage.selectDocumentType("Legal/Court");
					Thread.sleep(5000);
					custodianPage.clickUploadBtn();
					Thread.sleep(5000);
					custodianPage.expandCommonDirectory();
					String str =custodianPage.getUploadedLegalCourtFileName().toString().trim();
					Assert.assertEquals(str, "LegalHoldClosed.docx");
					
				}
				
				//Verify that user is able to upload the Folder
				@Test
				public void uploadFolders() throws Exception {
			        driver.get(applyURL);
					
					homePage = new HomePage(driver).waitForPage();
					
					loginPage = homePage.clickLogin();
					loginPage.enterUserName("testlegaladmin@yopmail.com");
					loginPage.enterPassword("test@123");
					dashboardPage= loginPage.clickLoginBtn();
					Thread.sleep(5000);
					
					//dashboardPage =categoryPage.clicklegalHolds();
					dashboardPage.clickCreateLegalHold();
					Thread.sleep(5000);
					dashboardPage.selectCatogryLegalHold("canteen");
					String caseName ="caseName"+System.currentTimeMillis();
					dashboardPage.enterCasename(caseName);
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
					 Date date = new Date();
					 String date1= dateFormat.format(date);
					dashboardPage.enterDate(date1);
					dashboardPage.enterDiscription("test desc");
					custodianPage=dashboardPage.clickCreateBttn();
					Thread.sleep(5000);
					String Email ="Test"+System.currentTimeMillis()+"@yopmail.com";
					String MgrEmail ="TestM"+System.currentTimeMillis()+"@yopmail.com";
					
					custodianPage.enterEmailAddr(Email);
					custodianPage.enterName(caseName);
					custodianPage.enterAlsoNotify(MgrEmail);
					custodianPage.clickSaveBttn();
					custodianPage.clickQuestionaire();
					custodianPage.clickApplyTemplate();
					custodianPage.clickFiles();
					custodianPage.clickUploadFiles();
					
					Thread.sleep(5000);
					String file = new File(this.getClass().getResource("/TestFolder").toURI()).toString();
					custodianPage.fileUpload().uploadFolder(file);
					custodianPage.selectDocumentType("Legal/Court");
					Thread.sleep(5000);
					custodianPage.clickUploadBtn();
					Thread.sleep(20000);
					
				}		
	
        
		//Verify the forgot password functionality
	    //@Test
	    public void forgotPasswordFunctionality() throws Exception {
		driver.get(applyURL);
		loginPage = new LoginPage(driver).waitForPage();
		loginPage.clickForgotPassword();
		forgotPassword=new ForgotPassword(driver).waitForPage();
		forgotPassword.enterUserName("testopenaxes+3@gmail.com");
		forgotPassword.clickRequestPasswordReset();
		String clickHere = new MailAPIUtils()
				.getClickHereLinkFromEmail("Shubham");
		driver.get(clickHere);
		forgotPassword.enterNewPassword();
		forgotPassword.enterConfirmPassword();
		forgotPassword.clickChangePassword();
		driver.manage().deleteAllCookies();
		driver.close();
		/*
		loginPage.enterUserName("testlegaladmin@yopmail.com");
		loginPage.enterPassword("test@123");
		dashboardPage= loginPage.clickLoginBtn();
		Thread.sleep(5000);
		String actual=dashboardPage.getHeader();
		Assert.assertEquals(actual, "My Legal Holds");
		*/
	}
	
	       //Verify that user is able to create a new ITAdministrator
	       //@Test
	       public void createNewUserITAdministrator() throws Exception {
			driver.get(applyURL);
			homePage = new HomePage(driver).waitForPage();		
			loginPage = homePage.clickLogin();		
			loginPage.enterUserName("testlegaladmin@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			String actual=dashboardPage.getHeader();
			Assert.assertEquals(actual, "My Legal Holds");
			userPage=dashboardPage.clickUsers();
			Thread.sleep(5000);
			userPage.clickCreateUser();
			Thread.sleep(3000);
			createUserPopup=userPage.switchToCreateUserpopup();
			createUserITAdmin();	
			
		}
	
	//Verify create user functionality LegalManager
	//@Test
	public void createNewUserLegalManager() throws Exception {
			//Map<String, Object> userData = pupulateUniqueData(xmlUtils.getXMLNodeValues("userData"));
			driver.get(applyURL);
			homePage = new HomePage(driver).waitForPage();		
			loginPage = homePage.clickLogin();		
			loginPage.enterUserName("testlegaladmin@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			String actual=dashboardPage.getHeader();
			Assert.assertEquals(actual, "My Legal Holds");
			userPage=dashboardPage.clickUsers();
			Thread.sleep(5000);
			userPage.clickCreateUser();
			Thread.sleep(3000);
			createUserPopup=userPage.switchToCreateUserpopup();
			createUserLegalManager();
			
			
		}
	
	//Verify create user functionality Standard
	//@Test
	public void createNewUserStandard() throws Exception {
		driver.get(applyURL);
		homePage = new HomePage(driver).waitForPage();		
		loginPage = homePage.clickLogin();		
		loginPage.enterUserName("testlegaladmin@yopmail.com");
		loginPage.enterPassword("test@123");
		dashboardPage= loginPage.clickLoginBtn();
		Thread.sleep(5000);
		String actual=dashboardPage.getHeader();
		Assert.assertEquals(actual, "My Legal Holds");
		userPage=dashboardPage.clickUsers();
		Thread.sleep(5000);
		userPage.clickCreateUser();
		Thread.sleep(3000);
		createUserPopup=userPage.switchToCreateUserpopup();
		createUserStandard();		
		
	}
	
	//Verify create user functionality Requestor
	//@Test
	public void createNewUserRequestor() throws Exception {
		driver.get(applyURL);
		homePage = new HomePage(driver).waitForPage();		
		loginPage = homePage.clickLogin();		
		loginPage.enterUserName("testlegaladmin@yopmail.com");
		loginPage.enterPassword("test@123");
		dashboardPage= loginPage.clickLoginBtn();
		Thread.sleep(5000);
		String actual=dashboardPage.getHeader();
		Assert.assertEquals(actual, "My Legal Holds");
		userPage=dashboardPage.clickUsers();
		Thread.sleep(5000);
		userPage.clickCreateUser();
		Thread.sleep(3000);
		createUserPopup=userPage.switchToCreateUserpopup();
		createUserRequestor();		
		
	}
	
	//Verify create user functionality Custodian
	//@Test
	public void createNewUserCustodian() throws Exception {
		//Map<String, Object> userData = pupulateUniqueData(xmlUtils.getXMLNodeValues("userData"));
		driver.get(applyURL);		
		homePage = new HomePage(driver).waitForPage();		
		loginPage = homePage.clickLogin();		
		loginPage.enterUserName("testlegaladmin@yopmail.com");
		loginPage.enterPassword("test@123");
		dashboardPage= loginPage.clickLoginBtn();
		Thread.sleep(5000);
		String actual=dashboardPage.getHeader();
		Assert.assertEquals(actual, "My Legal Holds");
		userPage=dashboardPage.clickUsers();
		Thread.sleep(5000);
		userPage.clickCreateUser();
		Thread.sleep(3000);
		createUserPopup=userPage.switchToCreateUserpopup();
		createUserCustodian();	
		
	}
	
	    //Verify the activation of legal hold functionality
	    //@Test
	    public void activateLegalHold() throws InterruptedException {
	           driver.get(applyURL);
	     
	     homePage = new HomePage(driver).waitForPage();
	     
	     loginPage = homePage.clickLogin();
	     loginPage.enterUserName("testlegaladmin@yopmail.com");
	     loginPage.enterPassword("test@123");
	     dashboardPage= loginPage.clickLoginBtn();
	     Thread.sleep(5000);
	     dashboardPage.clickConfig();
	     categoryPage=dashboardPage.clickCategory();
	     Thread.sleep(5000);
	     String catName ="Test"+System.currentTimeMillis();
	     categoryPage.addNewCategory(catName);
	     Thread.sleep(5000);
	     dashboardPage =categoryPage.clicklegalHolds();
	     dashboardPage.clickCreateLegalHold();
	     Thread.sleep(5000);
	     dashboardPage.selectCatogryLegalHold("canteen");
	     dashboardPage.enterCasename(catName);
	     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
	      Date date = new Date();
	      String date1= dateFormat.format(date);
	     dashboardPage.enterDate(date1);
	     dashboardPage.enterDiscription(catName);
	     custodianPage=dashboardPage.clickCreateBttn();
	     Thread.sleep(5000);
	     String MgrEmail ="Test"+System.currentTimeMillis()+"@yopmail.com";
	     String Email ="testopenaxes+"+System.currentTimeMillis()+"@gmail.com";
	     custodianPage.enterEmailAddr(Email);
	     custodianPage.enterName(catName);
	     custodianPage.enterAlsoNotify(MgrEmail);
	     custodianPage.clickSaveBttn();
	     custodianPage.clickQuestionaire();
	     custodianPage.clickApplyTemplate();
	     Thread.sleep(5000);
	     custodianPage.clickActivate();
	     Thread.sleep(5000);
	     custodianPage.clickPopActivate();
	     
	     //mail acknowledgement 
	     String clickHere = new MailAPIUtils()
					.getClickHereToAcknowledeLinkFromEmail(catName);
	     driver.get(clickHere);
	     litigationHoldNotice = new LitigationHoldNotice(driver).waitForPage();
	     litigationHoldNotice.clickUnderstandYesBtn();
	     litigationHoldNotice.addQuestionareAnswer();
	     litigationHoldNotice.activateAccount();
	     Thread.sleep(5000);
	     litigationHoldNotice.addPasswordDetails("Testuser123");
	     driver.get("http://demo.openaxes.com:82/App#//custodian");
	     driver.findElement(By.xpath("//a[@href='./logout']/parent::li/parent::ul/preceding-sibling::a")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//a[@href='./logout']")).click();
		 driver.get("http://demo.openaxes.com:82/Login");
		 loginPage = new LoginPage(driver).waitForPage();
		 loginPage.enterUserName(Email);
		 loginPage.enterPassword("Testuser123");
		 dashboardPage= loginPage.clickLoginBtn();
		 Thread.sleep(5000);
		 String actual=dashboardPage.getHeader();
		 Assert.assertEquals(actual, "My Legal Holds");	
		 
	     
	    }
	
	  //Verify the company tag functionality 
	  //@Test 
	  public void companyTagfunctionality() throws InterruptedException {
	      driver.get(applyURL);
	      
	      homePage = new HomePage(driver).waitForPage();
	      
	      loginPage = homePage.clickLogin();
	      loginPage.enterUserName("testlegaladmin@yopmail.com");
	      loginPage.enterPassword("test@123");
	      dashboardPage= loginPage.clickLoginBtn();
	      Thread.sleep(5000);
	      dashboardPage.clickConfig();
	      categoryPage=dashboardPage.clickTag();
	      Thread.sleep(5000);
	      String catName ="Test"+System.currentTimeMillis();
	      categoryPage.addNewTag(catName);
	          
	     }
	  
	//verify tag creation functionality
	  //@Test
	  public void tagCreation() throws InterruptedException {
	     driver.get(applyURL);
	     
	     homePage = new HomePage(driver).waitForPage();
	     
	     loginPage = homePage.clickLogin();
	     loginPage.enterUserName("testlegaladmin@yopmail.com");
	     loginPage.enterPassword("test@123");
	     dashboardPage= loginPage.clickLoginBtn();
	     Thread.sleep(5000);
	     dashboardPage.clickConfig();
	     categoryPage=dashboardPage.clickCategory();
	     Thread.sleep(5000);
	     String catName ="Test"+System.currentTimeMillis();
	     categoryPage.addNewCategory(catName);
	     Thread.sleep(5000);
	     dashboardPage =categoryPage.clicklegalHolds();
	     dashboardPage.clickCreateLegalHold();
	     Thread.sleep(5000);
	     dashboardPage.selectCatogryLegalHold("canteen");
	     dashboardPage.enterCasename(catName);
	     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
	     Date date = new Date();
	     String date1= dateFormat.format(date);
	     dashboardPage.enterDate(date1);
	     dashboardPage.enterDiscription(catName);
	     custodianPage=dashboardPage.clickCreateBttn();
	     Thread.sleep(5000);
	     String Email ="Test"+System.currentTimeMillis()+"@yopmail.com";
	     custodianPage.enterEmailAddr(Email);
	     custodianPage.enterName(catName);
	     custodianPage.enterAlsoNotify(catName);
	     custodianPage.clickSaveBttn();    
	     custodianPage.clickFiles();
	     Thread.sleep(5000);
	     String tagName ="Test"+System.currentTimeMillis();     
	     custodianPage.createNewTag(tagName);
	          
	    }
	  
     //Create a legal hold using requester user
	  //@Test
	  public void createLegalHoldbyRequesterUser() throws InterruptedException {
	        driver.get(applyURL);
			
			homePage = new HomePage(driver).waitForPage();
			
			loginPage = homePage.clickLogin();
			loginPage.enterUserName("testrequestor@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			//dashboardPage.clickConfig();
			//categoryPage=dashboardPage.clickCategory();
			//Thread.sleep(5000);
			String catName ="Test"+System.currentTimeMillis();
			//categoryPage.addNewCategory(catName);
			//Thread.sleep(5000);
			//dashboardPage =categoryPage.clicklegalHolds();
			dashboardPage.clickCreateLegalHold();
			Thread.sleep(5000);
			String caseName ="CaseName"+System.currentTimeMillis();
			dashboardPage.selectCatogryLegalHold("canteen");
			dashboardPage.enterCasename(caseName);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
			 Date date = new Date();
			 String date1= dateFormat.format(date);
			dashboardPage.enterDate(date1);
			dashboardPage.enterDiscription(catName);
			custodianPage=dashboardPage.clickCreateBttn();
			Thread.sleep(5000);
			String mgrEmail ="TestM"+System.currentTimeMillis()+"@yopmail.com";
			String Email ="Test"+System.currentTimeMillis()+"@yopmail.com";
			custodianPage.enterEmailAddr(Email);
			custodianPage.enterName(catName);
			custodianPage.enterAlsoNotify(mgrEmail);
			custodianPage.clickSaveBttn();
			Thread.sleep(2000);
			custodianPage.clickSubmitForApproval();
			Thread.sleep(2000);
			custodianPage.clickSubmit();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
			driver.findElement(By.xpath("//a[@href='./logout']")).click();
			driver.get("http://demo.openaxes.com:82/Login");
			loginPage = new LoginPage(driver).waitForPage();
			loginPage.enterUserName("testlegaladmin@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			String actual=dashboardPage.getHeader();
			Assert.assertEquals(actual, "My Legal Holds");
			legalHoldPage =dashboardPage.clickLegalHold();
			legalHoldPage.searchLegalHold(caseName);
			legalHoldPage.clickLegalHoldName(caseName);
			legalHoldPage.clickApproveButton();
			Thread.sleep(3000);
			legalHoldPage.clickLegalHoldApproveButton();
			String str=legalHoldPage.verifyConfirmationText();
			Assert.assertEquals(str, "Activated by testlegaladmin","Activate by text does not match");	
		}		
			
	 //Create a legal hold using requester user
	  //@Test
	  public void createLegalHoldbyRequesterUserReject() throws InterruptedException {
	        driver.get(applyURL);
			
			homePage = new HomePage(driver).waitForPage();
			
			loginPage = homePage.clickLogin();
			loginPage.enterUserName("testrequestor@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			//dashboardPage.clickConfig();
			//categoryPage=dashboardPage.clickCategory();
			//Thread.sleep(5000);
			String catName ="Test"+System.currentTimeMillis();
			//categoryPage.addNewCategory(catName);
			//Thread.sleep(5000);
			//dashboardPage =categoryPage.clicklegalHolds();
			dashboardPage.clickCreateLegalHold();
			Thread.sleep(5000);
			String caseName ="CaseName"+System.currentTimeMillis();
			dashboardPage.selectCatogryLegalHold("canteen");
			dashboardPage.enterCasename(caseName);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
			 Date date = new Date();
			 String date1= dateFormat.format(date);
			dashboardPage.enterDate(date1);
			dashboardPage.enterDiscription(catName);
			custodianPage=dashboardPage.clickCreateBttn();
			Thread.sleep(5000);
			String mgrEmail ="TestM"+System.currentTimeMillis()+"@yopmail.com";
			String Email ="Test"+System.currentTimeMillis()+"@yopmail.com";
			custodianPage.enterEmailAddr(Email);
			custodianPage.enterName(catName);
			custodianPage.enterAlsoNotify(mgrEmail);
			custodianPage.clickSaveBttn();
			Thread.sleep(2000);
			custodianPage.clickSubmitForApproval();
			Thread.sleep(2000);
			custodianPage.clickSubmit();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
			driver.findElement(By.xpath("//a[@href='./logout']")).click();
			driver.get("http://demo.openaxes.com:82/Login");
			loginPage = new LoginPage(driver).waitForPage();
			loginPage.enterUserName("testlegaladmin@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			String actual=dashboardPage.getHeader();
			Assert.assertEquals(actual, "My Legal Holds");
			legalHoldPage =dashboardPage.clickLegalHold();
			legalHoldPage.searchLegalHold(caseName);
			legalHoldPage.clickLegalHoldName(caseName);
			legalHoldPage.clickRejectButton();
			Thread.sleep(3000);
			legalHoldPage.writeRejectComment("This is a test comment");
			legalHoldPage.clickLegalHoldRejectButton();
			Thread.sleep(3000);
			String str=legalHoldPage.verifyConfirmationText();
			Assert.assertEquals(str, "Rejected by testlegaladmin","Reject by text does not match");	
		}	
	
	 //Create a legal hold using requester user
	  //@Test
	  public void createLegalHoldbyRequesterUserCancelLegalHold() throws InterruptedException {
	        driver.get(applyURL);
			homePage = new HomePage(driver).waitForPage();
			loginPage = homePage.clickLogin();
			loginPage.enterUserName("testrequestor@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			//dashboardPage.clickConfig();
			//categoryPage=dashboardPage.clickCategory();
			//Thread.sleep(5000);
			String catName ="Test"+System.currentTimeMillis();
			//categoryPage.addNewCategory(catName);
			//Thread.sleep(5000);
			//dashboardPage =categoryPage.clicklegalHolds();
			dashboardPage.clickCreateLegalHold();
			Thread.sleep(5000);
			String caseName ="CaseName"+System.currentTimeMillis();
			dashboardPage.selectCatogryLegalHold("canteen");
			dashboardPage.enterCasename(caseName);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
			 Date date = new Date();
			 String date1= dateFormat.format(date);
			dashboardPage.enterDate(date1);
			dashboardPage.enterDiscription(catName);
			custodianPage=dashboardPage.clickCreateBttn();
			Thread.sleep(5000);
			String mgrEmail ="TestM"+System.currentTimeMillis()+"@yopmail.com";
			String Email ="Test"+System.currentTimeMillis()+"@yopmail.com";
			custodianPage.enterEmailAddr(Email);
			custodianPage.enterName(catName);
			custodianPage.enterAlsoNotify(mgrEmail);
			custodianPage.clickSaveBttn();
			Thread.sleep(2000);
			custodianPage.clickSubmitForApproval();
			Thread.sleep(2000);
			custodianPage.clickSubmit();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
			driver.findElement(By.xpath("//a[@href='./logout']")).click();
			driver.get("http://demo.openaxes.com:82/Login");
			loginPage = new LoginPage(driver).waitForPage();
			loginPage.enterUserName("testlegaladmin@yopmail.com");
			loginPage.enterPassword("test@123");
			dashboardPage= loginPage.clickLoginBtn();
			Thread.sleep(5000);
			String actual=dashboardPage.getHeader();
			Assert.assertEquals(actual, "My Legal Holds");
			legalHoldPage =dashboardPage.clickLegalHold();
			legalHoldPage.searchLegalHold(caseName);
			legalHoldPage.clickLegalHoldName(caseName);
			legalHoldPage.clickCancelLegalHoldButton();
			Thread.sleep(2000);
			legalHoldPage.clickEndLegalHoldButton();
			legalHoldPage.clickYesIAmSureButtonButton();
		}	
	  
	
	
	@Override
	public void afterTest() {
		System.out.println("after test------------->>>");
		 
		try {
			
			//driver.get("http://demo.openaxes.com:82/App#/investigations?view=list");
			System.out.println("test1");
			driver.navigate().refresh();
			driver.findElement(By.xpath("//a[@href='./logout']/parent::li/parent::ul/preceding-sibling::a")).click();
			System.out.println("test2");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@href='./logout']")).click();
		} catch (Exception e) {
			driver.manage().deleteAllCookies();
			System.out.println("test3");
		}
		driver.manage().deleteAllCookies();
		
	}
		
	/*

	@Override
	public void beforeClass() throws InterruptedException, Exception {
	}
	*/

}
