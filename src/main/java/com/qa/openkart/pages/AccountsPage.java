package com.qa.openkart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By header = By.tagName("h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("button.btn-default");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public List<String> getAccPageHeader() {
		List<WebElement> eleList = driver.findElements(header);
		List<String> headerText = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			headerText.add(text);
		}
		return headerText;
	}

	public String getAccPageTitle() {
		eleUtil.waitForTitle(10, "My Account");
		String title = driver.getTitle();
		System.out.println("Account page title is: " + title);
		return title;
	}

	public String getAccPageUrl() {
		String url = driver.getCurrentUrl();
		System.out.println("Account page url is: " + url);
		return url;
	}

	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.doSendkeys(search, searchKey);
		eleUtil.doClickElement(searchIcon);
		return new SearchResultsPage(driver);
	}

}
