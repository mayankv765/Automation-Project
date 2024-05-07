package EcomSite.PageObjModel;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import EcomSite.AbstractComponents.AbstractComponent;

public class ProductPage extends AbstractComponent {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// All products
	@FindBy(xpath = "//h5[@style = 'text-transform: uppercase;']/b")
	List<WebElement> products;

	// for wait
	@FindBy(xpath = "//h5[@style = 'text-transform: uppercase;']/b")
	WebElement productswait;

	@FindBy(css = "#toast-container")
	WebElement toastMessage;

	@FindBy(css = ".ng-animating")
	WebElement loader;
	
	@FindBy(xpath = "//button[@class= 'btn w-10 rounded']")
	List<WebElement> addToCartButton;

	public List<WebElement> getProductList() {

		VisibilityOfElement(productswait);
		return products;
	}

	public void SelectProducts(String[] firstProduct) {

		List<String> selectProdcts = Arrays.asList(firstProduct);

		for (int i = 0; i < products.size(); i++) {
			// Get product name
			String productName = products.get(i).getText();

			if (selectProdcts.contains(productName)) {
				// Add to cart - 2 times
				addToCartButton.get(i).click();
				
				// Wait to appear the toast message
				VisibilityOfElement(toastMessage);
				
				// Wait to disappear the loading
				//InVisibilityOfElement(loader);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	
	
}
