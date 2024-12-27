package com.katalon.automation.scripts;

import com.katalon.automation.BaseTest;
import com.katalon.automation.pages.KatalonAppointmentPage;
import com.katalon.automation.pages.KatalonHomePage;
import com.katalon.automation.pages.KatalonLoginPage;
import org.testng.annotations.Test;

public class LoginKatalonTest extends BaseTest {

    @Test
    public void testMethod(){

        KatalonHomePage katalonHomePage = new KatalonHomePage();
        katalonHomePage.getPageName();
        katalonHomePage.clickAppointmentButton();

        KatalonLoginPage katalonLoginPage = new KatalonLoginPage();
        String username = katalonLoginPage.getLoginName();
        String password = katalonLoginPage.getLoginPassword();
        katalonLoginPage.login(username,password);

        KatalonAppointmentPage appointmentPage = new KatalonAppointmentPage();
//        appointmentPage.selectDateFromCalender("22/12/2024", "dd/MM/yyyy");
        appointmentPage.fillForm("Hongkong CURA Healthcare Center", "Medicaid", "Test text area",
                "22/10/2026", "dd/MM/yyyy");
    }
}
