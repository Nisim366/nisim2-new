package QADEMO.tests.part3.forms;


import demo.qa.forms.PracticeFormPage;
import org.junit.jupiter.api.Test;
import QADEMO.Base.BaseTest_QADEMO;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class RadioButtonTest extends BaseTest_QADEMO {


    @Test
    public void GoToPracticeFormPage() {
        homePage.goToFormsPage().clickPracticeFormPage();
    }

    @Test
    public void clickingRadioButton() {
        PracticeFormPage practice = homePage.goToFormsPage().clickPracticeFormPage();

        practice.clickFemaleRadioButton();
        assertTrue(practice.isFemaleRadioButtonSelected(), "Female radio button not selected");
        assertFalse(practice.isMaleRadioButtonSelected(), "Male radio button should not be selected");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other radio button should not be selected");

        practice.clickMaleRadioButton();
        assertTrue(practice.isMaleRadioButtonSelected(), "Male radio button not selected");
        assertFalse(practice.isFemaleRadioButtonSelected(), "Female radio button should not be selected");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other radio button should not be selected");

        practice.clickOtherRadioButton();
        assertTrue(practice.isOtherRadioButtonSelected(), "Other radio button not selected");
        assertFalse(practice.isFemaleRadioButtonSelected(), "Female radio button should not be selected");
        assertFalse(practice.isMaleRadioButtonSelected(), "Male radio button should not be selected");

        System.out.println("\u001B[32m=== TEST clickingRadioButton PASSED ===\u001B[0m");

    }
    @Test
    public void doubleClickRadioButton(){
        PracticeFormPage practice = homePage.goToFormsPage().clickPracticeFormPage();

        practice.clickFemaleRadioButton();
        assertTrue(practice.isFemaleRadioButtonSelected(), "Female radio button not selected");
        assertFalse(practice.isMaleRadioButtonSelected(), "Male radio button should not be selected");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other radio button should not be selected");

        practice.clickFemaleRadioButton();
        assertTrue(practice.isFemaleRadioButtonSelected(), "Female radio button not selected");
        assertFalse(practice.isMaleRadioButtonSelected(), "Male radio button should not be selected");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other radio button should not be selected");

        System.out.println("\u001B[32m=== TEST doubleClick PASSED ===\u001B[0m");


    }

    @Test
    public void quickClickRadioButton () {
        PracticeFormPage practice = homePage.goToFormsPage().clickPracticeFormPage();

        Actions actions = new Actions(driver);
        actions.moveToElement(practice.getFemaleRadioButton()).doubleClick().perform();

        assertTrue(practice.isFemaleRadioButtonSelected(), "Female radio button not selected");
        assertFalse(practice.isMaleRadioButtonSelected(), "Male radio button should not be selected");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other radio button should not be selected");

        System.out.println("\u001B[32m=== TEST quickClick  PASSED ===\u001B[0m");
    }

    @Test
    public void RadioButtonSelection() {
        PracticeFormPage practice = homePage.goToFormsPage().clickPracticeFormPage();

        practice.clickFemaleRadioButton();
        assertTrue(practice.isFemaleRadioButtonSelected(), "Female radio button should be selected");
        assertFalse(practice.isMaleRadioButtonSelected(), "Male radio button should not be selected");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other radio button should not be selected");

        practice.clickMaleRadioButton();
        assertTrue(practice.isMaleRadioButtonSelected(), "Male radio button should be selected");
        assertFalse(practice.isFemaleRadioButtonSelected(), "Female radio button should not be selected");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other radio button should not be selected");

        practice.clickOtherRadioButton();
        assertFalse(practice.isMaleRadioButtonSelected(), "Male radio button should not be selected");
        assertFalse(practice.isFemaleRadioButtonSelected(), "Female radio button should not be selected");
        assertTrue(practice.isOtherRadioButtonSelected(), "Other radio button should be selected");

        System.out.println("\u001B[32m=== TEST testRadioButtonSelection PASSED ===\u001B[0m");
    }
    @Test
    public void initialStateShouldBeUnchecked() {
        PracticeFormPage practice = homePage.goToFormsPage().clickPracticeFormPage();

        assertFalse(practice.isFemaleRadioButtonSelected(), "Female should not be selected by default");
        assertFalse(practice.isMaleRadioButtonSelected(), "Male should not be selected by default");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other should not be selected by default");

        System.out.println("\u001B[32m=== TEST initialStateShouldBeUnchecked PASSED ===\u001B[0m");

    }

    @Test
    public  void  refreshPage(){
        PracticeFormPage practice = homePage.goToFormsPage().clickPracticeFormPage();

        practice.clickFemaleRadioButton();
        assertTrue(practice.isFemaleRadioButtonSelected(), "Female radio button should be selected");
        assertFalse(practice.isMaleRadioButtonSelected(), "Male radio button should not be selected");
        assertFalse(practice.isOtherRadioButtonSelected(), "Other radio button should not be selected");

        driver.navigate().refresh();
        assertFalse(practice.isFemaleRadioButtonSelected(), "Female radio button should not be selected");

        System.out.println("\u001B[32m=== TEST refreshPage PASSED ===\u001B[0m");
    }



}
