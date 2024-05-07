package testCases.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EcomSite.PageObjModel.MyCartPage;
import EcomSite.PageObjModel.PaymentPage;
import EcomSite.PageObjModel.ProductPage;
import EcomSite.PageObjModel.ThankYouPage;
import testCases.TestComponents.BaseTest;

public class HashMapSetDataProvider extends BaseTest{
	
	String[] selectProduct = { "ADIDAS ORIGINAL", "IPHONE 13 PRO" };

	@Test(dataProvider = "DataSets")
	public void VerifyAllDataSets(HashMap<String, String> input) throws IOException {
		
		ProductPage productPage = landingPage.loginApplication(input.get("email"), input.get("pass"));

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
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("email", "mayankvyas765@gmail.com");
		map.put("pass", "Mayank@123");
		
		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("email", "vyas@gmail.com");
		map1.put("pass", "Vyas@123");
		

		return new Object[][] {{map },{map1}};
	
		}

}
