package testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	
	public void verify_accountRegistrationTest() {
		
		logger.info("**************Starting******************");
		
		try {
			HomePage hp=new HomePage(driver);
			
			
			hp.clickMyAccount();
			
			logger.info("Clicked on My Account link");
			
			hp.clickRegister();
			
			logger.info("Clicked Register button");
			
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			
			logger.info("Entering Customer Details...........");
			
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
//			regpage.setTelephone(randomeNumber());
			
			String password=randomAlphaNumeric();
			
			regpage.setPassword(password);
//			regpage.setConfirmPassword(password);
			
			//scroll to the bottom of page
			scrollToBottomOfPage();
			
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			
			logger.info("Validating Error message...........");
			
			String confmsg=regpage.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test Case is failed.........");
			logger.debug("Debug logs....................");
			Assert.fail();
		}
		
		logger.info("*************Finished Test Case*******************");
		
		
	}
	
}
