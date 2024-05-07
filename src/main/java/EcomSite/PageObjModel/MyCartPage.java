package EcomSite.PageObjModel;

import java.util.List;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import EcomSite.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath= "//div[@class='cartSection']/h3")
	List<WebElement> textNames;
	
	
	public Boolean ComparProductsName(String[] selectProduct) {	
	
		for (int i = 0; i < textNames.size(); i++) {
			String productNames = textNames.get(i).getText();
			
			for(int j=0; j<textNames.size(); j++){
				if(productNames.equals(selectProduct[j])) {
					return true;
				}
				
			}
			
		}
		return false;
		
	}
	
	@FindBy(xpath= "//li[@class='totalRow']/button")
	WebElement checkout;
	

	public PaymentPage ClickOnCheckout() {
		VisibilityOfElement(checkout);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkout);
		
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
		
	}
	
	
}
