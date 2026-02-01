package com.qa.openkart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.openkart.factory.DriverFactory;
import com.qa.openkart.pages.AccountsPage;
import com.qa.openkart.pages.LoginPage;
import com.qa.openkart.pages.ProductInfoPage;
import com.qa.openkart.pages.RegistrationPage;
import com.qa.openkart.pages.SearchResultsPage;

@Listeners(ChainTestListener.class)
public class BaseTest {

	WebDriver driver;
	protected Properties prop;
	protected DriverFactory df;
	protected LoginPage loginpage;
	protected AccountsPage accpage;
	protected SearchResultsPage searchresultpage;
	protected ProductInfoPage productinfopage;
	protected RegistrationPage registerpage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setUp(@Optional("edge") String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
	}

	@AfterMethod
	public void attacheScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshot(), "image/png");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
