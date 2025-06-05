package Generic.Test.Part1.Screens.Screen1.Second_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.PhoneField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPhone extends BaseTest_Generic {

    private PhoneField phoneField;

    // מתודה שמבצעת את הניווט לעמוד השני ומחזירה את אובייקט PhoneField
    private PhoneField navigateToSecondPage() {
        First obj = homePage.goToPractice();
        if (!obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        obj.clickContinueButton();
        return new PhoneField(driver);
    }

    @BeforeEach
    public void setup() {
        phoneField = navigateToSecondPage();
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

        // המתנה קצרה להופעת הודעת שגיאה
        // אפשר להוסיף Thread.sleep(500) או עדיף שימוש ב-Explicit Wait בתוך PhoneField

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
        assertFalse(numberPart.contains("abc!@#"), "Pho");
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
        assertTrue(phoneField.isOnSecondPage());
    }

    @Test
    public void testBackButton() {
        phoneField.clickBackButton();

        First firstPage = new First(driver);  // יצירת אובייקט של מסך ראשון עם אותו WebDriver
        boolean isBackToFirst = firstPage.verifyReturnedToFirstPage();

        assertTrue(isBackToFirst, "The user did not return to the first page after clicking back button.");
    }

}
