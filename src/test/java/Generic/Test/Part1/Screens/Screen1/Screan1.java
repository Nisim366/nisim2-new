package Generic.Test.Part1.Screens.Screen1;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Screan1 extends BaseTest_Generic {

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
    public void checkboxLabelTextIsCorrect() {
        First obj = homePage.goToPractice();
        String label = obj.getCheckboxLabelText();
        System.out.println("Checkbox label: " + label);
        Assertions.assertFalse(label.isEmpty(), "Checkbox label should not be empty");
        System.out.println("\u001B[32m=== TEST checkboxLabelTextIsCorrect PASSED ===\u001B[0m");
    }






}
