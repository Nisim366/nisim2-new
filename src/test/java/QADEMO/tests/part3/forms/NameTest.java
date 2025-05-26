package QADEMO.tests.part3.forms;

import QADEMO.Base.BaseTest_QADEMO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NameTest extends BaseTest_QADEMO {

    @Test
    public  void enterFirstName(){
        String expectedName = "Nisim";

        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clearFirstName();
        practice.enterFirstName(expectedName);
        String actualName = practice.getFirstNameValue();
        Assertions.assertEquals(expectedName, actualName, "First name field value does not match the expected input.");

        System.out.println("\u001B[32m=== TEST enterFirstName PASSED ===\u001B[0m");

    }
    @Test
    public  void enterLastName(){
        String expectedLastName = "Ben";

        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clearLastName();
        practice.enterLasttName(expectedLastName);
        String actualLastName = practice.getLastNameValue();
        Assertions.assertEquals(expectedLastName, actualLastName, "First name field value does not match the expected input.");

        System.out.println("\u001B[32m=== TEST enterLastName PASSED ===\u001B[0m");

    }
    @Test
    public  void deleteLastCharacter(){
        String enterdName = "Nisim";

        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clearFirstName();
        practice.enterFirstName(enterdName);
        String actualName = practice.getFirstNameValue();
        Assertions.assertEquals(enterdName, actualName, "First name field value does not match the expected input.");

        practice.deleteLastCharactersFromFirstName(2);
        String actualValue = practice.getFirstNameValue();
        Assertions.assertTrue(actualValue.length() == 3, "Expected the field to have fewer characters after deletion.");

        System.out.println("\u001B[32m=== TEST deleteLastCharacter PASSED ===\u001B[0m");
    }
    @Test
    public void enterValueAndDelete() {
        String enteredName = "Nisim";

        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clearFirstName();
        practice.enterFirstName(enteredName);
        String actualName = practice.getFirstNameValue();
        Assertions.assertEquals(enteredName, actualName, "First name field value does not match the expected input.");

        practice.clearFirstName();
        Assertions.assertFalse(practice.isFirstNameEntered(),"First name field should be empty after clearing." );
    }
    @Test
    public  void refreshPage(){
        String enteredName = "Nisim";

        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clearFirstName();
        practice.enterFirstName(enteredName);
        String actualName = practice.getFirstNameValue();
        Assertions.assertEquals(enteredName, actualName, "First name field value does not match the expected input.");

        driver.navigate().refresh();
        assertFalse(practice.isFirstNameEntered(), "First name field should be clear");

        System.out.println("\u001B[32m=== TEST refreshPage PASSED ===\u001B[0m");

    }
    @Test
    public  void flow(){
        String expectedName = "Nisim";
        String expectedLastName = "Ben";

        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clearFirstName();
        practice.clearLastName();
        practice.enterFirstName(expectedName);
        practice.enterLasttName(expectedLastName);
        String actualName = practice.getFirstNameValue();
        String actualLastName = practice.getLastNameValue();

        Assertions.assertEquals(expectedName, actualName, "First name field value does not match the expected input.");
        Assertions.assertEquals(expectedLastName, actualLastName, "First name field value does not match the expected input.");

    }

}
