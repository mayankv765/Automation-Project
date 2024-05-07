package EcomSite.PageObjModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import EcomSite.AbstractComponents.AbstractComponent;

public class ThankYouPage extends AbstractComponent{

	WebDriver driver;

	public ThankYouPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath= "//h1[@class='hero-primary']")
	WebElement wait1;
	
	@FindBy(xpath= "//h1[@class='hero-primary']")
	WebElement text;
	
	public String CaptureThankYou() {
		VisibilityOfElement(wait1);
		
		String textName = text.getText();
		return textName;
		
	}
	
}
