package Generic_product.Pages.Tenth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Eleventh_Screen.EleventhScreen;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class TenthScreen extends Generic_BasePage {

    private final By transactionNumberParagraph = By.xpath("//p[contains(text(), 'מספר עסקה:')]");
    By toggleConsentButton = By.xpath("//button[@id='toggle-dates-button']");
    private final By approveButton = By.cssSelector("button[data-testid='continue-button']");





    public TenthScreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnTenthScreen() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(transactionNumberParagraph)).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
    public void expandConsentAndScrollDownFor8Seconds() {
        try {
            // לחץ על הכפתור "שינוי תוקף ההסכמה"
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(toggleConsentButton));
            button.click();

            // המתן חצי שנייה לאחר לחיצה (לוודא שההרחבה נפתחת)
            Thread.sleep(500);

            // שלח חץ-מטה במשך 8 שניות
            long endTime = System.currentTimeMillis() + 8000;
            Actions actions = new Actions(driver);

            while (System.currentTimeMillis() < endTime) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
                Thread.sleep(250); // המתנה קטנה בין שליחות (4 חיצים בשנייה)
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ שגיאה בגלילה לאחר פתיחת הסכמה: " + e.getMessage(), e);
        }
    }
    public void clickApproveButton() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(approveButton));
            button.click();
        } catch (Exception e) {
            throw new RuntimeException("❌ לא ניתן היה ללחוץ על כפתור 'מאושר': " + e.getMessage(), e);
        }
    }
    public EleventhScreen completeTenthScreenFlow() {
        // שלב 1: פתיחת תוקף הסכמה
        WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(toggleConsentButton));
        toggleButton.click();

        // שלב 2: גלילה באמצעות חיצים מטה במשך 8 שניות
        try {
            Thread.sleep(500); // המתן קטן לאחר לחיצה
            Actions actions = new Actions(driver);
            long endTime = System.currentTimeMillis() + 8000;
            while (System.currentTimeMillis() < endTime) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("❌ שגיאה במהלך גלילה עם חיצים: " + e.getMessage(), e);
        }

        // שלב 3: לחיצה על כפתור "מאושר"
        By approveButton = By.cssSelector("button[data-testid='continue-button']");
        WebElement approve = wait.until(ExpectedConditions.elementToBeClickable(approveButton));
        approve.click();
        System.out.println("מסך אישור פניה למאגר אשראי ");


        return new EleventhScreen(driver);
    }
    public EleventhScreen goToEleventhScreen() {
        return completeTenthScreenFlow();
    }








}
