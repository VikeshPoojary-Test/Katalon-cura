package com.katalon.automation.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListeners implements IRetryAnalyzer {
    int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        System.out.println("Test case:" + result.getName());
        if (retryCount < Integer.parseInt(PropertyReader.getBrowserProperty("maxRetryCount").trim())) {
            retryCount++;
            return true;
        }
        return false;
    }

}
