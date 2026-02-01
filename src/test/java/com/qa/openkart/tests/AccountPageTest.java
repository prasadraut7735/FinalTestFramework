package com.qa.openkart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.qa.openkart.constants.AppConstants.*;

import com.qa.openkart.base.BaseTest;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accpage.getAccPageTitle();
		Assert.assertEquals(actTitle, "My Account");
	}

	@Test
	public void accPageUrlTest() {
		String actUrl = accpage.getAccPageUrl();
		Assert.assertTrue(actUrl.contains("route=account/account"));
	}

	@Test
	public void accPageHeaderTest() {
		List<String> actHeaderList = accpage.getAccPageHeader();
		Assert.assertEquals(actHeaderList, EXPECTED_HEADER_LIST);
	}
}
