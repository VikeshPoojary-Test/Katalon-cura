package com.katalon.automation.utility;

import com.katalon.automation.BasePage;
import com.katalon.automation.common.DriverManager;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePicker extends BasePage {

    public void selectDate(String targetDate, String dateFormat, By presentDateWebElement,
                           By previousButton_Calender, By nextButton_Calender, By selectCalenderDate) {
        Date formattedTargetDate;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        try {
            simpleDateFormat.setLenient(false);
            formattedTargetDate = simpleDateFormat.parse(targetDate);
            System.out.println(formattedTargetDate);

            calendar.setTime(formattedTargetDate);

            int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
            int targetMonth = calendar.get(Calendar.MONTH);
            int targetYear = calendar.get(Calendar.YEAR);

            String presentDate = getTextValue(presentDateWebElement);
            System.out.println(presentDate);
            calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(presentDate));

            int presentMonth = calendar.get(Calendar.MONTH);
            int presentYear = calendar.get(Calendar.YEAR);

            while (targetMonth < presentMonth || targetYear < presentYear) {
                DriverManager.getDriver().findElement(previousButton_Calender).click();

                presentDate = getTextValue(presentDateWebElement);
                calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(presentDate));

                presentMonth = calendar.get(Calendar.MONTH);
                presentYear = calendar.get(Calendar.YEAR);
            }

            while (targetMonth > presentMonth || targetYear > presentYear) {
                DriverManager.getDriver().findElement(nextButton_Calender).click();

                presentDate = getTextValue(presentDateWebElement);
                calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(presentDate));

                presentMonth = calendar.get(Calendar.MONTH);
                presentYear = calendar.get(Calendar.YEAR);
            }

            DriverManager.getDriver().findElement(selectCalenderDate).click();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
