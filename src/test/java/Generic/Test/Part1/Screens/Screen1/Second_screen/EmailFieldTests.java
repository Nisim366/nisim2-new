package Generic.Test.Part1.Screens.Screen1.Second_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.EmailFields;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailFieldTests extends BaseTest_Generic {

    private EmailFields emailFields;

    private EmailFields navigateToSecondPage() {
        First obj = homePage.goToPractice();
        if (!obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        obj.clickContinueButton();
        return new EmailFields(driver);
    }

    @BeforeEach
    public void setup() {
        emailFields = navigateToSecondPage();
    }

    @Test
    public void testEmailField_Empty_ShowsRequiredError() {
        emailFields.focusAndBlurEmail();
        assertTrue(emailFields.isEmailErrorDisplayed());
        assertEquals("שדה זה הוא שדה חובה", emailFields.getEmailErrorText());
    }

    @Test
    public void testEmailConfirmationField_Empty_ShowsRequiredError() {
        emailFields.focusAndBlurEmailConfirmation();
        assertTrue(emailFields.isEmailConfirmationErrorDisplayed());
        assertEquals("שדה זה הוא שדה חובה", emailFields.getEmailConfirmationErrorText());
    }

    @Test
    public void testEmailField_InvalidFormat_ShowsFormatError() {
        emailFields.setEmail("aaa");
        assertTrue(emailFields.isEmailErrorDisplayed());
        assertEquals("כתובת הדואר האלקטרוני אינה תקינה", emailFields.getEmailErrorText());
    }

    @Test
    public void testEmailConfirmationField_InvalidFormat_ShowsFormatError() {
        emailFields.setEmailConfirmation("bbb");
        assertTrue(emailFields.isEmailConfirmationErrorDisplayed());
        assertEquals("כתובת הדואר האלקטרוני אינה תקינה", emailFields.getEmailConfirmationErrorText());
    }

    @Test
    public void testEmailField_Valid_NoError() {
        emailFields.setEmail("valid@email.com");
        assertFalse(emailFields.isEmailErrorDisplayed());
    }

    @Test
    public void testEmailConfirmationField_Valid_NoError() {
        emailFields.setEmailConfirmation("valid@email.com");
        assertFalse(emailFields.isEmailConfirmationErrorDisplayed());
    }

    @Test
    public void testClearEmailField() {
        emailFields.setEmail("test@a.com");
        emailFields.clearEmail();
        assertEquals("", emailFields.getEmailValue());
    }

    @Test
    public void testClearEmailConfirmationField() {
        emailFields.setEmailConfirmation("test@a.com");
        emailFields.clearEmailConfirmation();
        assertEquals("", emailFields.getEmailConfirmationValue());
    }
}
