package com.katalon.automation.scripts;

import com.katalon.automation.BaseTest;
import com.katalon.automation.pages.KatalonHomePage;
import com.katalon.automation.pages.KatalonLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginKatalonFailTest extends BaseTest {

    @Test
    public void failScnerio(){

        KatalonHomePage katalonHomePage = new KatalonHomePage();
        katalonHomePage.getPageName();
        katalonHomePage.clickAppointmentButton();

        KatalonLoginPage katalonLoginPage = new KatalonLoginPage();
        String username = katalonLoginPage.getLoginName();
        String password1 = katalonLoginPage.getLoginPassword();
        String password = password1+"S";
        katalonLoginPage.login(username,password);

        String failLogin = katalonLoginPage.failMessage();
        Assert.assertEquals(failLogin, "Login failed! Please ensure the username and password are valid..");
    }
}
