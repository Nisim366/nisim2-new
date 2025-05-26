package demo.qa.elements;

import demo.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;
import utilities.WaitUtility;

import java.time.Duration;

public class DynamicPropertiesPage extends ElementsPage {

    private JavaScriptUtility jsUtil;
    private WebDriverWait wait;

    private By visibleAfterButton = By.id("visibleAfter");

    public DynamicPropertiesPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // אתחול נכון של wait
    }

    public String getVisibleAfterButtonText() {
        WaitUtility.explicitWaitUntilVisible(driver, 10, By.id("visibleAfter"));
        String visibleText = getText(visibleAfterButton);
        System.out.println("Button text: " + visibleText);
        return visibleText;
    }
}
