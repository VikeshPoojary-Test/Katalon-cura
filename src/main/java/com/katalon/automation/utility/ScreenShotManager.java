package com.katalon.automation.utility;

import com.katalon.automation.common.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShotManager {

    public void getScreenShot(String name){
        File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        File dstFile = new File(System.getProperty("user.dir" + "\\src\\test\\java\\Results\\"+name+".png"));
        try {
            FileUtils.copyFile(srcFile, dstFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getScreenShotWithBase64(){
        return  ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
