package testCases.Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import EcomSite.PageObjModel.LandingPage;
import EcomSite.PageObjModel.MyCartPage;
import EcomSite.PageObjModel.OrderPage;
import EcomSite.PageObjModel.PaymentPage;
import EcomSite.PageObjModel.ProductPage;
import EcomSite.PageObjModel.ThankYouPage;
import testCases.TestComponents.BaseTest;

public class MainTest extends BaseTest {

	String[] selectProduct = { "IPHONE 13 PRO", "ADIDAS ORIGINAL" };
	@Test
	public void SubmitOrder() throws IOException {
		
		ProductPage productPage = landingPage.loginApplication("mayankvyas765@gmail.com", "Mayank@123");

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
		Assert.assertEquals("THANKYOU FOR THE ORDER.", textName) ;
		System.out.println(textName);
	}
	
	//Validate After placing the order, product should be in order history
		@Test(dependsOnMethods = "SubmitOrder")
		public void VerifyProductInOrderHistory() {
			ProductPage productPage = landingPage.loginApplication("mayankvyas765@gmail.com", "Mayank@123");
			OrderPage orderPage = productPage.ClickOnOrders();
			orderPage.VerifyOrderDisplay(selectProduct);
			
			Assert.assertTrue(orderPage.VerifyOrderDisplay(selectProduct));
		}
		
		

}
