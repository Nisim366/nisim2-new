package demo.qa.alerts_frames_windows;

import demo.qa.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class Alert_Frames_WindowssPage extends HomePage {

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private By modalDialogMenueItem = By.xpath("//li[@id='item-4']/span[text()='Modal Dialogs']");
    private By alertsManueItem = By.xpath("//li[@id='item-1']/span[text()='Alerts']");
    private By framesManueItem = By.xpath("//li[@id='item-2']/span[text()='Frames']");
    private By browserWindowsMenueItem = By.xpath(" //li[@id='item-0']/span[text()='Browser Windows']");


    public Alert_Frames_WindowssPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public modalDialogPage clickModalDialog() {
        jsUtil.scrollToElementJS(modalDialogMenueItem);
        click(modalDialogMenueItem);
        return new modalDialogPage(driver);
    }

    public AlertsPage clickAlerts() {
        jsUtil.scrollToElementJS(alertsManueItem);
        click(alertsManueItem);
        return new AlertsPage(driver);
    }

    public FramesPage clickFrames() {
        jsUtil.scrollToElementJS(framesManueItem);
        click(framesManueItem);
        return new FramesPage(driver);
    }
    public  BrowserWindowsPage clickBrowserWindows (){
        jsUtil.scrollToElementJS(browserWindowsMenueItem);
        click(browserWindowsMenueItem);
        return new BrowserWindowsPage(driver);
    }
}
