package demo.qa.alerts_frames_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class modalDialogPage extends Alert_Frames_WindowssPage {

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private final  By smallModalButton = By.xpath("//div[@id='modalWrapper']//button[@id='showSmallModal']");
    private final  By smallModalTex    = By.xpath("//div[contains(text(),'small modal.')]");
    private final  By closeModal       = By.id("closeSmallModal");

    public modalDialogPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void clickSmallModalButtom (){
        click(smallModalButton);
    }
    public String getSmallModalText (){
        jsUtil.waitForElementVisibility(smallModalTex);
        return find(smallModalTex).getText();
    }
    public  void clickCloseButton (){
        click(closeModal);
    }
}
