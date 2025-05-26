package QADEMO.tests.part4.Dynamic_wait;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import QADEMO.Base.BaseTest_QADEMO;

public class DynemicWaitTest extends BaseTest_QADEMO {

    @Test
    public  void testGoToDynemicPage(){
        homePage.goToElements().clickDynamicProperties();
    }
    @Test
    public  void testVisibleAfterButtonText(){
        var dynemicPage = homePage.goToElements().clickDynamicProperties();
        String actualText = dynemicPage.getVisibleAfterButtonText();
        String expectedText = "Visible After 5 Seconds";
        Assertions.assertEquals(actualText,expectedText,"Actual and expected text not match");

    }
    @Test
    public  void testGoToProgressBarPage(){
        homePage.goToWidgets().clickBarPage();
    }
    @Test
    public  void tesProgressBar(){
        var progressBarPage = homePage.goToWidgets().clickBarPage();
        progressBarPage.clickStartButton();
        String actualVakue = progressBarPage.getProgressValue();
        String expectedValue = "100%";
        Assertions.assertEquals(actualVakue,expectedValue,"value is not 100%");
    }
}
