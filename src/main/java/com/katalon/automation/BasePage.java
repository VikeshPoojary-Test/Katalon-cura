package com.katalon.automation;

import com.katalon.automation.common.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


public class BasePage  {

    public void click(By webElement){
        DriverManager.getDriver().findElement(webElement).click();
    }

    public void fillField(By webElement, String value){
        DriverManager.getDriver().findElement(webElement).sendKeys(value);
    }

    public String getTextValue(By webElement){
        return DriverManager.getDriver().findElement(webElement).getText();
    }

    public void getUrl(){
        DriverManager.getDriver().get("https://katalon-demo-cura.herokuapp.com/");
    }

    public void selectDropdown(By webelement, String value){
        Select select = new Select(DriverManager.getDriver().findElement(webelement));
        select.selectByVisibleText(value);
    }




}
