package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

public class JavaScriptUtility {

    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(JavaScriptUtility.class.getName());

    public JavaScriptUtility(WebDriver driver) {
        this.driver = driver;
    }

    // Scroll to element using JavaScript
    public void scrollToElementJS(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            String jsScript = "arguments[0].scrollIntoView();";
            ((JavascriptExecutor) driver).executeScript(jsScript, element);
            logger.info("Successfully scrolled to the element: " + locator);
        } catch (Exception e) {
            logger.severe("Error while scrolling to the element: " + locator + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Click on element using JavaScript
    public void clickElementWithJS(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            scrollToElementJS(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.info("Successfully clicked the element: " + locator);
        } catch (Exception e) {
            logger.severe("Error while clicking the element: " + locator + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Wait for element to be visible
    public void waitForElementVisibility(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element is visible: " + locator);
        } catch (Exception e) {
            logger.severe("Error while waiting for visibility of the element: " + locator + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    // מחיקה של כל התוכן על ידי סימון והקלדה ריקה, מקבלת WebElement
    public void clearUsingCtrlADelete(WebElement input) {
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // בחר הכל
        input.sendKeys(Keys.DELETE);                      // מחק את הבחירה
    }

    // מחיקה של התו האחרון בעזרת Backspace, מקבלת WebElement
    public void deleteLastChar(WebElement input) {
        input.sendKeys(Keys.BACK_SPACE);
    }
}
