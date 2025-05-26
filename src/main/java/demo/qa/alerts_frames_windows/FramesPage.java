package demo.qa.alerts_frames_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static utilities.SwitchToUtility.*;
import utilities.JavaScriptUtility;

public class FramesPage extends Alert_Frames_WindowssPage {

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private final By textInFrame = By.id("sampleHeading");
    private final String iFrame1BigBox = "frame1";
    private final String iFrame2BigBox = "frame2";
    private final By headerFrameText = By.xpath("//div[@id='framesWrapper']/h1[text()='Frames']");
    private final By iFrameSmallBox = By.xpath("//div[@id='frame2Wrapper']/iframe");

    public FramesPage(WebDriver driver) {
        super(driver);
        this.jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // שיטה זו מבצעת את המעבר ל-iFrame 1
    public String getTextInBigFrame() {
        switchToBigBoxUsingString();  // מעבר ל-iFrame הראשון
        String bigFrameText = find(textInFrame).getText();
        System.out.println("Big frame text: " + bigFrameText);
        switchToDefaultContent(driver);
        return bigFrameText;
    }

    //
    public String getTextInSmallFrame() {
        switchToSmallBoxUsingElement();
        String smallFrameText = find(textInFrame).getText();
        System.out.println("Small frame text: " + smallFrameText);
        switchToDefaultContent(driver);
        return smallFrameText;
    }

    // שיטה זו מבצעת את המעבר ל-iFrame הראשון
    private void switchToBigBoxUsingString() {
        switchToFrameString(driver, iFrame1BigBox);  // מעבר לפי שם iFrame
    }

    // שיטה זו מבצעת את המעבר ל-iFrame השני
    private void switchToSmallBoxUsingString() {
        switchToFrameString(driver, iFrame2BigBox);  // מעבר לפי שם iFrame
    }

    // שיטה זו מבצעת את המעבר ל-iFrame לפי אינדקס
    private void switchToSmallBoxUsingElement() {
        switchToFrameElement(find(iFrameSmallBox),driver);
    }

    // שיטה זו מחזירה את כותרת ה-iframe
    public String getHeaderFrameText() {
        return find(headerFrameText).getText();  // מציאת הכותרת בעזרת ה-xpath
    }
}
