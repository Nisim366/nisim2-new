package QADEMO.tests.part3.Widgets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import QADEMO.Base.BaseTest_QADEMO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest extends BaseTest_QADEMO {

    @ParameterizedTest
    @CsvSource({
            // בדיקות רגילות ותקינות
            "December, 12, 10, 1966, true",
            "January, 01, 1, 2025, true",
            "December, 12, 31, 2025, true",

            // תאריכים לא חוקיים (31 באפריל, 30 בפברואר)
            "April, 04, 31, 2025, false",
            "February, 02, 30, 2025, false",

            // שנה מעוברת
            "February, 02, 29, 2023, false", // לא תקין - לא שנה מעוברת

            // תאריכים עתידיים מאוד
            "January, 01, 1, 2100, true",

            // תאריכים לא קיימים (יום שלילי או מעל 31)
            "May, 05, 0, 2025, false",
            "June, 06, 32, 2025, false",

            // יום לא קיים במפורש
            "December, 12, 101, 2004, false"
    })


    public void testDateSelection(String month, String monthNumber, String day, String year, boolean isValidDay) {
        var datePickerPage = homePage.goToWidgets().clickDatePicker();
        datePickerPage.clickSelectDate();
        datePickerPage.selectYear(year);
        datePickerPage.selectMonth(month);

        if (isValidDay) {
            Assertions.assertTrue(datePickerPage.isDayInMonth(day), "Day " + day + " is not available in the calendar");
            datePickerPage.clickDay(day);

            day = String.format("%02d", Integer.parseInt(day));
            String expectedDate = monthNumber + "/" + day + "/" + year;
            String actualDate = datePickerPage.getDate();

            assertEquals(expectedDate, actualDate, "The selected date is incorrect");
            System.out.println("\u001B[32m=== TEST PASSED for date: " + expectedDate + " ===\u001B[0m");
        } else {
            Assertions.assertFalse(datePickerPage.isDayInMonth(day), "Day " + day + " should NOT be available in the selected month.");
            System.out.println("\u001B[32m=== TEST PASSED for invalid day: " + day + " ===\u001B[0m");
        }
    }





}

