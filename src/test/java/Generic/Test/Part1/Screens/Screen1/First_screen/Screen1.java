package Generic.Test.Part1.Screens.Screen1.First_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Generic_HomePage;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.Second;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Screen1 extends BaseTest_Generic {



    @Test
    public void isOnFirstPageReturnsTrue() {
        First obj = homePage.goToPractice();
        Assertions.assertTrue(obj.isOnFirstPage(), "Should be on the first page");
        System.out.println("\u001B[32m=== TEST isOnFirstPageReturnsTrue PASSED ===\u001B[0m");
    }

    @Test
    public void checkboxIsNotSelectedInitially() {
        First obj = homePage.goToPractice();
        if (obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        Assertions.assertFalse(obj.isCheckboxSelected(), "Checkbox should NOT be selected initially");
        System.out.println("\u001B[32m=== TEST checkboxIsNotSelectedInitially PASSED ===\u001B[0m");
    }


    @Test
    public void checkboxSelectsAfterClick() {
        First obj = homePage.goToPractice();
        if (obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        obj.clickCheckbox();
        Assertions.assertTrue(obj.isCheckboxSelected(), "Checkbox should be selected after click");
        System.out.println("\u001B[32m=== TEST checkboxSelectsAfterClick PASSED ===\u001B[0m");
    }

    @Test
    public void checkboxDeselectsAfterSecondClick() {
        First obj = homePage.goToPractice();
        if (!obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        obj.clickCheckbox();
        Assertions.assertFalse(obj.isCheckboxSelected(), "Checkbox should NOT be selected after second click");
        System.out.println("\u001B[32m=== TEST checkboxDeselectsAfterSecondClick PASSED ===\u001B[0m");
    }

    @Test
    public void checkboxIsVisible() {
        First obj = homePage.goToPractice();
        Assertions.assertFalse(obj.getCheckboxLabelText().isEmpty(), "Checkbox label should be visible");
        System.out.println("\u001B[32m=== TEST checkboxIsVisible PASSED ===\u001B[0m");
    }
    @Test
    public void checkboxLabelDoesNotChangeAfterClick() {
        First obj = homePage.goToPractice();
        String labelBefore = obj.getCheckboxLabelText();
        obj.clickCheckbox();
        String labelAfter = obj.getCheckboxLabelText();
        Assertions.assertEquals(labelBefore, labelAfter, "Checkbox label should not change after click");
        System.out.println("\u001B[32m=== TEST checkboxLabelDoesNotChangeAfterClick PASSED ===\u001B[0m");
    }

    @Test
    public void checkboxLabelTextIsCorrect() {
        First obj = homePage.goToPractice();
        String label = obj.getCheckboxLabelText();
        System.out.println("Checkbox label: " + label);
        Assertions.assertFalse(label.isEmpty(), "Checkbox label should not be empty");
        System.out.println("\u001B[32m=== TEST checkboxLabelTextIsCorrect PASSED ===\u001B[0m");
    }
    @Test
    public void testGoToSecondScreen() {
        First obj = homePage.goToPractice();
        if (!obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        obj.clickContinueButton();
        Second secondPage = new Second(driver);

        Assertions.assertTrue(secondPage.isOnSecondPage(), "Should be on second page after clicking Continue");
        System.out.println("\u001B[32m=== TEST navigateToSecondPageAfterContinue PASSED ===\u001B[0m");
    }







}
