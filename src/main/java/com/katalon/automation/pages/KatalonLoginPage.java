package com.katalon.automation.pages;

import com.aventstack.extentreports.Status;
import com.katalon.automation.BasePage;
import com.katalon.automation.common.DriverManager;
import com.katalon.automation.utility.ExtentReportManager;
import org.openqa.selenium.By;

public class KatalonLoginPage extends BasePage {

    private final By loginName_Data = By.cssSelector(".alert-info [placeholder='Username']");
    private final By loginPassword_Data = By.cssSelector(".alert-info [placeholder='Password']");
    private final By userNameField = By.cssSelector("[name='username']");
    private final By passWordField = By.cssSelector("[name='password']");
    private final By loginButton = By.xpath("//button[@id='btn-login']");
    private final By failMessage = By.cssSelector(".lead.text-danger");

    public String getLoginName() {
//        return getTextValue(loginName_Data);
        return DriverManager.getDriver().findElement(loginName_Data).getAttribute("value");
    }

    public String getLoginPassword(){
//        return getTextValue(loginPassword_Data);
        return DriverManager.getDriver().findElement(loginPassword_Data).getAttribute("value");
    }

    public void login(String username, String password){
        fillField(userNameField,username);
        fillField(passWordField, password);
        click(loginButton);
        ExtentReportManager.getTest().log(Status.INFO,"User logged successfully");
    }

    public String failMessage(){
        return getTextValue(failMessage);
    }
}
