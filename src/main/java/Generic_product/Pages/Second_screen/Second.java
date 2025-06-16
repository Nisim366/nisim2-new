package Generic_product.Pages.Second_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Third_screen.Third_screen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import utilities.JavaScriptUtility;

public class Second extends Generic_BasePage {
    private JavaScriptUtility js;

    private final By headerSecondPage = By.xpath("//h1[@id='page-header' and contains(text(),'נתחיל בכמה פרטים אישיים')]");
    private final By continueButton = By.cssSelector("button[data-testid='continue-button']");

    public Second(WebDriver driver) {
        super(driver);
        js = new JavaScriptUtility(driver);
    }

    public boolean isOnSecondPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(headerSecondPage));
            String text = getText(headerSecondPage);
            return text.contains("נתחיל בכמה פרטים אישיים");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        } catch (Exception e) {
            js.clickElementWithJS(continueButton);
        }
    }

    /**
     * מבצע את כל הפעולות למילוי שדות חובה במסך השני וממשיך למסך השלישי.
     *
     * @param firstName השם הפרטי למילוי
     * @param lastName שם המשפחה למילוי
     * @param phone מספר הטלפון למילוי
     * @param email כתובת האימייל למילוי
     * @return אובייקט Page Object של המסך השלישי (Third_screen) לאחר סיום הפעולות.
     * @throws IllegalStateException אם קיימת בעיה במציאת או אינטראקציה עם אלמנטים.
     * @throws RuntimeException אם מתרחשת שגיאה בלתי צפויה.
     */
    public Third_screen completeSecondScreenHappyFlow(String firstName, String lastName, String phone, String email) {
        FirstLastName firstLastName = new FirstLastName(driver);
        PhoneField phoneField = new PhoneField(driver);
        EmailFields emailFields = new EmailFields(driver);

        try {
            firstLastName.setFirstName(firstName);
            firstLastName.setLastName(lastName);

            phoneField.setPhoneInput(phone);

            emailFields.setEmail(email);
            emailFields.setEmailConfirmation(email);

            if (!firstLastName.getFirstName().equals(firstName)) {
                throw new IllegalStateException("שגיאה: השם הפרטי לא הוזן נכון.");
            }
            if (!firstLastName.getLastName().equals(lastName)) {
                throw new IllegalStateException("שגיאה: שם המשפחה לא הוזן נכון.");
            }
            if (!phoneField.getNormalizedPhoneValue().equals(phoneField.normalizePhoneForComparison(phone))) {
                throw new IllegalStateException("שגיאה: מספר הטלפון לא הוזן נכון.");
            }
            if (!emailFields.getEmail().equals(email)) {
                throw new IllegalStateException("שגיאה: האימייל לא הוזן נכון.");
            }
            if (!emailFields.getEmailConfirmation().equals(email)) {
                throw new IllegalStateException("שגיאה: אישור האימייל לא הוזן נכון.");
            }

            clickContinueButton();

            return new Third_screen(driver);

        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("כשל כללי ב-Happy Flow של המסך השני.", e);
        }
    }

    public void clickBackButton() {
        super.clickBackButton();
    }
}