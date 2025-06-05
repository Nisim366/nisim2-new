package Generic_product.Pages.Second_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class EmailFields extends Generic_BasePage {

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private Actions actions;

    // לוקייטורים לשדה דוא"ל
    private final By emailInput = By.name("applicant.email");
    private final By emailErrorRequired = By.xpath("//p[text()='שדה זה הוא שדה חובה']");
    private final By emailErrorInvalid = By.xpath("//p[text()='כתובת הדואר האלקטרוני אינה תקינה']");

    // לוקייטורים לשדה אימות דוא"ל
    private final By emailConfirmationInput = By.name("applicant.emailConfirmation");
    private final By emailConfirmationErrorRequired = By.xpath("//p[text()='שדה זה הוא שדה חובה']");
    private final By emailConfirmationErrorInvalid = By.xpath("//p[text()='כתובת הדואר האלקטרוני אינה תקינה']");

    public EmailFields(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.jsUtil = new JavaScriptUtility(driver);
        this.actions = new Actions(driver);
    }

    // ********* פעולות על שדה דוא"ל *********

    public void setEmail(String email) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        input.clear();
        input.sendKeys(email);
    }

    public String getEmail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).getAttribute("value");
    }

    public void clearEmail() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        input.clear();
    }

    public boolean isEmailRequiredErrorDisplayed() {
        return isErrorVisible(emailErrorRequired);
    }

    public boolean isEmailInvalidErrorDisplayed() {
        return isErrorVisible(emailErrorInvalid);
    }

    public String getEmailErrorText() {
        if (isEmailRequiredErrorDisplayed()) {
            return "שדה זה הוא שדה חובה";
        } else if (isEmailInvalidErrorDisplayed()) {
            return "כתובת הדואר האלקטרוני אינה תקינה";
        }
        return "";
    }

    // ✅ חדשה – בודקת האם קיימת שגיאה כלשהי בשדה דוא"ל
    public boolean isEmailErrorDisplayed() {
        return isEmailRequiredErrorDisplayed() || isEmailInvalidErrorDisplayed();
    }

    // ✅ חדשה – מחזירה את הערך של שדה הדוא"ל (alias)
    public String getEmailValue() {
        return getEmail();
    }

    // ✅ חדשה – מבצעת focus ואז blur על שדה דוא"ל
    public void focusAndBlurEmail() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        input.click();
        actions.moveByOffset(0, 100).click().perform(); // blur
    }

    // ********* פעולות על שדה אימות דוא"ל *********

    public void setEmailConfirmation(String email) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailConfirmationInput));
        input.clear();
        input.sendKeys(email);
    }

    public String getEmailConfirmation() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailConfirmationInput)).getAttribute("value");
    }

    public void clearEmailConfirmation() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailConfirmationInput));
        input.clear();
    }

    public boolean isEmailConfirmationRequiredErrorDisplayed() {
        return isErrorVisible(emailConfirmationErrorRequired);
    }

    public boolean isEmailConfirmationInvalidErrorDisplayed() {
        return isErrorVisible(emailConfirmationErrorInvalid);
    }

    public String getEmailConfirmationErrorText() {
        if (isEmailConfirmationRequiredErrorDisplayed()) {
            return "שדה זה הוא שדה חובה";
        } else if (isEmailConfirmationInvalidErrorDisplayed()) {
            return "כתובת הדואר האלקטרוני אינה תקינה";
        }
        return "";
    }

    // ✅ חדשה – בודקת האם קיימת שגיאה כלשהי בשדה אימות דוא"ל
    public boolean isEmailConfirmationErrorDisplayed() {
        return isEmailConfirmationRequiredErrorDisplayed() || isEmailConfirmationInvalidErrorDisplayed();
    }

    // ✅ חדשה – מחזירה את הערך של שדה אימות הדוא"ל (alias)
    public String getEmailConfirmationValue() {
        return getEmailConfirmation();
    }

    // ✅ חדשה – מבצעת focus ואז blur על שדה אימות דוא"ל
    public void focusAndBlurEmailConfirmation() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailConfirmationInput));
        input.click();
        actions.moveByOffset(0, 100).click().perform(); // blur
    }

    // ********* מתודה פנימית לבדוק הופעת שגיאה *********
    private boolean isErrorVisible(By locator) {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
