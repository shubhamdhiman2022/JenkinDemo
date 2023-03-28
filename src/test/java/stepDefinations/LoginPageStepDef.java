package stepDefinations;

import static automationFramework.StartDriver.driver;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import static automationFramework.DataReader.getParameterString;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;


public class LoginPageStepDef {

	LoginPage loginPage=new LoginPage(driver);
	
	@Given("naviagte to dummy site")
	public void naviagte_to_dummy_site() throws Exception {
		loginPage.navigateToGoogle();
	}
	@When("User enter username and password and submit")
	public void user_enter_username_and_password_and_submit() throws Exception {
		loginPage.enterusernameAndPass();
	}
	@Then("Verify user logged in successfully")
	public void verify_user_logged_in_successfully() {
		loginPage.verifyloggedin();
	}
	@Then("verify failure")
	public void verify_failure() {
		loginPage.failedscenrios();
	}


	}
