package cucumberOptions;

import static automationFramework.StartDriver.closeDriver;
import static automationFramework.StartDriver.initializeWeb_Driver;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import automationFramework.Config;
import automationFramework.LoggerHelper;
import automationFramework.Utils;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features="src//test//java//Features",
		glue="stepDefinations",
		dryRun = false,
		monochrome = true,
				plugin = {
						"pretty",
						"json:target/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" } ,tags = "@fast" 
		)
public class TestRunner extends AbstractTestNGCucumberTests   {
private static Logger log = LoggerHelper.getLogger(TestRunner.class);
	
	@BeforeClass
	public static void startPoint() throws Throwable {	
		log.info("Execution Starts at: " +Utils.getCurrentDateTime());	
		Utils.deleteDir(Config.fol_failedScreenshotPath);
		Utils.deleteDir(Config.fol_passScreenshotPath);
		Utils.deleteDir(Config.fol_downloadFilePath);
		initializeWeb_Driver();
	}
	
	@AfterClass
	public static void endPoint() {
		log.info("Execution Ends at: " +Utils.getCurrentDateTime());
		closeDriver();
	}
}
