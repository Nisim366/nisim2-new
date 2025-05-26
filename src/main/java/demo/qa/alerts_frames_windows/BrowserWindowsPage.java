package demo.qa.alerts_frames_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;
import java.util.Set;

import static utilities.SwitchToUtility.switchToWindow;

public class BrowserWindowsPage extends Alert_Frames_WindowssPage{

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private final By newWindow = By.id("windowButton");

    public BrowserWindowsPage(WebDriver driver) {
        super(driver);
        this.jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public  void clickNewWindowButton(){
        click(newWindow);
    }
    public  void switchToNewWindow (){
        String currentHundle = driver.getWindowHandle();
        System.out.println("main window id "+currentHundle+"\n");
        Set<String> allHanduls = driver.getWindowHandles();
        System.out.println("number of open windows"+allHanduls.size());
        for( String handele : allHanduls){
            if(currentHundle.equals(handele)){
                System.out.println("1st window id :"+ handele);
            }else{
                switchToWindow(driver,handele);
                System.out.println("2st window id:"+handele);
            }
        }

    }
}
