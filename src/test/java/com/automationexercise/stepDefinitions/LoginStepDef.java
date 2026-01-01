package com.automationexercise.stepDefinitions;

import com.automationexercise.pages.*;
import com.automationexercise.utils.ConfigReader;
import com.automationexercise.supportLibraries.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginStepDef extends MasterStepDef {
    static Logger log = LoggerFactory.getLogger(LoginStepDef.class);
    WebDriver driver = DriverFactory.getDriver();
    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    ShoppingPage shoppingPage = new ShoppingPage(driver);

    @Given("user launches the application")
    public void userLaunchesTheApplication() {
        driver.get(ConfigReader.getProperty("baseUrl"));
        homePage.clickConsentButton();
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
        addStepScreenshot();
    }

    @When("user navigates to Signup Login page")
    public void userNavigateToSignupLoginPage() {
        homePage.clickSignupLogin();
        addStepScreenshot();
    }

    @When("User login with correct email {string} and correct password {string}")
    @When("User login with correct email {string} and incorrect password {string}")
    public void userLoginWithEmailAndPassword(String email, String password) {
        loginPage.login(email, password);
        addStepScreenshot();
    }

    @Then("user {string} is logged in")
    public void userIsLoggedIn(String user) {
        String actualUser = shoppingPage.getLogInUser();
        Assert.assertEquals(actualUser, user);
        addStepScreenshot();
    }

    @Then("verify error message {string}")
    public void verifyErrorMessage(String message) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualMessage, message,
                "Error message mismatch. Expected: '" + message + "', but found: '" + actualMessage + "'");
        addStepScreenshot();
    }
}
