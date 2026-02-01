package com.qa.openkart.tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

@Feature("CPD-87432 Login Page")

@Epic("CPD-65372 Group view implementation")
@Story("CPD-83742 Implementation of the 'HW Request' tab")
public class LoginPageTest extends BaseTest {

	@Description("CPD-76324 Login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, "Account Login");
	}

	@Issue("CPD-23475")
	@TmsLink("CPD-23598")
	@Test(description = "CPD-1234")
	public void loginPageUrlTest() {
		String actUrl = loginpage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains("route=account/login"));
	}

	@Step("Checking forget password link exist or not")
	@Test(description = "Forgot Password Check")
	public void ForgetPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgetPwdLinkExist());
	}

	@Step("Login with valid username: {0} and password: {1}")
	@Test(enabled = false)
	public void loginTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accpage.getAccPageTitle(), "My Account");
	}
}
