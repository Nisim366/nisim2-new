package QADEMO.tests.part3.Widgets;

import demo.qa.Widgets.practiceWidgetsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import QADEMO.Base.BaseTest_QADEMO;

import java.util.List;

public class selectDropDownTest extends BaseTest_QADEMO {
    String expectedOption = "Volvo";


    @Test
    public void openSelectManueTest() {
        practiceWidgetsPage select = homePage.goToWidgets().clickSelectMenu();
    }

    @Test
    public void isExpectedOptionSelectedTest() {

        practiceWidgetsPage select = homePage.goToWidgets().clickSelectMenu();
        select.selectStandartMultiByInt(0);
        select.selectStandartMultiByInt(1);

        List<String> actualOptions = select.getAllSelectedOptions();
        Assertions.assertTrue(actualOptions.contains(expectedOption),
                "Expected option '" + expectedOption + "' was not found in the selected options");
        System.out.println("Selected options: " + actualOptions);
    }
    @Test
    public void deselectOptionByIndexTest() {
        practiceWidgetsPage practiceWidgets = homePage.goToWidgets().clickSelectMenu();
        practiceWidgets.selectStandartMultiByInt(0);
        var selectedBefore = practiceWidgets.getAllSelectedOptions();
        Assertions.assertFalse(selectedBefore.isEmpty(), "There should be at least one selected option before deselecting.");

        practiceWidgets.deselectByIndex(0);
        var selectedAfter = practiceWidgets.getAllSelectedOptions();
        Assertions.assertTrue(selectedAfter.isEmpty(), "There should be no selected options after deselecting.");


    }

}
