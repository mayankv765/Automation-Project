package EcomSite.PageObjModel;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcomSite.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class ='form-group']/input")
	WebElement countryName;

	@FindBy(xpath = "//button[@type='button']")
	WebElement wait1;

	@FindBy(xpath = "//button[@type='button']/span")
	List<WebElement> countryList;

	public void SelectCountry(String country) {
		countryName.sendKeys(country);

		VisibilityOfElement(wait1);

		for (int i = 0; i < countryList.size(); i++) {
			// Get text name of counties
			String name = countryList.get(i).getText();

			if (name.equalsIgnoreCase(country)) {
				// Click on country name
				countryList.get(i).click();
			}
		}

	}

	@FindBy(xpath = "//div[@class='actions']/a")
	WebElement placeOrder;

	public ThankYouPage ClickOnPlaceOrder() {
		VisibilityOfElement(placeOrder);
		// Place order
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", placeOrder);

		ThankYouPage thankYouPage = new ThankYouPage(driver);

		return thankYouPage;
	}

}
