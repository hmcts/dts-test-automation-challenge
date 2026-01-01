package com.automationexercise.pages;

import com.automationexercise.supportLibraries.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShoppingPage extends CommonFunctions {
    WebDriver driver;

    @FindBy(css = "li a b")
    private WebElement logInUser;


    public ShoppingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    static Logger log = LoggerFactory.getLogger(ShoppingPage.class);
    /**
     * Method to get Login user name
     * @return user name
     */
    public String getLogInUser(){
        return logInUser.getText();
    }
}
