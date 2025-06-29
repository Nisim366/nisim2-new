package Generic_product.Pages.Second_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Third_screen.Third_screen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.JavaScriptUtility;

public class Second extends Generic_BasePage {
    private final JavaScriptUtility js;


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

    public void clickBackButton() {
        super.clickBackButton();
    }

    private Third_screen completeSecondScreenHappyFlow() {
        FirstLastName firstLastName = new FirstLastName(driver);
        PhoneField phoneField = new PhoneField(driver);
        EmailFields emailFields = new EmailFields(driver);

        // ערכים תקניים מראש
        String firstName = "חן";
        String lastName = "הניגון";
        String phone = "0532407762";
        String email = "yossi@example.com";

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

// המתנה להופעת שדה תעודת זהות במסך השלישי
            By identifierField = By.cssSelector("[data-testid='applicant.identifier-input']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifierField));

// יצירת מופע של המסך השלישי
            Third_screen thirdScreen = new Third_screen(driver);

// בדיקה שהמסך אכן נטען
            if (!thirdScreen.isOnThirdScreen()) {
                throw new IllegalStateException("שגיאה: לא עברנו למסך השלישי בהצלחה.");
            }


            return thirdScreen;

        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("כשל כללי ב-Happy Flow של המסך השני.", e);
        }
    }


    public Third_screen goTothirdScreen() {
        return completeSecondScreenHappyFlow(); // בלי פרמטרים
    }

}
