package Generic_product.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;
import java.time.Duration;

public class Generic_BasePage {

    protected WebDriver driver;
    protected JavaScriptUtility jsUtility;
    protected final WebDriverWait longwait;
    private final By backButton = By.cssSelector("[data-testid='back-button']");

    public Generic_BasePage(WebDriver driver) {
        this.driver = driver;
        this.longwait = new WebDriverWait(driver, Duration.ofSeconds(90));
        this.jsUtility = new JavaScriptUtility(driver);
    }

    // ✅ מאפשר יצירת WebDriverWait מותאם זמן
    public WebDriverWait customWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public WebDriver getDriver() {
        return driver;
    }

    // ✅ מציאת אלמנט – ברירת מחדל 10 שניות
    protected WebElement find(By locator) {
        return customFind(locator, 10);
    }

    // ✅ מציאת אלמנט עם זמן מותאם אישית
    protected WebElement customFind(By locator, int seconds) {
        return customWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ✅ הכנסת טקסט לשדה – זמן ברירת מחדל 2 שניות
    protected void set(By locator, String textToSet) {
        WebElement element = customFind(locator, 2);
        jsUtility.clearUsingCtrlADelete(element);
        element.sendKeys(textToSet);
    }

    // ✅ בדיקת האם אלמנט מוצג – עם זמן מותאם אישית
    public boolean isElementVisible(By locator, int seconds) {
        try {
            return customWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ בדיקת האם אלמנט מוצג – ברירת מחדל: 10 שניות
    public boolean isElementVisible(By locator) {
        return isElementVisible(locator, 10);
    }

    // ✅ בדיקה אם אלמנט מופיע ומופעל
    protected boolean isElementEnabled(By locator, int seconds) {
        try {
            WebElement element = customWait(seconds).until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ ניקוי שדה – זמן ברירת מחדל 2 שניות
    protected void clear(By locator) {
        WebElement element = customFind(locator, 2);
        jsUtility.clearUsingCtrlADelete(element);
    }

    // ✅ לחיצה על אלמנט – זמן ברירת מחדל 10 שניות
    protected void click(By locator) {
        click(locator, 2);
    }

    // ✅ לחיצה עם זמן מותאם
    protected void click(By locator, int seconds) {
        customWait(seconds).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // ✅ קבלת טקסט מהאלמנט – ברירת מחדל 10 שניות
    protected String getText(By locator) {
        return getText(locator, 2);
    }

    // ✅ קבלת טקסט עם זמן מותאם
    protected String getText(By locator, int seconds) {
        return customFind(locator, seconds).getText();
    }

    // ✅ לחיצה על כפתור חזרה – זמן ברירת מחדל 2 שניות
    public void clickBackButton() {
        click(backButton, 2);
    }
}
