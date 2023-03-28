package automationFramework;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
	public static StringBuffer VERIFICATION_ERRORS = new StringBuffer();
	public static Integer EXPLICIT_WAIT_180_SEC = 180;
	public static Integer EXPLICIT_WAIT_120_SEC = 120;
	public static Integer EXPLICIT_WAIT_60_SEC = 60;
	public static Integer EXPLICIT_WAIT_10_SEC = 10;
	public static Integer EXPLICIT_WAIT_5_SEC = 5;
	public static JavascriptExecutor jsExecutor;

	/**
	 * Description: Wait till page load
	 * 
	 * @author aatish.slathia
	 */
	public static void waitTillPageLoad() {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_180_SEC));
		jsExecutor = (JavascriptExecutor) StartDriver.driver;
		// Wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = wd -> ((JavascriptExecutor) StartDriver.driver)
				.executeScript("return document.readyState").toString().equals("complete");
		// Get JS is Ready
		boolean jsReady = (Boolean) jsExecutor.executeScript("return document.readyState").toString()
				.equals("complete");
		// Wait Javascript until it is Ready!
		if (!jsReady) {
			System.out.println("JS in NOT Ready!");
			// Wait for Javascript to load
			wait.until(jsLoad);
		} else {
			sleep(5);
		}
	}

	/**
	 * @author aatish.slathia
	 * @description sleep
	 * @param seconds
	 */
	public static void sleep(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebElement waitUntilClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_10_SEC));
		wait.pollingEvery(Duration.ofSeconds(1));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static WebElement waitUntilClickable(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(waitTime));
		wait.pollingEvery(Duration.ofSeconds(1));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static Boolean verifyWebElementVisible(WebElement element, int explicitWait) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(explicitWait));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			VERIFICATION_ERRORS.append("Element: ").append(element)
					.append(" is not present on page \n -Caugth exception: ").append(e.getMessage()).append("\n\n");
			return false;
		}
	}

	public static Boolean verifylocatorPresent(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(time));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			VERIFICATION_ERRORS.append("Element: ").append(e.getMessage()).append("\n\n");
			return false;
		}
	}

	public static Boolean verifyWebElementVisibleWebElementBoolean(WebElement element) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_180_SEC));
		wait.pollingEvery(Duration.ofSeconds(1));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			VERIFICATION_ERRORS.append("Element: ").append(element)
					.append(" is not present on page \n -Caught exception: ").append(e.getMessage()).append("\n\n");
			return false;
		}
	}

	public static Boolean verifyWebElementByVisible(By by) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_10_SEC));
		wait.pollingEvery(Duration.ofSeconds(1));
		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			VERIFICATION_ERRORS.append("By: ").append(by).append(" is not visible on page \n -Caugth exception: ")
					.append(e.getMessage()).append("\n\n");
			return false;
		}
	}

	public static Boolean waitUntilTitleDisplayed(String title) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_10_SEC));
		wait.pollingEvery(Duration.ofSeconds(1));
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			;
			return false;
		}
	}

	public static void verifyAlertPresent(int waitTime) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(waitTime));
		try {
			wait.until(ExpectedConditions.alertIsPresent());

		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			VERIFICATION_ERRORS.append("By: ").append(" is not visible on page \n -Caugth exception: ")
					.append(e.getMessage()).append("\n\n");

		}
	}
	

	public static Boolean verifyWebElementByInVisible(By by) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_10_SEC));
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			VERIFICATION_ERRORS.append("By: ").append(by).append(" is not present on page \n -Caught exception: ")
					.append(e.getMessage()).append("\n\n");
			return false;
		}
	}

	public static Boolean verifyWebElementInVisible(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(waitTime));
		try {
			wait.until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			VERIFICATION_ERRORS.append("By: ").append(element).append(" is not present on page \n -Caught exception: ")
					.append(e.getMessage()).append("\n\n");
			return false;
		}
	}

	public static Boolean verifyPresenceOfElementByLocator(String xpathValue) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_10_SEC));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathValue)));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			VERIFICATION_ERRORS.append("By: ").append(" is not present on page \n -Caught exception: ")
					.append(e.getMessage()).append("\n\n");
			return false;
		}
	}

	public static boolean checkIfElementExists(WebDriver driver, By by) {
		boolean exist = false;
		exist = !driver.findElements(by).isEmpty();
		return exist;
	}

	public static void waitByNotVisible(By by) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_180_SEC));
		if (checkIfElementExists(StartDriver.driver, by)) {
			wait.pollingEvery(Duration.ofSeconds(1));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}
	}

	public static boolean verifyWebElementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(StartDriver.driver, Duration.ofSeconds(EXPLICIT_WAIT_120_SEC));
		wait.pollingEvery(Duration.ofSeconds(1));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException
				| ErrorHandler.UnknownServerException | TimeoutException e) {
			StartDriver.driver.navigate().refresh();
			VERIFICATION_ERRORS.append(
					"Element: " + element + " is not present on page \n -Caugth exception: " + e.getMessage() + "\n\n");
			return false;
		}
	}

}
