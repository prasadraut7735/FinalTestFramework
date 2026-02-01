package com.qa.openkart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.openkart.exception.BrowserException;

public class ElementUtil {

	private WebDriver driver;
	private Actions act;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doSendkeys(By locator, String inputText) {
		doClear(locator);
		getElement(locator).sendKeys(inputText);
	}

	private void doClear(By locator) {
		getElement(locator).clear();
	}

	public void doSendkeys(By locator, CharSequence inputText) {
		doClear(locator);
		getElement(locator).sendKeys(inputText);
	}

	public void doClickElement(By locator) {
		getElement(locator).click();
	}

	public void nullCheck(String value) {
		if (value == null || value.length() == 0) {
			throw new IllegalArgumentException("=====VALUE SHOULD NOT BE NULL=====");
		}
	}

	public String getElementText(By locator) {
		String text = getElement(locator).getText();
		if (text != null) {
			System.out.println("Element text is: " + text);
			return text;
		} else {
			System.out.println("===NULL TEXT===" + text);
			return null;
		}
	}

	public boolean isElementDisplayed(By locator) {
		try {
			getElement(locator).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("=====ELEMENT IS NOT DISPLAYED=====" + locator);
			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementSelected(By locator) {
		try {
			getElement(locator).isSelected();
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("=====ELEMENT IS NOT SELECTED=====" + locator);
			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementEnabled(By locator) {
		try {
			getElement(locator).isEnabled();
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("=====ELEMENT IS NOT ENABLED=====" + locator);
			e.printStackTrace();
			return false;
		}
	}

	public String doElementGetAttribute(By locator, String attributeName) {
		nullCheck(attributeName);
		String attributeValue = getElement(locator).getDomAttribute(attributeName);
		System.out.println("DOM Attribute is: " + attributeValue);
		return attributeValue;
	}

	public String doElementGetProperty(By locator, String propertyName) {
		nullCheck(propertyName);
		String propertyValue = getElement(locator).getDomProperty(propertyName);
		System.out.println("DOM Attribute is: " + propertyValue);
		return propertyValue;
	}

	/**
	 * Find Elements util
	 */

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println("ELement Count is: " + eleCount);
		return eleCount;
	}

	public List<String> getElementTextList(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			textList.add(text);
		}
		System.out.println("Text list is: " + textList);
		return textList;
	}

	public List<String> getElementTextList1(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			textList.addAll(Arrays.asList(text.split("\\R")));
		}
		System.out.println("Text list is: " + textList);
		return textList;
	}

	public boolean isElementPresent(By locator) {
		int eleCount = getElements(locator).size();
		if (eleCount == 1) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}

	public void checkElementDisplayed(By locator, int expectedCount) {
		int eleCount = getElements(locator).size();
		if (eleCount == expectedCount) {
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
	}

	public boolean doSearchMethod(By locator, String searchKey, String expectedKey) {
		List<WebElement> eleList = getElements(locator);
		boolean flag = false;
		if (eleList.size() == 0) {
			throw new NullPointerException("====NO SUGGETIONS FOR MENTIONED SEARCHKEY====");
		} else {

			for (WebElement e : eleList) {
				String text = e.getText();
				if (text.equalsIgnoreCase(expectedKey)) {
					e.click();
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			System.out.println("Suggestions Match");
			return true;
		} else {
			System.out.println("Suggestions Not Match");
			return false;
		}
	}

	public boolean clickedElementMenthod(By locator, String expectedTextClick) {
		boolean flag = false;
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.equalsIgnoreCase(expectedTextClick)) {
				e.click();
				flag = true;
				break;
			}
		}
		if (flag) {
			return true;
		} else {
			System.out.println("====Element is not found====");
			return false;
		}
	}

	/**
	 * Select base Drop-down util
	 */

	public Select getSelect(By locator) {
		return new Select(getElement(locator));
	}

	public boolean doSelectByIndex(By locator, int index) {
		try {
			getSelect(locator).selectByIndex(index);
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean doSelectByValue(By locator, String value) {
		try {
			getSelect(locator).selectByValue(value);
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean doSelectByVisibleText(By locator, String text) {
		try {
			getSelect(locator).selectByVisibleText(text);
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getDropDownOptionsCount(By locator) {
		return getSelect(locator).getOptions().size();
	}

	public List<String> getDropDownTextList(By locator) {
		List<WebElement> eleList = getSelect(locator).getOptions();
		List<String> textList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			textList.add(text);
		}
		System.out.println("TextList: " + textList);
		return textList;
	}

	/**
	 * Select drop-down using select class
	 */

	public boolean selectDropDown(By locator, String valueToBeSelected) {
		boolean flag = false;
		List<WebElement> eleList = getSelect(locator).getOptions();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.equalsIgnoreCase(valueToBeSelected)) {
				e.click();
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println("Value" + valueToBeSelected + "is selected");
			return true;
		} else {
			System.out.println("Value" + valueToBeSelected + "is not selected");
			return false;
		}
	}

	public boolean compareSelectBasedDropdownOptionsWithExpected(By locator, List<String> expectedTextList) {
		List<String> actTextList = new ArrayList<String>();
		List<WebElement> eleList = getSelect(locator).getOptions();
		for (WebElement e : eleList) {
			String text = e.getText();
			actTextList.add(text);
		}
		if (actTextList.containsAll(expectedTextList)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Select drop-down without select class
	 */

	public boolean doSelectNonSelectBasedDropdownValue(By locator, String valueToBeSelected) {
		boolean flag = false;
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.equalsIgnoreCase(valueToBeSelected)) {
				e.click();
				flag = true;
				break;
			}
		}
		if (flag) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to select the multiple value from Drop-down: 1. Single
	 * selection is allowed 2. Multiple Selection is allowed 3. Selection of all is
	 * also possible.
	 * 
	 * @param selectDropDown
	 * @param dropDownList
	 * @param valueToBeSelected
	 */
	public void multiSelectDropDown(By locator, By selectDropDown, String... valueToBeSelected) {
		doClickElement(selectDropDown);

		List<WebElement> eleList = getElements(locator);
		if (valueToBeSelected[0].equalsIgnoreCase("All")) {
			for (WebElement e : eleList) {
				e.click();
			}
		} else {
			for (WebElement e : eleList) {
				String text = e.getText();
				for (String value : valueToBeSelected) {
					if (text.equals(value)) {
						e.click();
						break;
					}
				}
			}
		}
	}

	/**
	 * Actions Utils
	 * 
	 * @return
	 */
	public Actions doMoveToElement(By locator) {
		return act.moveToElement(getElement(locator));
	}

	public void doActionsClick(By locator) {
		act.click(getElement(locator)).build().perform();
	}

	public void doActionsSendKeys(By locator, String textToBeEntered) {
		act.sendKeys(getElement(locator), textToBeEntered);
	}

	public void doActiobsSendKeysWithPause(By locator, String text, int pauseInterval) {
		char[] ch = text.toCharArray();
		for (char e : ch) {
			act.sendKeys(String.valueOf(e)).pause(pauseInterval).build().perform();
		}
	}

	public void handleParentSubMenu(By parentMeny, By subMenu) {
		doMoveToElement(parentMeny).click(getElement(subMenu)).build().perform();
	}

	public void hanle4LevelMenuHandling(By selectmenu, By level1, By level2, By level3) {
		doClickElement(selectmenu);
		doMoveToElement(level1).build().perform();
		act.pause(200);
		doMoveToElement(level2).build().perform();
		act.pause(200);
		doClickElement(level3);
	}

	/**
	 * Wait Utils
	 */
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param timeout
	 * @param locator
	 */

	public void waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param timeout
	 * @param locator
	 */
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void clickWithWait(By locator, int timeout) {
		waitForElementVisible(locator, timeout).click();
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void sendkeyswithWait(By locator, int timeout, String valueToBeEntered) {
		waitForElementVisible(locator, timeout).sendKeys(valueToBeEntered);
	}

	public void waitForAllElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitForAllElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * Wait alert utility
	 */

	public Alert waitForAlert(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(By locator, int timeout) {
		waitForAlert(locator, timeout).accept();
	}

	public void dismissAlert(By locator, int timeout) {
		waitForAlert(locator, timeout).dismiss();
	}

	public String getAlertText(By locator, int timeout) {
		return waitForAlert(locator, timeout).getText();
	}

	/**
	 * Wait For Title utility
	 */
	public boolean waitForTitleContains(int timeout, String fractionTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.titleContains(fractionTitle));
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean waitForTitle(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.titleIs(title));
		} catch (TimeoutException e) {
			return false;
		}
	}

	public String getTitleIs(String expTitle, int timeout) {
		if (waitForTitleContains(timeout, expTitle)) {
			return driver.getTitle();
		} else {
			throw new BrowserException("=====TITLE NOT MATCHED=====");
		}
	}

	/**
	 * Wait For URL utility
	 */
	public boolean waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.urlToBe(url));
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean waitForUrlContains(String fractionUrl, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.urlContains(fractionUrl));
		} catch (TimeoutException e) {
			return false;
		}
	}

	public String getUrl(String expfractionUrl, int timeout) {
		if (waitForUrlContains(expfractionUrl, timeout)) {
			return driver.getCurrentUrl();
		} else {
			throw new BrowserException("======TITLE NOT MATCHED======");
		}
	}

	/**
	 * Wait For Frame utility
	 */
	public void waitForFrame(int timeout, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrame(int timeout, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrame(int timeout, String frameName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public void waitForFrame(int timeout, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	/**
	 * Wait Window utility
	 */
	public boolean waitForWindow(int timeout, int expectedNumberOfWindow) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindow));
		} catch (TimeoutException e) {
			throw new BrowserException("===NUMBER OF WINDOW NOT MATCHED===");
		}
	}

	/**
	 * Fluent Wait
	 */
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForVisibilityOfElementWithPollingInterval(By locator, int pollingInterval, int timeout) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("====ELEMENT NOT FOUND EXCEPTION===");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @param pollingTime
	 * @return
	 */
	public void waitForPresenceOfElementWithPollingInterval(By locator, int timeout, int pollingInterval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("===ELEMENT NOT FOUND===");
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public boolean isPageLoaded(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("document.readyState=='complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
}
