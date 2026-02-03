package com.qa.openkart.pages;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By email = By.cssSelector("#input-email");
	private final By pwd = By.cssSelector("#input-password");
	private final By loginBtn = By.cssSelector("input.btn-primary");
	private final By forgetPwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");
	private final By logo = By.id("logo");

	private static final Logger log = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getLoginPageTitle() {
		String title = driver.getTitle();
		log.info("Login page title is : " + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = driver.getCurrentUrl();
		log.info("Login page url is: " + url);
		return url;
	}

	public boolean isForgetPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgetPwdLink);
	}

	public boolean isLogoExist() {
		return eleUtil.isElementDisplayed(logo);
	}

	public RegistrationPage navigateToRegistrationPage() {
		eleUtil.doClickElement(registerLink);
		return new RegistrationPage(driver);
	}

	public AccountsPage doLogin(String username, String password) {
		eleUtil.waitForElementVisible(email, 10);
		eleUtil.doSendkeys(email, username);
		eleUtil.doSendkeys(pwd, password);
		eleUtil.doClickElement(loginBtn);
		return new AccountsPage(driver);
	}
}
