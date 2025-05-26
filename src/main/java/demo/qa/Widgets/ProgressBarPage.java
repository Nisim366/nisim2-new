package demo.qa.Widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;
import utilities.WaitUtility.*;

import static utilities.WaitUtility.fluentWaitUntilVisible;

public class ProgressBarPage extends  WidgetsPage{

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private final By startButton = By.id("startStopButton");
    private final By progressValue = By.xpath("//div[@id='progressBar']/div[@aria-valuenow='100']");

    public ProgressBarPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void clickStartButton(){
        click(startButton);
    }
    public String getProgressValue(){
        fluentWaitUntilVisible(driver,30,progressValue);
        return getText(progressValue);
    }

}
