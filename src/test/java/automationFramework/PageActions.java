package automationFramework;

import static automationFramework.Waits.verifyWebElementPresent;
import static automationFramework.Waits.verifyWebElementVisibleWebElementBoolean;
import static automationFramework.Waits.waitTillPageLoad;
import static automationFramework.Waits.waitUntilClickable;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class PageActions extends StartDriver {
	public static String mainID;
	public static Logger log = Logger.getLogger(PageActions.class);

	/*
	 * Description: Verify the attribute value of an element
	 */
	public static void validateAttribute(WebElement element, String valueToBeCompared) {
		String actualvalue = element.getAttribute("title");
		log.info("Tool tip text: " + actualvalue);
		try {
			Assert.assertEquals(actualvalue, valueToBeCompared);
		} catch (Exception e) {
			log.info("its not working ");
		}
	}

	/*
	 * Description: Scroll to bottom of the page
	 */
	public static void scrollToBottomOfPage() {
		JavascriptExecutor executor = (JavascriptExecutor) StartDriver.driver;
		executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/*
	 * Description: Scroll to paticular element
	 */
	public static void executeJavaScriptScroll(WebElement element) throws InterruptedException {
		Waits.waitUntilClickable(element);
		String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
				+ "var elementTop = arguments[0].getBoundingClientRect().top;"
				+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";
		((JavascriptExecutor) StartDriver.driver).executeScript(scrollElementIntoMiddle, element);
	}

	/*
	 * Description: Java script function for click
	 */
	public static void executeJavaScriptClick(WebElement element) throws InterruptedException {
		WebDriverWait wait10sec = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(10));
		wait10sec.pollingEvery(Duration.ofSeconds(1));
		JavascriptExecutor executor = (JavascriptExecutor) StartDriver.driver;

		int attempts = 0;

		while (attempts < 20) {

			try {
				wait10sec.until(ExpectedConditions.elementToBeClickable(element));
				executor.executeScript("arguments[0].click();", element);
				break;
			} catch (StaleElementReferenceException e) {

			}
			attempts++;
		}

		if (attempts < 19) {

		} else {

		}
	}

	/**
	 * Description: Double click on element
	 */
	public static void doubleClick(WebElement element, String elementName) {
		if (waitUntilClickable(element) != null) {
			try {
				Actions act = new Actions(driver);
				act.doubleClick(element).perform();
				log.info("Double click on: " + elementName);
			} catch (AssertionError e) {
				assertTrue(verifyWebElementPresent(element), "----> Element is not present");
			}
		} else {
			log.info("Unable to click on: " + elementName);
			Assert.fail("Element not found");
		}
	}

	/**
	 * Description: Scroll to element and performing click
	 */
	public static void clickElement(WebElement element, String elementName) throws Exception {
		assertTrue(verifyWebElementPresent(element), "----> Element is present");
		if (waitUntilClickable(element) != null) {
			try {
				scrollToElement(element);
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript(
						"arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');", element);
				//executeJavaScriptClick(element);
				element.click();
				log.info("Clicking on: " + elementName);
			} catch (AssertionError | Exception e) {
				assertTrue(verifyWebElementPresent(element), "----> Element is not present");
				scrollToElement(element);
				executeJavaScriptClick(element);
				log.info("JS Clicking on: " + elementName);
			}
		} else {
			log.info("Unable to click on: " + elementName);
			Assert.fail("Element not found");
		}
	}

	// To access list method class name should be change for different projects
	/*
	 * public static void clickOnElementFormList(String wantedValue) throws
	 * Exception { WebElement
	 * element=driver.findElement(By.xpath("//*[@class='k-item' and text()='"+
	 * wantedValue +"']")); if (waitUntilClickable(element) != null) { try {
	 * assertTrue(verifyWebElementPresent(element), "----> Element is present");
	 * element.click(); log.info("Clicking on: " + wantedValue); } catch
	 * (AssertionError e) { assertTrue(verifyWebElementPresent(element),
	 * "----> Element is not present"); executeJavaScriptClick(element); } } else {
	 * log.info("Unable to click on: " + wantedValue);
	 * Assert.fail("Element not found"); } }
	 */
	
	public static void clickOnElementFormListWithId(String ULListId, String wantedValue) throws Exception {
		WebElement element = driver
				.findElement(By.xpath("//*[@id='" + ULListId + "']//*[text()='" + wantedValue + "']"));
		assertTrue(verifyWebElementPresent(element), "----> Element is present");
		if (waitUntilClickable(element) != null) {
			try {
				scrollToElement(element);
				element.click();
				log.info("Clicking on: " + wantedValue);
			} catch (Exception e) {
				assertTrue(verifyWebElementPresent(element), "----> Element is not present");
				executeJavaScriptClick(element);
			}
		} else {
			log.info("Unable to click on: " + wantedValue);
			Assert.fail("Element not found");
		}
	}

	public static void clickOnElementFormListWithClassName(String ULListClassName, String wantedValue) throws Exception {
		waitTillPageLoad();
		WebElement element = driver
				.findElement(By.xpath("//*[@class='" + ULListClassName + "']//*[text()='" + wantedValue + "']"));
		assertTrue(verifyWebElementPresent(element), "----> Element is present");
		if (waitUntilClickable(element) != null) {
			try {
				scrollToElement(element);
				element.click();
				log.info("Clicking on: " + wantedValue);
				executeJavaScriptClick(driver.findElement(By.xpath("//h2")));
			} catch (Exception e) {
				assertTrue(verifyWebElementPresent(element), "----> Element is not present");
				executeJavaScriptClick(element);
			}
		} else {
			log.info("Unable to click on: " + wantedValue);
			Assert.fail("Element not found");
		}
	}

	public static void clickOnElementFormListWithContainsTextByClassName(String ListClassName, String wantedValue)
			throws Exception {
		WebElement element = driver.findElement(
				By.xpath("//*[@class='" + ListClassName + "']//*[contains(text(),'" + wantedValue + "')]"));
		if (waitUntilClickable(element) != null) {
			try {
				assertTrue(verifyWebElementPresent(element), "----> Element is present");
				element.click();
				log.info("Clicking on: " + wantedValue);
			} catch (AssertionError e) {
				assertTrue(verifyWebElementPresent(element), "----> Element is not present");
				executeJavaScriptClick(element);
			}
		} else {
			log.info("Unable to click on: " + wantedValue);
			Assert.fail("Element not found");
		}
	}

	public static void clickOnDateFormCalenderWithClassName(String calenderClassName, String wantedDate)
			throws Exception {
		WebElement element = driver.findElement(
				By.xpath("//*[@class='" + calenderClassName + "']//td[@class='day' and text()='" + wantedDate + "']"));
		if (waitUntilClickable(element) != null) {
			try {
				assertTrue(verifyWebElementPresent(element), "----> Element is present");
				element.click();
				log.info("Clicking on: " + wantedDate);
			} catch (AssertionError e) {
				assertTrue(verifyWebElementPresent(element), "----> Element is not present");
				executeJavaScriptClick(element);
			}
		} else {
			log.info("Unable to click on: " + wantedDate);
			Assert.fail("Element not found");
		}
	}

	public static void clickOnOptionWithContainsTextBySelectId(String selectId, String wantedOption) throws Exception {
		WebElement element = driver
				.findElement(By.xpath("//*[@id='" + selectId + "']//option[contains(text(),'" + wantedOption + "')]"));
		if (waitUntilClickable(element) != null) {
			try {
				assertTrue(verifyWebElementPresent(element), "----> Element is present");
				element.click();
				log.info("Clicking on: " + wantedOption);
			} catch (AssertionError e) {
				assertTrue(verifyWebElementPresent(element), "----> Element is not present");
				executeJavaScriptClick(element);
			}
		} else {
			log.info("Unable to click on: " + wantedOption);
			Assert.fail("Element not found");
		}
	}

	public static void clickOnSaveButtonBySectionAttributeName(String sectionAttributeName) throws Exception {
		waitTillPageLoad();
		WebElement element = driver
				.findElement(By.xpath("//button[text()='Save' and @section='" + sectionAttributeName + "']"));
		if (waitUntilClickable(element) != null) {
			try {
				assertTrue(verifyWebElementPresent(element), "----> Element is present");
				element.click();
				log.info("Clicking on: " + sectionAttributeName + " Save Button");
			} catch (AssertionError | Exception e) {
				assertTrue(verifyWebElementPresent(element), "----> Element is not present");
				executeJavaScriptClick(element);
			}
		} else {
			log.info("Unable to click on: " + sectionAttributeName + " Save Button");
			Assert.fail("Element not found");
		}
	}

	/**
	 * Description :Method to switch to Child Window/Tab
	 * 
	 * @author aatish.slathia
	 */
	public static synchronized void switchWindow() {
		mainID = driver.getWindowHandle();
		Set<String> allID = driver.getWindowHandles();
		for (String id : allID) {
			if (!id.equals(mainID)) {
				driver.switchTo().window(id);
			}
		}
	}

	/**
	 * Description : Method click on tab key and click on enter
	 * 
	 * @author aatish.slathia
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	/*
	 * public static void pressTabKeyThenEnter() throws AWTException,
	 * InterruptedException { Robot robot = new Robot();
	 * robot.keyPress(KeyEvent.VK_TAB); robot.keyPress(KeyEvent.VK_ENTER);
	 * 
	 * }
	 */

	/**
	 * Description : Method click on enter
	 * 
	 * @author aatish.slathia
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public static void pressEnterKey(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}
	
	public static synchronized void switchWindowWithNewTab() {
		mainID = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
	}

	/**
	 * Description :Method to switch to Main Window/Tab
	 * 
	 * @author aatish.slathia
	 */
	public static synchronized void switchToMainWindow() {
		driver.switchTo().window(mainID);
	}

	/**
	 * Description: Scroll to element particular element
	 */
	public static void scrollToElement(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		} catch (Exception e) {
			System.out.println("Scroll till the failed");
		}
	}

	public static void scrollToElementAndHoverOnIt(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
		} catch (Exception e) {
			System.out.println("Scroll till the failed");
		}
	}
	
	public static void zoomOutwebPage()
	{
		try
		{
//			WebElement html = driver.findElement(By.tagName("html"));
//			//html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
//			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
//			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
//			JavascriptExecutor executor = (JavascriptExecutor)driver;
//			executor.executeScript("document.body.style.zoom = '0.90'");
			
			for(int i=0; i<2; i++){
		        Robot robot = new Robot();
		        robot.keyPress(KeyEvent.VK_CONTROL);
		        robot.keyPress(KeyEvent.VK_MINUS);
		        robot.keyRelease(KeyEvent.VK_CONTROL);
		        robot.keyRelease(KeyEvent.VK_MINUS);
		        }
			waitTillPageLoad();
		}
		catch (Exception e) {
			System.out.println("zoomOut web page failed");
		}
		
	}

	public static void scrollToElementAndHighlight(WebElement element) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			jsExecutor.executeScript("window.scrollBy(0,-100)");
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:pink');",
					element);
		} catch (Exception e) {
			System.out.println("Scroll till the failed");
		}
	}

	/*
	 * Description:Verifying Text
	 */
	public void verifyAlertText(WebElement element, String expectedText) {
		String actualText = element.getAttribute("innerHTML");
		System.out.println(actualText);
		Assert.assertTrue(actualText.contains(expectedText));
	}

	/**
	 * Description To Enter the Text to the Text filed
	 * 
	 * @author aatish.slathia
	 * @param element
	 * @param value
	 * @param elementName
	 */
	public static void typeText(WebElement element, String value, String elementName) {
		try {
			Waits.waitUntilClickable(element);
			assertTrue(verifyWebElementPresent(element), "----> Element is not present");
			scrollToElement(element);
			element.clear();
			log.info("Enter the value into " + elementName);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:pink');",
					element);
			element.sendKeys(value);
			log.info(value + " typed into " + elementName);
		} catch (AssertionError error) {
			log.info(" Unable to type " + value + " into " + elementName);
			Assert.fail("Unable to type " + elementName);
		} catch (Exception e) {
			log.info(" Unable to type " + value + " into " + elementName);
			Assert.fail("Unable to type " + elementName);
		}
	}

	public static String getFirstSelectedValueFromDropdown(WebElement element) {
		Select dropdown = new Select(element);
		String dropdownDefaultText = dropdown.getFirstSelectedOption().getText();
		return dropdownDefaultText;
	}

	public static int randomDate() {
		Random ran = new Random();
		return ran.nextInt(30) + 1;
	}

	public static void selectFromDropdownByVisibletext(WebElement element, String wantedOption) {

		if (verifyWebElementVisibleWebElementBoolean(element)) {
			scrollToElement(element);
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(wantedOption);
			log.info(wantedOption + " :Option selected");
		}

		else {
			System.out.println("Dropdown is not visible");
		}

	}

	public static String defaultValueOfDropdown(WebElement dropdownElement) {
		Select select = new Select(dropdownElement);
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();

		return defaultItem;

	}

	public static void allDropdownOptions(String selectXpath, ArrayList<String> options) {
		int size = driver.findElements(By.xpath(selectXpath)).size();

		for (int i = 1; i <= size; i++) {
			options.add(driver.findElement(By.xpath(selectXpath + "[" + i + "]")).getText());
		}

	}

	public static boolean isClickable(WebElement el) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void hoverOnElement(WebElement element, String elementName) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		log.info("Hover over: " + elementName);

	}

	/*
	 * Description: Mouse hover on element and click on the element
	 */
	public static void mouseHoverAndClick(WebElement element, String elementName ) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click().build().perform();
		log.info("Hover over and click on "+elementName);
	}

	public static void closeCurrentTabAndMoveToParentTab(String parentWin) {
		((JavascriptExecutor) driver).executeScript("window.close()");
		// driver.close();
		driver.switchTo().window(parentWin);
		driver.switchTo().defaultContent();
	}

	public static void selectFromDropdownByIndex(WebElement element, int index) {
		if (verifyWebElementVisibleWebElementBoolean(element)) {
			Select dropdown = new Select(element);
			dropdown.selectByIndex(index);
			log.info(index + " :index selected");
		}
	}

	public static void selectFromDropdownByValue(WebElement element, String wantedOption) {
		if (verifyWebElementVisibleWebElementBoolean(element)) {
			Select dropdown = new Select(element);
			dropdown.selectByValue(wantedOption);
			log.info("Selecting option: " + wantedOption);
		}
	}

	public static void clearText(WebElement element) {
		String clearText = Keys.chord(Keys.CONTROL, "a");
		element.sendKeys(clearText);
		element.sendKeys(Keys.BACK_SPACE);
	}

	/**
	 * Description :File upload by using the image path
	 * 
	 * 
	 * @param imagePath
	 */
	
	public static void uploadFile(String filePath) {
		StringSelection stringSelection = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean verifyFramePresent()
	{
		int frameCount=StartDriver.driver.findElements(By.tagName("iframe")).size();
		if(frameCount>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void switchToFrame(WebElement frameWebElement, String elementName) {
		try {
			if (verifyFramePresent()) {
				driver.switchTo().frame(frameWebElement);
				log.info("Switched to iframe");
			}
		}

		catch (Exception e) {
			e.getMessage();
			log.info("No iframe present");
			assertTrue(false, "No iframe present for element: " + elementName);
		}

	}

}
