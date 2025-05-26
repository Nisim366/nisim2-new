package QADEMO.tests.part4.Frames;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import QADEMO.Base.BaseTest_QADEMO;

public class FramesTest extends BaseTest_QADEMO {

    @Test
    public void testFramesBigBox() {
        var framesPage = homePage.goToAlertsFrameWindowsCard().clickFrames();

        String expectedBigBoxText = "This is a sample page";
        String actualBigBoxText = framesPage.getTextInBigFrame();
        Assertions.assertEquals(expectedBigBoxText, actualBigBoxText, "Text in big frame does not match the expected value.");

        String expectedHeaderText = "Frames";
        String actualHeaderText = framesPage.getHeaderFrameText();
        Assertions.assertEquals(expectedHeaderText, actualHeaderText, "Header text in frames page does not match the expected value.");
    }

    @Test
    public void testFrameSmallBox() {
        var framesPage = homePage.goToAlertsFrameWindowsCard().clickFrames();
        int i = 2;
        String expectedSmallBoxText = "This is a sample page";
        String actualSmallBoxText = framesPage.getTextInSmallFrame();
        Assertions.assertEquals(expectedSmallBoxText, actualSmallBoxText, "Text in small frame does not match the expected value.");

        String expectedHeaderText = "Frames";
        String actualHeaderText = framesPage.getHeaderFrameText();
        Assertions.assertEquals(expectedHeaderText, actualHeaderText, "Header text in frames page does not match the expected value.");
    }
}
