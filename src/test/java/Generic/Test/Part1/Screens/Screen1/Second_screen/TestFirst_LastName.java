package Generic.Test.Part1.Screens.Screen1.Second_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.DevToolsHelper;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestFirst_LastName extends BaseTest_Generic {

    private FirstLastName page;

    // נווט אל המסך השני (שם ושם משפחה)
    private FirstLastName navigateToSecondPage() {
        First firstPage = homePage.goToPractice();  // כניסה למסך הראשון
        if (!firstPage.isCheckboxSelected()) {
            firstPage.clickCheckbox();
        }
        firstPage.clickContinueButton();
        return new FirstLastName(driver);  // מחזיר מופע של המסך השני
    }

    @BeforeEach
    public void setup() {
        page = navigateToSecondPage();  // אתחל את המסך השני (FirstLastName)
    }

    @Test
    public void testLastNameIsDisplayedAndRequired() {
        assertTrue(page.isLastNameVisible(), "שדה שם המשפחה לא מוצג");
        assertTrue(page.isLastNameRequired(), "שדה שם המשפחה לא מסומן כנדרש");
    }

    // --- בדיקות הזנת ערכים וקבלת הערכים ---

    @Test
    public void testSetAndGetFirstName() {
        String testName = "ישראל";
        page.setFirstName(testName);
        assertEquals(testName, page.getFirstName(), "ערך השם הפרטי לא נשמר כראוי");
    }

    @Test
    public void testSetAndGetLastName() {
        String testLastName = "כהן";
        page.setLastName(testLastName);
        assertEquals(testLastName, page.getLastName(), "ערך שם המשפחה לא נשמר כראוי");
    }

    // --- בדיקות ניקוי שדות ומחיקת תווים ---

    @Test
    public void testClearFirstNameUsingJS() {
        page.setFirstName("ישראל");
        page.clearFirstNameUsingJS();
        assertEquals("", page.getFirstName(), "שדה השם הפרטי לא נוקה כראוי");
    }

    @Test
    public void testClearLastNameUsingJS() {
        page.setLastName("כהן");
        page.clearLastNameUsingJS();
        assertEquals("", page.getLastName(), "שדה שם המשפחה לא נוקה כראוי");
    }

    @Test
    public void testDeleteCharsFromFirstName() {
        String name = "ישראל";
        page.setFirstName(name);
        page.deleteFirstNameChars(2);
        String expected = name.substring(0, name.length() - 2);
        assertEquals(expected, page.getFirstName(), "מחיקת תווים משדה השם הפרטי לא עבדה");
    }

    @Test
    public void testDeleteCharsFromLastName() {
        String lastName = "כהן";
        page.setLastName(lastName);
        page.deleteLastNameChars(1);
        String expected = lastName.substring(0, lastName.length() - 1);
        assertEquals(expected, page.getLastName(), "מחיקת תווים משדה שם המשפחה לא עבדה");
    }

    // --- בדיקות הודעות שגיאה ---

    @Test
    public void testFirstNameErrorMessageRequired() {
        page.setFirstName(""); // ריק
        page.leaveFirstNameField();
        assertTrue(page.isFirstNameErrorRequiredMessage(), "הודעת השגיאה לא מצביעה על שדה חובה");
    }

    @Test
    public void testLastNameErrorMessageRequired() {
        page.setLastName(""); // ריק
        page.leaveLastNameField();
        assertTrue(page.isLastNameErrorRequiredMessage(), "הודעת השגיאה לא מצביעה על שדה חובה");
    }

    @Test
    public void testFirstNameErrorMessageHebrewOnly() {
        page.setFirstName("abc123"); // לא חוקי
        page.leaveFirstNameField();
        assertTrue(page.isFirstNameErrorHebrewOnlyMessage(), "הודעת השגיאה לא מצביעה על אותיות עבריות בלבד");
    }

    @Test
    public void testLastNameErrorMessageHebrewOnly() {
        page.setLastName("123abc"); // לא חוקי
        page.leaveLastNameField();
        assertTrue(page.isLastNameErrorHebrewOnlyMessage(), "הודעת השגיאה לא מצביעה על אותיות עבריות בלבד");
    }

    // --- בדיקות aria-invalid ---

    @Test
    public void testFirstNameAriaInvalid() {
        page.setFirstName(""); // ריק
        page.leaveFirstNameField();
        assertEquals("true", page.getFirstNameAriaInvalidAttribute(), "שדה השם הפרטי לא מסומן כ-invalid בעת שגיאה");
    }

    @Test
    public void testLastNameAriaInvalid() {
        page.setLastName(""); // ריק
        page.leaveLastNameField();
        assertEquals("true", page.getLastNameAriaInvalidAttribute(), "שדה שם המשפחה לא מסומן כ-invalid בעת שגיאה");
    }

    // --- בדיקות שהשגיאה נעלמת לאחר תיקון ---

    @Test
    public void testFirstNameErrorClearsAfterValidInput() {
        page.setFirstName(""); // שגיאה
        page.leaveFirstNameField();
        assertTrue(page.isFirstNameErrorRequiredMessage(), "הודעת שגיאה על שדה ריק לא הופיעה");

        page.setFirstName("ישראל"); // תקין
        assertEquals("", page.getFirstNameErrorMessage(), "הודעת השגיאה לא נעלמה אחרי הזנת ערך תקין");
        assertEquals("false", page.getFirstNameAriaInvalidAttribute(), "שדה השם הפרטי עדיין מסומן כ-invalid אחרי תיקון");
    }

    @Test
    public void testLastNameErrorClearsAfterValidInput() {
        page.setLastName(""); // שגיאה
        page.leaveLastNameField();
        assertTrue(page.isLastNameErrorRequiredMessage(), "הודעת שגיאה על שדה ריק לא הופיעה");

        page.setLastName("כהן"); // תקין
        assertEquals("", page.getLastNameErrorMessage(), "הודעת השגיאה לא נעלמה אחרי הזנת ערך תקין");
        assertEquals("false", page.getLastNameAriaInvalidAttribute(), "שדה שם המשפחה עדיין מסומן כ-invalid אחרי תיקון");
    }

    // --- בדיקות קלט עם רווחים בלבד ---

    @Test
    public void testFirstNameOnlySpaces() {
        page.setFirstName("     ");
        page.leaveFirstNameField();
        assertTrue(page.isFirstNameErrorRequiredMessage(), "לא הופיעה שגיאה על קלט שהוא רק רווחים (שדה חובה)");
    }

    @Test
    public void testLastNameOnlySpaces() {
        page.setLastName("     ");
        page.leaveLastNameField();
        assertTrue(page.isLastNameErrorRequiredMessage(), "לא הופיעה שגיאה על קלט שהוא רק רווחים בשם המשפחה");
    }

    // --- בדיקות אורך שדות ---

    @Test
    public void testFirstNameMaxLengthAllowed() {
        String validName = "שששששששששששששששששששששדדדדדדדדד"; // 30 תווים
        page.setFirstName(validName);
        assertEquals(validName, page.getFirstName(), "שם תקני באורך מקסימלי לא התקבל");
    }

    @Test
    public void testFirstNameTooLong() {
        String longName = "שששששששששששששששששששששדדדדדדדדדד"; // 31 תווים
        page.setFirstName(longName);
        String actual = page.getFirstName();
        assertTrue(actual.length() <= 30, "שדה השם הפרטי קיבל ערך ארוך מהמותר");
    }

    @Test
    public void testLastNameMaxLengthAllowed() {
        String validLastName = "שששששששששששששששששששששדדדדדדדדד"; // 30 תווים
        page.setLastName(validLastName);
        assertEquals(validLastName, page.getLastName(), "שם משפחה תקני באורך מקסימלי לא התקבל");
    }

    @Test
    public void testLastNameTooLong() {
        String longLastName = "שששששששששששששששששששששדדדדדדדדדד"; // 31 תווים
        page.setLastName(longLastName);
        String actual = page.getLastName();
        assertTrue(actual.length() <= 30, "שדה שם המשפחה קיבל ערך ארוך מהמותר");
    }

    // --- בדיקות שגיאות בזמן הקלדה (שדה בפוקוס) ---

    @Test
    public void testFirstNameInvalidInputShowsError() {
        page.setFirstName("123");  // ערך לא חוקי
        page.leaveFirstNameField();
        assertTrue(page.isFirstNameErrorHebrewOnlyMessage(), "לא הופיעה הודעת שגיאה עבור ערך לא חוקי בשדה השם הפרטי");
    }

    @Test
    public void testLastNameInvalidInputShowsError() {
        page.setLastName("123");  // ערך לא חוקי
        page.leaveLastNameField();
        assertTrue(page.isLastNameErrorHebrewOnlyMessage(), "לא הופיעה הודעת שגיאה עבור ערך לא חוקי בשדה שם המשפחה");
    }

    @Test
    public void testFirstNameNotEmptyAfterInvalidInput() {
        page.setFirstName("123");  // ערך לא חוקי
        page.leaveFirstNameField();
        assertEquals("123", page.getFirstName(), "לא נשמר הערך הלא חוקי לאחר השגיאה");
    }

    @Test
    public void testLastNameNotEmptyAfterInvalidInput() {
        page.setLastName("123");  // ערך לא חוקי
        page.leaveLastNameField();
        assertEquals("123", page.getLastName(), "לא נשמר הערך הלא חוקי לאחר השגיאה");
    }
}

