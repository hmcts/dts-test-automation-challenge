package com.automationexercise.stepDefinitions;

import com.automationexercise.supportLibraries.DriverFactory;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class MasterStepDef {
    static Logger log = LoggerFactory.getLogger(MasterStepDef.class);
    protected static ThreadLocal<Scenario> currentScenario  = new ThreadLocal<>();

    public void addStepScreenshot(){
        String screenshotName = currentScenario.get().getName().replaceAll("[^a-zA-Z0-9_-]", "_")
                + String.valueOf(System.currentTimeMillis());
        try{
            File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "Reports/screenshots/" + screenshotName + ".png";
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            ExtentCucumberAdapter.getCurrentStep().pass(MediaEntityBuilder.createScreenCaptureFromPath("./screenshots/" + screenshotName + ".png").build());
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }
}
