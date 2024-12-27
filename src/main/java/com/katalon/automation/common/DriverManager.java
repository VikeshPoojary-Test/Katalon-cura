package com.katalon.automation.common;

import com.katalon.automation.utility.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public final class DriverManager {
    final static ThreadLocal<WebDriver> thread = new ThreadLocal<>();

    public synchronized static WebDriver getDriver() {
        if (thread.get() == null) {
            createDriver();
        }
        return thread.get();
    }

    public synchronized static WebDriver createDriver() {
        WebDriver driver = null;
        String key = PropertyReader.getBrowserProperty("browser");
        System.out.println("KEY ---------------------------------- " + key);
        if (key.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (key.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        thread.set(driver);
        return driver;
    }


}
