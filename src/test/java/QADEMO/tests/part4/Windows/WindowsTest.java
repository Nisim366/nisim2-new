package QADEMO.tests.part4.Windows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import QADEMO.Base.BaseTest_QADEMO;

import static utilities.GetUtility.getURL;

public class WindowsTest extends BaseTest_QADEMO {
    private By actual = By.id("sampleHeading");

    @Test
    public  void testNewWindowURL(){
        var windowPage = homePage.goToAlertsFrameWindowsCard().clickBrowserWindows();
    }
    @Test
    public  void testWindowURL(){
        var windowPage = homePage.goToAlertsFrameWindowsCard().clickBrowserWindows();
        windowPage.clickNewWindowButton();
        windowPage.switchToNewWindow();
        String actualUrl = getURL(driver);
        String expectedUrl = "https://demoqa.com/sample";
        Assertions.assertEquals(actualUrl,expectedUrl,"actual and expedted URLs not match");
    }
    @Test
    public void testWindowSwitch() {
        var windowPage = homePage.goToAlertsFrameWindowsCard().clickBrowserWindows();
        windowPage.clickNewWindowButton();
        windowPage.switchToNewWindow();
        String actualUrl = getURL(driver);
        String expectedUrl = "https://demoqa.com/sample";
        Assertions.assertEquals(actualUrl, expectedUrl, "actual and expected URLs not match");

        String expectedText = "This is a sample page";
        String actualText = driver.findElement(By.id("sampleHeading")).getText();

        Assertions.assertEquals(expectedText, actualText, "Text inside the new window is not as expected.");
    }

}
