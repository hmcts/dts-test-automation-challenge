package com.automationexercise.supportLibraries;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CommonFunctions {

    /**
     * Method to scroll to a webelement
     * @param element web element
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(1000);
    }

    /**
     * Method to sleep
     * @param timeOut timeout
     */
    public void sleep(long timeOut){
        try{
            Thread.sleep(timeOut);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Method to hover element
     * @param element WebElement
     */
    public void hover(WebElement element) {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).perform();
        sleep(1000);
    }

    /**
     * Mehtod to wait for visibility
     * @param element WebElement
     */
    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
