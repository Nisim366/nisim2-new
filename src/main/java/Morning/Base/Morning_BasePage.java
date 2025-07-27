package Morning.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class Morning_BasePage {

    protected WebDriver driver;
    protected JavaScriptUtility jsUtility;
    protected final WebDriverWait longwait;
    protected final By continueButton = By.cssSelector("[data-testid='continue-button']");
    protected final By welcomeBackButton = By.cssSelector("[data-testid='onboarding-close-welcome-back-dialog']");


    public Morning_BasePage(WebDriver driver) {
        this.driver = driver;
        this.longwait = new WebDriverWait(driver, Duration.ofSeconds(90));
        this.jsUtility = new JavaScriptUtility(driver);
    }
    public void handleWelcomeBackPopupIfExists() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement element = shortWait.until(ExpectedConditions.elementToBeClickable(welcomeBackButton));
            element.click();
            System.out.println("🟢 כפתור 'חזרה לתהליך' זוהה ונלחץ");
        } catch (Exception e) {
            System.out.println("ℹ️ כפתור 'חזרה לתהליך' לא הופיע – ממשיכים כרגיל");
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

     public void clickContinueButton() {
         click(continueButton, 2);
     }
    public boolean isOnCorrectScreen(int expectedStep) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            Object result = js.executeScript("return ezbob.actions.userState.getCurrentStep();");
            if (result != null) {
                int currentStep = Integer.parseInt(result.toString());
                return currentStep == expectedStep;
            }
        } catch (Exception e) {
            System.out.println("❌ שגיאה בהרצת currentStep: " + e.getMessage());
        }
        return false;
    }
    public int getCurrentStepFromConsole() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object result = js.executeScript("return ezbob.actions.userState.getCurrentStep();");
            return result != null ? Integer.parseInt(result.toString()) : -1;
        } catch (Exception e) {
            System.out.println("❌ שגיאה בקריאת currentStep: " + e.getMessage());
            return -1;
        }
    }
    public boolean isOnPage(int expectedStep) {
        return getCurrentStepFromConsole() == expectedStep;
    }




}
