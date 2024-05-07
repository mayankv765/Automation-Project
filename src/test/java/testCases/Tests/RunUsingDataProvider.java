package testCases.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EcomSite.PageObjModel.MyCartPage;
import EcomSite.PageObjModel.PaymentPage;
import EcomSite.PageObjModel.ProductPage;
import EcomSite.PageObjModel.ThankYouPage;
import testCases.TestComponents.BaseTest;

public class RunUsingDataProvider extends BaseTest{
	
	//String[] selectProduct = { "ADIDAS ORIGINAL", "IPHONE 13 PRO" };

	@Test(dataProvider = "DataSets")
	public void VerifyAllDataSets(String email, String pass, String[] selectProduct) throws IOException {
		
		ProductPage productPage = landingPage.loginApplication(email, pass);

		// Get All Products
		productPage.getProductList();

		// Products that we want in Cart and Add to cart
		productPage.SelectProducts(selectProduct);

		// Click on Cart
		MyCartPage myCartPage = productPage.ClickToCart();

		Boolean match = myCartPage.ComparProductsName(selectProduct);
		Assert.assertTrue(match);
		System.out.println(match);

		PaymentPage paymentPage = myCartPage.ClickOnCheckout();

		paymentPage.SelectCountry("India");

		ThankYouPage thankYouPage = paymentPage.ClickOnPlaceOrder();

		String textName = thankYouPage.CaptureThankYou();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", textName);
		System.out.println(textName);
	}

	@DataProvider
	public Object[][] DataSets() {
	
			return new Object[][] {{"mayankvyas765@gmail.com", "Mayank@123","ZARA COAT 3" },{"vyas@gmail.com", "Vyas@123", "ADIDAS ORIGINAL"}};
	
		}

}
