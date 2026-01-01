package com.automationexercise.pages;

import com.automationexercise.supportLibraries.CommonFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonFunctions {

    WebDriver driver;

    @FindBy(css = "input[name='name']")
    private WebElement userSignUpName;

    @FindBy(css = ".signup-form input[name='email']")
    private WebElement userSignUpEmail;

    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signUpButton;

    @FindBy(css = ".login-form input[name='email']")
    private WebElement loginEmail;

    @FindBy(css = ".login-form input[name='password']")
    private WebElement loginPassword;

    @FindBy(css = ".login-form button")
    private WebElement loginButton;

    @FindBy(css = ".login-form p")
    private WebElement errorMessageText;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    static Logger log = LoggerFactory.getLogger(LoginPage.class);

    public void userSignUp(String userName, String email){
        userSignUpName.sendKeys(userName);
        log.debug("Entered Name - {}", userName);
        userSignUpEmail.sendKeys(email);
        log.debug("Entered Email - {}", email);
        signUpButton.click();
        log.debug("Clicked Signup button");
    }

    public void login(String email, String password){
        loginEmail.sendKeys(email);
        log.debug("Entered user name - {}", email);
        loginPassword.sendKeys(password);
        log.debug("Entered password - {}", password);
        scrollToElement(loginButton);
        loginButton.click();
        log.debug("Clicked Login button");
    }

    public String getErrorMessage(){
        waitForVisibility(errorMessageText);
        return errorMessageText.getText();
    }
}
