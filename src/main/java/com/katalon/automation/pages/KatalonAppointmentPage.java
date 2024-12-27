package com.katalon.automation.pages;

import com.katalon.automation.BasePage;
import com.katalon.automation.common.DriverManager;
import com.katalon.automation.utility.DatePicker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.IntStream;

public class KatalonAppointmentPage extends BasePage {
    private final By facility_Dropdown = By.xpath("//select[@id='combo_facility']");
    private final By readmission_Checkbox = By.xpath("//input[@id='chk_hospotal_readmission']");
    private final By healthcareProgram_RadioBtns = By.xpath("//div[@class='col-sm-4']/label");
    private final By textArea = By.cssSelector("textarea");
    private final By bookAppointment_Btn = By.cssSelector("button#btn-book-appointment");

    // Calender Page objects
    private final By selectCalenderDate = By.xpath("//table[@class='table-condensed']/tbody/tr/td[not(contains(@class,'old'))" +
            " and not(contains(@class,'new'))]");
    private final By calenderPrevBtn = By.cssSelector(".datepicker-days .prev");
    private final By calenderNextBtn = By.cssSelector(".datepicker-days .next");
    private final By actualMonthandYear = By.cssSelector(".datepicker-days .datepicker-switch");

    // Confirmation Page
    private final By confirmMessage = By.cssSelector("h2");


    DatePicker datePicker = new DatePicker();

    public void selectDateFromCalender(String targetDate, String dateFormat) {
        datePicker.selectDate(targetDate, dateFormat, actualMonthandYear,
                calenderPrevBtn, calenderNextBtn, selectCalenderDate);
    }

    public void fillForm(String value, String radioBtnName, String textValue, String targetDate, String dateFormat) {
        selectDropdown(facility_Dropdown, value);
        click(readmission_Checkbox);

        List<WebElement> radioButtons = DriverManager.getDriver().findElements(healthcareProgram_RadioBtns);
        IntStream.range(0, radioButtons.size()).filter(i -> radioButtons.get(i).getText()
                .equalsIgnoreCase(radioBtnName)).forEach(i -> radioButtons.get(i).click());

        DriverManager.getDriver().findElement(By.cssSelector("[name='visit_date']")).click();
        selectDateFromCalender(targetDate, dateFormat);
        fillField(textArea, textValue);
        click(bookAppointment_Btn);
    }

    public void verifyAppointment() {
        getTextValue(confirmMessage);
    }
}
