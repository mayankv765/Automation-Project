package testCases.Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import EcomSite.PageObjModel.LandingPage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllTests {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

		String baseUrl = "https://rahulshettyacademy.com/client";
		driver.get(baseUrl);
		
		LandingPage landingPage = new LandingPage(driver);
		// Login
		driver.findElement(By.id("userEmail")).sendKeys("mayankvyas765@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mayank@123");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//h5[@style = 'text-transform: uppercase;']/b"))));

		// All Products
		List<WebElement> products = driver.findElements(By.xpath("//h5[@style = 'text-transform: uppercase;']/b"));

		// Products that we want in Cart
		String[] selectProduct = { "ADIDAS ORIGINAL", "IPHONE 13 PRO" };

		// Converted Products in ArrayList
		List<String> selectProdcts = Arrays.asList(selectProduct);

		for (int i = 0; i < products.size(); i++) {
			// Get product name
			String productName = products.get(i).getText();
			System.out.println(productName);

			if (selectProdcts.contains(productName)) {
				// Add to cart - 2 times
				driver.findElements(By.xpath("//button[@class= 'btn w-10 rounded']")).get(i).click();

				// Wait to appear the toast message
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));

			}

			// Wait to disappear the loading
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		}
		// loop ends

		// Click on Cart
		driver.findElement(By.xpath("//button[@class='btn btn-custom']/i[@class= 'fa fa-shopping-cart']")).click();

		// Check all selected products are in cart or not
		// Get all products that are in cat
		List<WebElement> textNames = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		for (int i = 0; i < textNames.size(); i++) {
			// Get Text from cart products and compare to selectproduct(Which have our
			// products)
			Boolean match = textNames.get(i).getText().equalsIgnoreCase(selectProduct[i]);

			// If products match, it shows True
			// System.out.println(match);
			Assert.assertTrue(match);

		}

		// Click on Checkout
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[@class='totalRow']/button")));

		// Select country
		String country = "India";
		driver.findElement(By.xpath("//div[@class ='form-group']/input")).sendKeys(country);

		// Wait till drop down/Suggestions visible
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type='button']"))));

		// Fatch all suggestions/countries
		List<WebElement> cList = driver.findElements(By.xpath("//button[@type='button']/span"));

		for (int i = 0; i < cList.size(); i++) {
			// Get text name of counties
			String name = cList.get(i).getText();

			if (name.equalsIgnoreCase(country)) {
				// Click on country name
				driver.findElements(By.xpath("//button[@type='button']/span")).get(i).click();
			}

		}

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='actions']/a"))));

		// Place order
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='actions']/a")));

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='hero-primary']"))));

		// Capture Text
		String textName = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", textName);

		System.out.println(textName);

		driver.close();
	}

}
