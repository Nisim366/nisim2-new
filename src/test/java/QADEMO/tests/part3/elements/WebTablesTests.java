package QADEMO.tests.part3.elements;

import org.junit.jupiter.api.Test;
import QADEMO.Base.BaseTest_QADEMO;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebTablesTests extends BaseTest_QADEMO {
    private String ageTest = "0";
    private String salaryTest = "1";
    private String email = "alden@example.com";

    @Test
    public void setAgeTest() {
        var webTablePage = homePage.goToElements().clickWebTables();
        webTablePage.clickEdit(email);
        webTablePage.setAge(ageTest);
        webTablePage.clickSubmitButton();

        String actualAge = webTablePage.getTableAge(email);
        String expectedAge = ageTest;

        if (Objects.equals(actualAge, expectedAge)) {
            System.out.println("Test Passed: The age for email " + email + " was successfully updated to " + expectedAge + ".");
        } else {
            System.out.println("Test Failed: Expected age " + expectedAge + " but got " + actualAge + " for email " + email + ".");
        }
    }

    @Test
    public void setSalaryTest(){
        var webTablePage = homePage.goToElements().clickWebTables();
        webTablePage.clickEdit(email);
        webTablePage.setSalary(salaryTest);
        webTablePage.clickSubmitButton();

        String actualSalary = webTablePage.getTableSalary(email);
        String expectedSalary = salaryTest;

        if (Objects.equals(actualSalary, expectedSalary)) {
            System.out.println("Test Passed: The Salary for email " + email + " was successfully updated to " + expectedSalary + ".");
        } else {
            System.out.println("Test Failed: Expected Salary " + expectedSalary + " but got " + actualSalary + " for email " + email + ".");
        }




    }
}
