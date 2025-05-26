package QADEMO.tests.part4.alerts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import QADEMO.Base.BaseTest_QADEMO;

import static utilities.SwitchToUtility.*;

public class AlertsTest extends BaseTest_QADEMO {

    @Test
    public  void alertsInfoItem (){
        String expectedAlertText = "You clicked a button";
        var alertsPage = homePage.goToAlertsFrameWindowsCard().clickAlerts();
        alertsPage.clickInfoAlertButton();
        Assertions.assertEquals(expectedAlertText,getAlertText(driver), " massages not match");
        acceptAlert(driver);
    }
    @Test
    public  void  testConfirmAlert (){
        var alertsPage = homePage.goToAlertsFrameWindowsCard().clickAlerts();
        alertsPage.clickConfirmAlertButton();
        dismissAlert(driver);

        String  actualConfirmResult = alertsPage.getConfirmationResult();
        String expectedConfirmResult = "You selected Cancel";

        Assertions.assertEquals(actualConfirmResult,expectedConfirmResult,"you did not select cancel");
    }
    @Test
    public void testPronptAlert(){
        String alertTest = "nisim";
        var alertsPage = homePage.goToAlertsFrameWindowsCard().clickAlerts();
        alertsPage.clickpromptAlertButton();
        setAlertText(driver,alertTest);
        acceptAlert(driver);
        String actualResult = alertsPage.getPronptAlertResult();
        String expectedResult = "You entered "+alertTest;
        Assertions.assertEquals(expectedResult,actualResult, " acteal ans expected are not match");

    }
}
