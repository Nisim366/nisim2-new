package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SwitchToUtility extends Utility {

    private WebDriverWait wait;

    public SwitchToUtility(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private static WebDriver.TargetLocator switchTo(WebDriver driver) {
        return driver.switchTo();
    }

    public static String getAlertText(WebDriver driver) {
        return switchTo(driver).alert().getText();
    }

    public static void acceptAlert(WebDriver driver) {
        switchTo(driver).alert().accept();
    }

    public static void dismissAlert(WebDriver driver) {
        switchTo(driver).alert().dismiss();
    }

    public static void setAlertText(WebDriver driver, String text) {
        switchTo(driver).alert().sendKeys(text);
    }

    public static void switchToFrameString(WebDriver driver, String value) {
        switchTo(driver).frame(value);
    }

    public static void switchToFrameIndex(WebDriver driver, int index) {
        switchTo(driver).frame(index);
    }

    public static void switchToDefaultContent(WebDriver driver) {
        switchTo(driver).defaultContent();
    }
    public  static  void switchToFrameElement (WebElement element,WebDriver driver){
        switchTo(driver).frame(element);
    }
    public static  void switchToWindow(WebDriver driver, String handule){
        switchTo(driver).window(handule);

    }
}
