package testCases.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import EcomSite.PageObjModel.LandingPage;
import EcomSite.PageObjModel.OrderPage;

public class BaseTest {

	public WebDriver driver;

	public LandingPage landingPage;
	
	//public OrderPage orderPage;

	public WebDriver intializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\EcomSite\\Resorces\\GlobleData.properties");

		prop.load(fis);
		String broswerName = prop.getProperty("broswer");

		if (broswerName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
		}

		else if (broswerName.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (broswerName.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver", "D:\\Selenium\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		return driver;

	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot)driver;
		
		File source = ss.getScreenshotAs(OutputType.FILE);
		
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName  + ".png");
		
		FileUtils.copyFile(source,file );
		
		return System.getProperty("user.dir") + "//reports//" + testcaseName  +".png";
		
	}

	@BeforeMethod
	public LandingPage launchApp() throws IOException {

		intializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();

		return landingPage;
	}
	
	@AfterMethod
	public void CloseBroswer() {

		driver.quit();
	}

}
