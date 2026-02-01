package com.qa.openkart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;
import com.qa.openkart.utils.ExcelUtil;

public class UserRegisterTest extends BaseTest {

	@BeforeClass
	public void regSetUp() {
		registerpage = loginpage.navigateToRegistrationPage();
	}

	@DataProvider
	public Object[][] getDataFromExcel() {
		return ExcelUtil.getExcelData("Sheet1");
	}

	@Test(dataProvider = "getDataFromExcel")
	public void userRegisterTest(String fname, String lName, String telephone, String passport, String isSubscribe) {
		registerpage.registerTheUser(fname, lName, telephone, passport, isSubscribe);
	}
}
