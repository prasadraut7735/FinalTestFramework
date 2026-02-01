package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {

	private WebDriver driver;
	private By resultProduct = By.xpath("//div[@class='product-thumb']//h4//a");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getResultProductCount() {
		int resultCount = driver.findElements(resultProduct).size();
		System.out.println("Product result count is: " + resultCount);
		return resultCount;
	}

	public ProductInfoPage selectProduct(String productKey) {
		driver.findElement(By.linkText(productKey)).click();
		return new ProductInfoPage(driver);
	}
}
