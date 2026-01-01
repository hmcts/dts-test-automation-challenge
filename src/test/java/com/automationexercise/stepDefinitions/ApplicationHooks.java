package com.automationexercise.stepDefinitions;

import com.automationexercise.supportLibraries.DriverFactory;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

public class ApplicationHooks extends MasterStepDef {

    @Before
    public void setUp(Scenario scenario) {
        DriverFactory.initDriver();
        currentScenario.set(scenario);
    }

    @After
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
               /* final byte[] screenshotBytes = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", "Failed Step Screenshot");
                byte[] screenshotBytes = FileUtils.readFileToByteArray(new File(screenshotPath));
                scenario.attach(screenshotBytes, "image/png", scenarioName);*/
                File screenshotDir = new File("Reports/screenshots");
                if (!screenshotDir.exists()) screenshotDir.mkdirs();
                String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9_-]", "_");
                String screenshotPath = "Reports/screenshots/" + scenarioName + ".png";
                File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFile, new File(screenshotPath));
                ExtentCucumberAdapter.getCurrentStep().fail(MediaEntityBuilder.createScreenCaptureFromPath("./screenshots/" + scenarioName + ".png").build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Quit driver
    @After(order = 0)
    public void tearDown() {
        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
        }
    }
}
