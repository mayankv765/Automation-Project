package EcomSite.PageObjModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcomSite.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Page Factory
	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;

	public ProductPage loginApplication(String useremail, String userPassword) {

		email.sendKeys(useremail);
		password.sendKeys(userPassword);
		submit.click();

		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;

	public String getErrorMessage() {

		VisibilityOfElement(errorMessage);
		return errorMessage.getText();
		// .ng-tns-c4-5.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	}
	
	@FindBy(xpath = "//div[text() = '*Password is required']")
	WebElement blankErrorMessage;

	public String BlankPasswordErrorMessage() {

		VisibilityOfElement(blankErrorMessage);
		return blankErrorMessage.getText();
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
	}

}
