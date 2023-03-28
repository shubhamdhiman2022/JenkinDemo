package automationFramework;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import static automationFramework.Config.*;
import static automationFramework.PageActions.zoomOutwebPage;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StartDriver {
	public static WebDriver driver;

	/*
	 * Description: Initialize Driver
	 */
	public static void initializeWeb_Driver()
			throws IOException, ParseException, org.json.simple.parser.ParseException, InterruptedException {
		String browser = "chrome";
		//DataReader.getParameterString("browser", "environment");
		if ((browser.equalsIgnoreCase("chrome"))) {
			WebDriverManager.chromedriver().setup();
			
			Map<String, Object> preferences = new Hashtable<String, Object>();
			preferences.put("profile.default_content_settings.popups", 0);
			preferences.put("download.default_directory", fol_downloadFilePath);
			preferences.put("download.prompt_for_download", "false");

			// disable flash and the PDF viewer
			preferences.put("plugins.plugins_disabled", new String[]{
			    "Adobe Flash Player", "Chrome PDF Viewer"});
			
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("-incognito");
			options.addArguments("start-maximized");
			options.setExperimentalOption("excludeSwitches",
				    Arrays.asList("disable-popup-blocking"));
			options.addArguments("--remote-allow-origins=*");
			options.setExperimentalOption("prefs", preferences);
			//For headless mode
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setAcceptInsecureCerts(true);
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		}

		else if ((browser.equalsIgnoreCase("firefox"))) {
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setAcceptInsecureCerts(true);
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--start-maximized");
			driver = new FirefoxDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		}

	}


	public static void closeDriver() {
		driver.quit();
	}
}
