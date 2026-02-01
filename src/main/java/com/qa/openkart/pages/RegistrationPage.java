package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.ElementUtil;
import com.qa.openkart.utils.StringUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailID = By.id("input-email");
	private By telephoneNmb = By.id("input-telephone");
	private By pwd = By.id("input-password");
	private By cfmPwd = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckbox = By.name("agree");
	private By continueBtn = By.cssSelector("input[type='submit']");

	private By successMessage = By.cssSelector("div#content h1");
	private By logout = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean registerTheUser(String fName, String lName, String telephone, String password, String isSubscriber) {
		driver.findElement(firstName).sendKeys(fName);
		eleUtil.doSendkeys(lastName, lName);
		driver.findElement(emailID).sendKeys(StringUtil.getRandomEmail());
		eleUtil.doSendkeys(telephoneNmb, telephone);
		eleUtil.doSendkeys(pwd, password);
		eleUtil.doSendkeys(cfmPwd, password);
		if (isSubscriber.equalsIgnoreCase("Yes")) {
			eleUtil.doClickElement(subscribeYes);
		} else {
			eleUtil.doClickElement(subscribeNo);
		}

		eleUtil.doClickElement(agreeCheckbox);
		eleUtil.doClickElement(continueBtn);
		String successMsg = driver.findElement(successMessage).getText();

		if (successMsg.contains("Your Account Has Been Created!")) {
			driver.findElement(logout).click();
			driver.findElement(registerLink).click();
			return true;
		}
		return false;
	}
}
