package com.katalon.automation;

import com.katalon.automation.common.DriverManager;
import com.katalon.automation.utility.Listener;
import com.katalon.automation.utility.PropertyReader;
import com.katalon.automation.utility.RetryAnnotation;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({Listener.class})
public class BaseTest {


    @BeforeSuite
    public void beforeSuite() {

    }

    @BeforeMethod
    public synchronized void initBrowser() {
        WebDriver driver = DriverManager.createDriver();
        driver.manage().window().maximize();
        driver.get(PropertyReader.getBrowserProperty("url"));

    }

//    @AfterMethod
//    public synchronized void tearDown(ITestResult iTestResult) {
//        DriverManager.getDriver().quit();
//    }

}
