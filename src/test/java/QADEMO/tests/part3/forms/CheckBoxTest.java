package QADEMO.tests.part3.forms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import QADEMO.Base.BaseTest_QADEMO;

public class CheckBoxTest extends BaseTest_QADEMO {

    @Test
    public void SelectSportCheckBox() {
        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clickSportCheckBox();
        Assertions.assertTrue(practice.isSportSelected(), "Sport checkbox should be selected");
        System.out.println("\u001B[32m=== TEST SPORT CHECKBOX PASSED ===\u001B[0m");
    }

    @Test
    public void SelectReadingCheckBox() {
        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clickReadingCheckBox();
        Assertions.assertTrue(practice.isReadingSelected(), "Reading checkbox should be selected");
        System.out.println("\u001B[32m=== TEST READING CHECKBOX PASSED ===\u001B[0m");
    }

    @Test
    public void SelectMusicCheckBox() {
        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clickMusicCheckBox();
        Assertions.assertTrue(practice.isMusicSelected(), "Music checkbox should be selected");
        System.out.println("\u001B[32m=== TEST MUSIC CHECKBOX PASSED ===\u001B[0m");
    }

    @Test
    public void UnclickAfterClicking() {
        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.clickReadingCheckBox();
        Assertions.assertTrue(practice.isReadingSelected(), "Reading checkbox should be selected");


        practice.unClickReadingCheckBox();
        Assertions.assertFalse(practice.isReadingSelected(), "Reading checkbox should be Unselected");

        System.out.println("\u001B[32m=== TEST UNCLICK AFTER CLICKING PASSED ===\u001B[0m");
    }

    @Test
    public void UnclickWithoutClicking() {
        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.unClickReadingCheckBox(); // בלי לבחור לפני כן
        Assertions.assertFalse(practice.isReadingSelected(), "Reading checkbox should be unselected");
        System.out.println("\u001B[32m=== TEST UNCLICK WITHOUT CLICKING PASSED ===\u001B[0m");
    }

    @Test
    public void SelectAllCheckBoxes() {
        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.unClickReadingCheckBox();
        practice.unClickSportCheckBox();
        practice.unClickMusicCheckBox();

        practice.clickMusicCheckBox();
        Assertions.assertTrue(practice.isMusicSelected(), "Music checkbox should be selected");
        Assertions.assertFalse(practice.isReadingSelected(), "Reading checkbox should not be selected");
        Assertions.assertFalse(practice.isSportSelected(), "Sport checkbox should not be selected");

        practice.clickReadingCheckBox();
        Assertions.assertTrue(practice.isMusicSelected(), "Music checkbox should still be selected");
        Assertions.assertTrue(practice.isReadingSelected(), "Reading checkbox should be selected");
        Assertions.assertFalse(practice.isSportSelected(), "Sport checkbox should not be selected");

        practice.clickSportCheckBox();
        Assertions.assertTrue(practice.isMusicSelected(), "Music checkbox should still be selected");
        Assertions.assertTrue(practice.isReadingSelected(), "Reading checkbox should still be selected");
        Assertions.assertTrue(practice.isSportSelected(), "Sport checkbox should be selected");

        System.out.println("\u001B[32m=== TEST SELECT ALL CHECKBOXES PASSED ===\u001B[0m");
    }
    @Test
    public void ZeroState(){
        var practice = homePage.goToFormsPage().clickPracticeFormPage();
        practice.isSportSelected();
        practice.isReadingSelected();
        practice.isMusicSelected();

        Assertions.assertFalse(practice.isMusicSelected(), "Music checkbox should be unselected");
        Assertions.assertFalse(practice.isReadingSelected(), "Reading checkbox should be unselected");
        Assertions.assertFalse(practice.isSportSelected(), "Sport checkbox should be unselected");

        System.out.println("\u001B[32m=== TEST ZeroState PASSED ===\u001B[0m");

    }
}
