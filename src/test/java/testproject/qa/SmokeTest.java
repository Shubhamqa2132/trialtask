package testproject.qa;


import org.testng.Assert;
import org.testng.annotations.Test;
import testproject.qa.po.HomePage;
import testproject.qa.po.LoginPage;
import testproject.qa.po.RentalApplication;


public class SmokeTest extends BaseTest{
	private RentalApplication rentalApplicationPage;
	private HomePage homePage;
	private LoginPage loginPage;
	public static String email;
	
	//Functional Test scenario
	@Test(priority=3)
	public void submitRentalApplication() throws InterruptedException {
		driver.get(applyURL);
		rentalApplicationPage = new RentalApplication(driver);
		rentalApplicationPage.enterDesireddate();
		rentalApplicationPage.enterFirstName("test");
		rentalApplicationPage.enterLastName("qa");
		rentalApplicationPage.enterPhone("1231233452");
		
		email = "qatest1+" + System.currentTimeMillis() + "@yopmail.com";
		rentalApplicationPage.enterEmail(email);
		rentalApplicationPage.enterStreetName("test street");
		rentalApplicationPage.enterStreetNumber("S 123");
		rentalApplicationPage.enterZipCode("12323");
		rentalApplicationPage.enterCity("test city");
		rentalApplicationPage.enterEmployername("emp");
		rentalApplicationPage.enterEmployerAdd("emp add");
		rentalApplicationPage.enterOccupation("teacher");
		rentalApplicationPage.enterMonthlyIncome("50000");
		rentalApplicationPage.clickSmokingToggle();
		rentalApplicationPage.clickPetToggle();
		rentalApplicationPage.clickRentToggle();
		rentalApplicationPage.clickEvictedToggle();
		rentalApplicationPage.enterSignature();
		rentalApplicationPage.enterPassword("123456");
		rentalApplicationPage.enterConfirmPassword("123456");
		rentalApplicationPage.selectAgreement();
		homePage =rentalApplicationPage.clickRegisterBtn();
		Thread.sleep(5000);
		homePage.clickAlertSubmit();
		homePage.clickAlertOk();
		String actualText =homePage.getAvatarText();
		String expectedText = "test qa";
		Assert.assertEquals(actualText, expectedText);
		homePage.clickLogout();
		
	}
    
	//Functional Test scenario
	@Test(dependsOnMethods= {"submitRentalApplication"})
	public void loginExistingUser() throws InterruptedException {
		driver.get("https://acme-qa.everreal.co/accounts/login");
		loginPage = new LoginPage(driver);
		
		loginPage.enterUserName(email);
		loginPage.enterPassword("123456");
		homePage=loginPage.clickLoginBtn();
		Thread.sleep(5000);
		//Assert.assertTrue(homePage.isUserLoggedIn());
		
		//homePage.clickLogout();
		
		
	}
	
	//Validation Test scenario
	@Test(priority=2)
	public void checkValidationOnPhoneNumber() throws InterruptedException {
		driver.get(applyURL);
		rentalApplicationPage = new RentalApplication(driver);
		rentalApplicationPage = new RentalApplication(driver);
		rentalApplicationPage.enterDesireddate();
		rentalApplicationPage.enterFirstName("test");
		rentalApplicationPage.enterLastName("qa");
		rentalApplicationPage.enterPhone("jdgjsgds");
		
		String actualError =rentalApplicationPage.getValidationError();
		Assert.assertEquals(actualError, "Must be a valid phone number (i.e +4989123654)");
				
	}
	
	//UI test scenario 
	@Test(priority=1)
	public void validateExposeButtonUI() {
		driver.get(applyURL);
		rentalApplicationPage = new RentalApplication(driver);
		//Validate button Text
		Assert.assertEquals(rentalApplicationPage.getExposeBtnText(),"VIEW EXPOSÉ");
		//validate button style & CSS(font size)
		Assert.assertEquals(rentalApplicationPage.getExposeBtnCSS(), "14px");
	}
	
	@Override
	public void afterTest() {
		// TODO Auto-generated method stub
		
	}

}
