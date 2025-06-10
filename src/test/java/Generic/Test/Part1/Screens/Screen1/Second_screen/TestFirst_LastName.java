package Generic.Test.Part1.Screens.Screen1.Second_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.Second;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestFirst_LastName extends BaseTest_Generic {

    private FirstLastName FirstLastNameField;
    private Second secondPage;


    // נווט אל המסך השני (שם ושם משפחה)
    private FirstLastName navigateToSecondPage() {
        First firstPage = homePage.goToPractice();  // כניסה למסך הראשון
        if (!firstPage.isCheckboxSelected()) {
            firstPage.clickCheckbox();
        }
        firstPage.clickContinueButton();
        secondPage = new Second(driver);
        Assertions.assertTrue(secondPage.isOnSecondPage(), "לא הגעת למסך השני בהצלחה");
        return new FirstLastName(driver);  // מחזיר מופע של המסך השני
    }

    @BeforeEach
    public void setup() {
        FirstLastNameField = navigateToSecondPage();  // אתחל את המסך השני (FirstLastName)
    }

    @Test
    public void testLastNameIsDisplayedAndRequired() {
        assertTrue(FirstLastNameField.isLastNameVisible(), "שדה שם המשפחה לא מוצג");
        assertTrue(FirstLastNameField.isLastNameRequired(), "שדה שם המשפחה לא מסומן כנדרש");
    }

    // --- בדיקות הזנת ערכים וקבלת הערכים ---

    @Test
    public void testSetAndGetFirstName() {
        String testName = "ישראל";
        FirstLastNameField.setFirstName(testName);
        assertEquals(testName, FirstLastNameField.getFirstName(), "ערך השם הפרטי לא נשמר כראוי");
    }

    @Test
    public void testSetAndGetLastName() {
        String testLastName = "כהן";
        FirstLastNameField.setLastName(testLastName);
        assertEquals(testLastName, FirstLastNameField.getLastName(), "ערך שם המשפחה לא נשמר כראוי");
    }

    // --- בדיקות ניקוי שדות ומחיקת תווים ---

    @Test
    public void testClearFirstNameUsingJS() {
        FirstLastNameField.setFirstName("ישראל");
        FirstLastNameField.clearFirstNameUsingJS();
        assertEquals("", FirstLastNameField.getFirstName(), "שדה השם הפרטי לא נוקה כראוי");
    }

    @Test
    public void testClearLastNameUsingJS() {
        FirstLastNameField.setLastName("כהן");
        FirstLastNameField.clearLastNameUsingJS();
        assertEquals("", FirstLastNameField.getLastName(), "שדה שם המשפחה לא נוקה כראוי");
    }

    @Test
    public void testDeleteCharsFromFirstName() {
        String name = "ישראל";
        FirstLastNameField.setFirstName(name);
        FirstLastNameField.deleteFirstNameChars(2);
        String expected = name.substring(0, name.length() - 2);
        assertEquals(expected, FirstLastNameField.getFirstName(), "מחיקת תווים משדה השם הפרטי לא עבדה");
    }

    @Test
    public void testDeleteCharsFromLastName() {
        String lastName = "כהן";
        FirstLastNameField.setLastName(lastName);
        FirstLastNameField.deleteLastNameChars(1);
        String expected = lastName.substring(0, lastName.length() - 1);
        assertEquals(expected, FirstLastNameField.getLastName(), "מחיקת תווים משדה שם המשפחה לא עבדה");
    }

    // --- בדיקות הודעות שגיאה ---

    @Test
    public void testFirstNameErrorMessageRequired() {
        FirstLastNameField.setFirstName(""); // ריק
        FirstLastNameField.leaveFirstNameField();
        assertTrue(FirstLastNameField.isFirstNameErrorRequiredMessage(), "הודעת השגיאה לא מצביעה על שדה חובה");
    }

    @Test
    public void testLastNameErrorMessageRequired() {
        FirstLastNameField.setLastName(""); // ריק
        FirstLastNameField.leaveLastNameField();
        assertTrue(FirstLastNameField.isLastNameErrorRequiredMessage(), "הודעת השגיאה לא מצביעה על שדה חובה");
    }

    @Test
    public void testFirstNameErrorMessageHebrewOnly() {
        FirstLastNameField.setFirstName("abc123"); // לא חוקי
        FirstLastNameField.leaveFirstNameField();
        assertTrue(FirstLastNameField.isFirstNameErrorHebrewOnlyMessage(), "הודעת השגיאה לא מצביעה על אותיות עבריות בלבד");
    }

    @Test
    public void testLastNameErrorMessageHebrewOnly() {
        FirstLastNameField.setLastName("123abc"); // לא חוקי
        FirstLastNameField.leaveLastNameField();
        assertTrue(FirstLastNameField.isLastNameErrorHebrewOnlyMessage(), "הודעת השגיאה לא מצביעה על אותיות עבריות בלבד");
    }

    // --- בדיקות aria-invalid ---

    @Test
    public void testFirstNameAriaInvalid() {
        FirstLastNameField.setFirstName(""); // ריק
        FirstLastNameField.leaveFirstNameField();
        assertEquals("true", FirstLastNameField.getFirstNameAriaInvalidAttribute(), "שדה השם הפרטי לא מסומן כ-invalid בעת שגיאה");
    }

    @Test
    public void testLastNameAriaInvalid() {
        FirstLastNameField.setLastName(""); // ריק
        FirstLastNameField.leaveLastNameField();
        assertEquals("true", FirstLastNameField.getLastNameAriaInvalidAttribute(), "שדה שם המשפחה לא מסומן כ-invalid בעת שגיאה");
    }

    // --- בדיקות שהשגיאה נעלמת לאחר תיקון ---

    @Test
    public void testFirstNameErrorClearsAfterValidInput() {
        FirstLastNameField.setFirstName(""); // שגיאה
        FirstLastNameField.leaveFirstNameField();
        assertTrue(FirstLastNameField.isFirstNameErrorRequiredMessage(), "הודעת שגיאה על שדה ריק לא הופיעה");

        FirstLastNameField.setFirstName("ישראל"); // תקין
        assertEquals("", FirstLastNameField.getFirstNameErrorMessage(), "הודעת השגיאה לא נעלמה אחרי הזנת ערך תקין");
        assertEquals("false", FirstLastNameField.getFirstNameAriaInvalidAttribute(), "שדה השם הפרטי עדיין מסומן כ-invalid אחרי תיקון");
    }

    @Test
    public void testLastNameErrorClearsAfterValidInput() {
        FirstLastNameField.setLastName(""); // שגיאה
        FirstLastNameField.leaveLastNameField();
        assertTrue(FirstLastNameField.isLastNameErrorRequiredMessage(), "הודעת שגיאה על שדה ריק לא הופיעה");

        FirstLastNameField.setLastName("כהן"); // תקין
        assertEquals("", FirstLastNameField.getLastNameErrorMessage(), "הודעת השגיאה לא נעלמה אחרי הזנת ערך תקין");
        assertEquals("false", FirstLastNameField.getLastNameAriaInvalidAttribute(), "שדה שם המשפחה עדיין מסומן כ-invalid אחרי תיקון");
    }

    // --- בדיקות קלט עם רווחים בלבד ---

    @Test
    public void testFirstNameOnlySpaces() {
        FirstLastNameField.setFirstName("     ");
        FirstLastNameField.leaveFirstNameField();
        assertTrue(FirstLastNameField.isFirstNameErrorRequiredMessage(), "לא הופיעה שגיאה על קלט שהוא רק רווחים (שדה חובה)");
    }

    @Test
    public void testLastNameOnlySpaces() {
        FirstLastNameField.setLastName("     ");
        FirstLastNameField.leaveLastNameField();
        assertTrue(FirstLastNameField.isLastNameErrorRequiredMessage(), "לא הופיעה שגיאה על קלט שהוא רק רווחים בשם המשפחה");
    }

    // --- בדיקות אורך שדות ---

    @Test
    public void testFirstNameMaxLengthAllowed() {
        String validName = "שששששששששששששששששששששדדדדדדדדד"; // 30 תווים
        FirstLastNameField.setFirstName(validName);
        assertEquals(validName, FirstLastNameField.getFirstName(), "שם תקני באורך מקסימלי לא התקבל");
    }

    @Test
    public void testFirstNameTooLong() {
        String longName = "שששששששששששששששששששששדדדדדדדדדד"; // 31 תווים
        FirstLastNameField.setFirstName(longName);
        String actual = FirstLastNameField.getFirstName();
        assertTrue(actual.length() <= 30, "שדה השם הפרטי קיבל ערך ארוך מהמותר");
    }

    @Test
    public void testLastNameMaxLengthAllowed() {
        String validLastName = "שששששששששששששששששששששדדדדדדדדד"; // 30 תווים
        FirstLastNameField.setLastName(validLastName);
        assertEquals(validLastName, FirstLastNameField.getLastName(), "שם משפחה תקני באורך מקסימלי לא התקבל");
    }

    @Test
    public void testLastNameTooLong() {
        String longLastName = "שששששששששששששששששששששדדדדדדדדדד"; // 31 תווים
        FirstLastNameField.setLastName(longLastName);
        String actual = FirstLastNameField.getLastName();
        assertTrue(actual.length() <= 30, "שדה שם המשפחה קיבל ערך ארוך מהמותר");
    }

    // --- בדיקות שגיאות בזמן הקלדה (שדה בפוקוס) ---

    @Test
    public void testFirstNameInvalidInputShowsError() {
        FirstLastNameField.setFirstName("123");  // ערך לא חוקי
        FirstLastNameField.leaveFirstNameField();
        assertTrue(FirstLastNameField.isFirstNameErrorHebrewOnlyMessage(), "לא הופיעה הודעת שגיאה עבור ערך לא חוקי בשדה השם הפרטי");
    }

    @Test
    public void testLastNameInvalidInputShowsError() {
        FirstLastNameField.setLastName("123");  // ערך לא חוקי
        FirstLastNameField.leaveLastNameField();
        assertTrue(FirstLastNameField.isLastNameErrorHebrewOnlyMessage(), "לא הופיעה הודעת שגיאה עבור ערך לא חוקי בשדה שם המשפחה");
    }

    @Test
    public void testFirstNameNotEmptyAfterInvalidInput() {
        FirstLastNameField.setFirstName("123");  // ערך לא חוקי
        FirstLastNameField.leaveFirstNameField();
        assertEquals("123", FirstLastNameField.getFirstName(), "לא נשמר הערך הלא חוקי לאחר השגיאה");
    }

    @Test
    public void testLastNameNotEmptyAfterInvalidInput() {
        FirstLastNameField.setLastName("123");  // ערך לא חוקי
        FirstLastNameField.leaveLastNameField();
        assertEquals("123", FirstLastNameField.getLastName(), "לא נשמר הערך הלא חוקי לאחר השגיאה");
    }
}

