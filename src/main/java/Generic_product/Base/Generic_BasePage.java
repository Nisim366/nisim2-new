package Generic_product.Base;

import org.openqa.selenium.*;
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
    public void selectAutocompleteOption(By fieldLocator, int arrowDownPresses) {
        try {
            // ממתין שהשדה יהיה לחיץ
            customWait(2).until(ExpectedConditions.elementToBeClickable(fieldLocator));
            WebElement inputField = driver.findElement(fieldLocator);

            // לוחץ על השדה לקבלת פוקוס
            inputField.click();

            // המתנה קטנה לטעינת האפשרויות
            Thread.sleep(2000);

            // לוחץ חץ מטה לפי מספר הלחיצות שנשלח
            for (int i = 0; i < arrowDownPresses; i++) {
                inputField.sendKeys(Keys.ARROW_DOWN);
                Thread.sleep(500); // ⏳ המתנה של חצי שנייה בין כל לחיצה
            }

            // מאשר את הבחירה
            inputField.sendKeys(Keys.ENTER);

        } catch (TimeoutException e) {
            throw new RuntimeException("⏳ Timeout – לא נמצאה אפשרות בשדה: " + fieldLocator, e);
        } catch (Exception e) {
            throw new RuntimeException("❌ שגיאה כללית בבחירת ערך בשדה: " + fieldLocator, e);
        }
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
        return isElementVisible(locator, 90);
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
