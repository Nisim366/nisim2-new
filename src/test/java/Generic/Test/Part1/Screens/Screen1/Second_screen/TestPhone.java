package Generic.Test.Part1.Screens.Screen1.Second_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First; // נניח ש-homePage בא מ-BaseTest_Generic וזה קלאס אחר
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// אין צורך בייבוא WebDriver, ExpectedConditions, WebDriverWait כאן
// אם הם מטופלים בקלאס BaseTest_Generic ונגישים כ-protected

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestPhone extends BaseTest_Generic {

    private PhoneField phoneField;
    private Second secondPage;
    // אין צורך להצהיר על 'driver' כאן. הוא מוגדר כ-protected ב-BaseTest_Generic ונגיש.

    private final String EXPECTED_HEADER_TEXT_SCREEN_2 = "נתחיל בכמה פרטים אישיים";
    // תיקון: הפקודה המלאה ל-JavaScript
    private final String JS_COMMAND_STEP_SCREEN_2 = "ezbob.actions.userState.setCurrentStepByName('contactDetailsGeneric')";

    // מתודה זו אינה נקראת כרגע ב-setup(), ולכן ניתן להסיר אותה או לשלב את הלוגיקה שלה ב-setup.
    // אם זו הדרך העיקרית להגיע למסך השני, וודא שהיא נקראת מתוך ה-setup.
    /*
    private PhoneField navigateToSecondPage() {
        // ודא ש-homePage מאותחל וזמין ב-BaseTest_Generic
        First obj = homePage.goToPractice();
        if (!obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        obj.clickContinueButton();
        secondPage = new Second(driver); // 'driver' נגיש מהקלאס הבסיסי
        Assertions.assertTrue(secondPage.isOnSecondPage(), "לא הגעת למסך השני בהצלחה");
        return new PhoneField(driver);
    }
    */

    @BeforeEach
    public void setup() {
        try {
            // ודא שמתודת setup() ב-BaseTest_Generic כבר רצה ואיכלסה את ה-driver.
            // ב-JUnit 5, מתודת @BeforeEach של קלאס הבסיס רצה לפני מתודת ה-@BeforeEach של הקלאס היורש.

            // מתודות אלו משתמשות ב-driver, ולכן הוא חייב להיות מאותחל ב-BaseTest_Generic.
            navigateToApplicationUrl();
            waitForManualConsoleInputAndScreenTransition(JS_COMMAND_STEP_SCREEN_2);
            verifyNewScreenHeader(EXPECTED_HEADER_TEXT_SCREEN_2);

            // **תיקון קריטי**: ייזום ה-Page Objects כאן, אחרי שהדף נטען ומוכן.
            // 'driver' זמין עכשיו (מהקלאס הבסיסי).
            secondPage = new Second(driver);
            phoneField = new PhoneField(driver);

            // אם אתה רוצה המתנה קשיחה, השתמש במתודת Wait שהגדרת ב-BaseTest_Generic.
            // זכור: זו המתנה קשיחה, פחות מומלץ לאמינות בדיקות ארוכות טווח.
            // לדוגמה, אם יש אנימציות או רכיבים שנטענים לאט, זו יכולה להיות נקודת כשל.
            // Wait(driver, Duration.ofSeconds(30)); // מבוטל, כי isPhoneInputVisible() אמור להספיק

            // אימותים שמוודאים שהמסך והאלמנטים החשובים גלויים
            assertTrue(secondPage.isOnSecondPage(), "אובייקט ה-Page Object של המסך השני לא אושר כטוען נכון.");
            assertTrue(phoneField.isPhoneInputVisible(), "שדה קלט הטלפון אינו גלוי לאחר טעינת המסך השני.");


        } catch (Exception e) {
            // תיקון: הודעת השגיאה תתייחס למסך השני, לא הרביעי.
            fail("❌ כשל בהכנת הסביבה (setup) למסך השני: " + e.getMessage());
        }
    }

    @Test
    public void testPhonePrefixIsPresent() {
        assertTrue(phoneField.isPhoneInputVisible());
        String val = phoneField.getPhoneInputValue();
        assertTrue(val.startsWith("+972"));
        assertEquals("+972", val);
    }

    @Test
    public void testSetValidPhoneNumber() {
        phoneField.clearPhoneInputKeepingPrefix();
        phoneField.setPhoneInput("501234567");
        assertFalse(phoneField.isPhoneErrorMessageDisplayed());
        assertTrue(phoneField.isPhoneNumberValid());
    }

    @Test
    public void testSetInvalidPhoneNumberShowsError() {
        phoneField.clearPhoneInputKeepingPrefix();
        phoneField.setPhoneInput("123");

        // אם הודעת השגיאה מופיעה באופן אסינכרוני, ייתכן שתצטרך המתנה כאן
        // לדוגמה, באמצעות WebDriverWait בתוך ה-Page Object PhoneField
        // (לדוגמה: phoneField.waitForErrorMessageVisibility(); )

        assertTrue(phoneField.isPhoneErrorMessageDisplayed(), "Error message should be displayed for invalid phone number");
        assertTrue(phoneField.getPhoneErrorMessage().contains("יש להקליד מספר נייד תקין"), "Error message should mention the Hebrew text");
        assertFalse(phoneField.isPhoneNumberValid(), "Phone number validation should fail for invalid input");
    }

    @Test
    public void testCannotDeletePrefix() {
        phoneField.clearPhoneInputKeepingPrefix();
        phoneField.deleteCharsFromPhoneInput(10);
        String val = phoneField.getPhoneInputValue();
        assertTrue(val.startsWith("+972"));
        assertEquals("+972", val);
    }

    @Test
    public void testOnlyNumbersAllowedInInput() {
        phoneField.clearPhoneInputKeepingPrefix();
        phoneField.setPhoneInput("abc!@#120");
        String val = phoneField.getPhoneInputValue();
        assertTrue(val.startsWith("+972"), "Phone input should start with +972 prefix");

        String numberPart = val.substring("+972".length());
        assertFalse(numberPart.contains("abc!@#"), "Phone input should not contain invalid characters");
        assertTrue(numberPart.contains("120"), "Phone input should contain '120'");
        assertTrue(numberPart.length() <= 10, "Phone number length should be at most 10 digits");
    }

    @Test
    public void testTooltipButtonAndText() {
        assertTrue(phoneField.isPhoneTooltipButtonVisible());
        assertFalse(phoneField.isTooltipTextVisible());

        phoneField.clickPhoneTooltipButton();

        assertTrue(phoneField.isTooltipTextVisible());
        assertTrue(phoneField.getTooltipText().contains("לנייד זה יישלח כעת קוד אימות"));
    }

    @Test
    public void testTooltipClosedOnPageLoad() {
        assertTrue(phoneField.isTooltipClosedOnPageLoad());
    }

    @Test
    public void testIsOnSecondPage() {
        // אם secondPage.isOnSecondPage() נבדק כבר ב-setup, הטסט הזה יכול להיות מיותר,
        // אלא אם כן יש סיבה ספציפית לבדוק זאת שוב.
        assertTrue(secondPage.isOnSecondPage());
    }
}