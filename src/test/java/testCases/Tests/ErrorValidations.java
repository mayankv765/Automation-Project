package testCases.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import EcomSite.PageObjModel.MyCartPage;
import EcomSite.PageObjModel.ProductPage;
import testCases.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test(retryAnalyzer = testCases.TestComponents.Retry.class)
	public void getErrorMessageOfLogin() {

		landingPage.loginApplication("mayankvyas765@gmail.com", "Mayank@12");

		landingPage.getErrorMessage();

		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());

	}

	@Test
	public void getErrorMessageOfBlankPassword() {
		landingPage.loginApplication("mayankvyas765@gmail.com", "");

		landingPage.BlankPasswordErrorMessage();

		Assert.assertEquals("*Password is required", landingPage.BlankPasswordErrorMessage());

	}
	
	
	
	

}
