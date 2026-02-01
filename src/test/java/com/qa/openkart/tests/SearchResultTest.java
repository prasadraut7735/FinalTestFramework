package com.qa.openkart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;

public class SearchResultTest extends BaseTest {

	@BeforeClass
	public void searchSetUp() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void searchResultTest() {
		searchresultpage = accpage.doSearch("MacBook");
		int actProductCount = searchresultpage.getResultProductCount();
		Assert.assertEquals(actProductCount, 3);
	}
}
