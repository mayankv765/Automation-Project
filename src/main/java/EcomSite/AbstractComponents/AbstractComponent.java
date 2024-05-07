package EcomSite.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import EcomSite.PageObjModel.MyCartPage;
import EcomSite.PageObjModel.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
	}

	public void VisibilityOfElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void InVisibilityOfElement(WebElement element1) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf(element1));
	}

	@FindBy(xpath = "//button[@routerlink= '/dashboard/myorders']")
	WebElement Orders;

	public OrderPage ClickOnOrders() {

		Orders.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;

	}
	
	@FindBy(xpath = "//button[@class='btn btn-custom']/i[@class= 'fa fa-shopping-cart']")
	WebElement clickOnCart;

	public MyCartPage ClickToCart() {

		clickOnCart.click();
		MyCartPage myCartPage = new MyCartPage(driver);
		return myCartPage;
	}

}
