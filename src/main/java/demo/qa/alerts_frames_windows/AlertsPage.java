package demo.qa.alerts_frames_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class AlertsPage extends  Alert_Frames_WindowssPage{

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private  By infoAlertButton = By.id("alertButton");
    private  By confirmAlertButton = By.id("confirmButton");
    private  By confirmationResult = By.id("confirmResult");
    private  By promptAlertButton = By.id("promtButton");
    private By promptResult= By.id("promptResult");


    public AlertsPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public  void clickInfoAlertButton (){
        click(infoAlertButton);
    }
    public  void clickConfirmAlertButton (){
        click(confirmAlertButton);
    }
    public String getConfirmationResult(){
        return  find(confirmationResult).getText();
    }
    public void clickpromptAlertButton(){
        click(promptAlertButton);
    }
    public String getPronptAlertResult(){
        return  find(promptResult).getText();
    }
}
