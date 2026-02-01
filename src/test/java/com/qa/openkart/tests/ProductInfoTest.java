package com.qa.openkart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productIndoSetUp() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object getProductData() {
		return new Object[][] {
			{"MacBook","MacBook"},
			{"MacBook","MacBook Pro"},
			{"MacBook","MacBook Air"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void productHeaderTest(String productSearchKey, String productName) {
		searchresultpage = accpage.doSearch(productSearchKey);
		productinfopage = searchresultpage.selectProduct(productName);
		String actHeader = productinfopage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}

	@Test
	public void productImageCountTest() {
		searchresultpage = accpage.doSearch("MacBook");
		productinfopage = searchresultpage.selectProduct("MacBook Pro");
		int actImageCount = productinfopage.getProductImageCount();
		Assert.assertEquals(actImageCount, 4);
	}

	@Test
	public void productMetaTest() {
		searchresultpage = accpage.doSearch("MacBook");
		productinfopage = searchresultpage.selectProduct("MacBook Pro");
		Map<String, String> actMeta = productinfopage.getProductMetaData();
		Assert.assertEquals(actMeta.get("Brand"), "Apple");
	}

	@Test
	public void productPriceTest() {
		searchresultpage = accpage.doSearch("MacBook");
		productinfopage = searchresultpage.selectProduct("MacBook Pro");
		Map<String, String> actPrice = productinfopage.getPriceData();
		Assert.assertEquals(actPrice.get("Product Price"), "$2,000.00");
	}
}
