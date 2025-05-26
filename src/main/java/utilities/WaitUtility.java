package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class WaitUtility extends Utility {

    private WebDriverWait wait;

    public WaitUtility(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // מתן זמן המתנה 5 שניות
    }

    public static void explicitWaitUntilVisible(WebDriver driver, int second, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second)); // הממתינים עד שהאלמנט יראה
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void fluentWaitUntilVisible(WebDriver driver, int second, By locator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(second)) // זמן המתנה מרבי
                .pollingEvery(Duration.ofSeconds(1)) // זמן המתנה בין בדיקה לבדיקה
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class); // יוצא מהשגיאות האלו
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // ממתינים עד שהאלמנט ייראה
    }
}
