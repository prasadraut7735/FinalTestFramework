package com.qa.openkart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductInfoPage {

	private WebDriver driver;
	private Map<String, String> productMap;
	private By header = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By priceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getProductHeader() {
		String headerText = driver.findElement(header).getText();
		System.out.println("Product header is: " + headerText);
		return headerText;
	}

	public int getProductImageCount() {
		int productImagesCount = driver.findElements(productImages).size();
		System.out.println("Total images count: " + productImagesCount);
		return productImagesCount;
	}

	public Map<String, String> getProductMetaData() {
		productMap = new LinkedHashMap<String, String>();
		List<WebElement> eleList = driver.findElements(productMetaData);
		for (WebElement e : eleList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
		return productMap;
	}

	public Map<String, String> getPriceData() {
		productMap = new LinkedHashMap<String, String>();
		List<WebElement> eleList = driver.findElements(priceData);
		String productPrice = eleList.get(0).getText();
		String extPrice = eleList.get(1).getText().split(":")[1].trim();
		productMap.put("Product Price", productPrice);
		productMap.put("Ext Price", extPrice);
		return productMap;
	}
}
