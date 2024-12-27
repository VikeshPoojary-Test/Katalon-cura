package com.katalon.automation.pages;

import com.katalon.automation.BasePage;
import org.openqa.selenium.By;

public class KatalonHomePage extends BasePage {

    private final By makeAppointmentBtn = By.linkText("Make Appointment");
    private final By menuBtn = By.cssSelector("#menu-toggle");
    private final By addressDetails = By.cssSelector("[class='col-lg-10 col-lg-offset-1 text-center']");
    private final By headerName = By.xpath("//div//h1");
    private final By menuContent = By.cssSelector(".sidebar-nav li");


    public void getPageName() {
        System.out.println(getTextValue(headerName));
    }

    public void clickAppointmentButton() {
        click(makeAppointmentBtn);
    }

}
