package com.automationexercise.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    @FindBy(css = "li a[href='/login']")
    WebElement signUpLink;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private By consentButtonBy = By.xpath("//button[@aria-label='Consent']");
    static Logger log = LoggerFactory.getLogger(HomePage.class);

    /**
     * Method to click on the consent button
     */
    public void clickConsentButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement consentBtn = wait.until(ExpectedConditions.elementToBeClickable(consentButtonBy));
            consentBtn.click();
        } catch (Exception e) {
            log.info("Consent banner not found or already handled.");
        }
    }

    /**
     * Method to click on the Signup link
     */
    public void clickSignupLogin() {
        signUpLink.click();
        log.info("Clicked Signup link in Home page");
    }
}
