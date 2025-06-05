package Generic.Test.Part1.Screens.Screen1.Second_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Generic_HomePage;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.FirstLastName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class TestFirst_LastName extends BaseTest_Generic {

    private FirstLastName navigateToSecondPage() {
        Generic_HomePage homePage = new Generic_HomePage(driver);
        First firstPage = homePage.goToPractice();

        if (!firstPage.isCheckboxSelected()) {
            firstPage.clickCheckbox();
        }

        firstPage.clickContinueButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-testid='applicant.fullName.firstName-input']")));

        System.out.println("Navigated to second screen");

        return new FirstLastName(driver);
    }

    @Test
    public void testLastNameInputIsDisplayedAndRequired() {
        FirstLastName page = navigateToSecondPage();
        assertTrue(page.isLastNameVisible(), "שדה שם המשפחה לא מוצג");
        assertTrue(page.isLastNameRequired(), "שדה שם המשפחה לא מסומן כנדרש");
    }

    @Test
    public void testSetAndGetFirstName() {
        FirstLastName page = navigateToSecondPage();
        String testName = "ישראל";
        page.setFirstName(testName);
        assertEquals(testName, page.getFirstName(), "ערך השם הפרטי לא נשמר כראוי");
    }

    @Test
    public void testSetAndGetLastName() {
        FirstLastName page = navigateToSecondPage();
        String testLastName = "כהן";
        page.setLastName(testLastName);
        assertEquals(testLastName, page.getLastName(), "ערך שם המשפחה לא נשמר כראוי");
    }

    @Test
    public void testClearFirstNameUsingJS() {
        FirstLastName page = navigateToSecondPage();
        page.setFirstName("ישראל");
        page.clearFirstNameUsingJS();
        assertEquals("", page.getFirstName(), "שדה השם הפרטי לא נוקה כראוי");
    }

    @Test
    public void testClearLastNameUsingJS() {
        FirstLastName page = navigateToSecondPage();
        page.setLastName("כהן");
        page.clearLastNameUsingJS();
        assertEquals("", page.getLastName(), "שדה שם המשפחה לא נוקה כראוי");
    }

    @Test
    public void testDeleteCharsFromFirstNameInput() {
        FirstLastName page = navigateToSecondPage();
        String name = "ישראל";
        page.setFirstName(name);
        page.deleteFirstNameChars(2);
        String expected = name.substring(0, name.length() - 2);
        assertEquals(expected, page.getFirstName(), "מחיקת תווים משדה השם הפרטי לא עבדה");
    }

    @Test
    public void testDeleteCharsFromLastNameInput() {
        FirstLastName page = navigateToSecondPage();
        String lastName = "כהן";
        page.setLastName(lastName);
        page.deleteLastNameChars(1);
        String expected = lastName.substring(0, lastName.length() - 1);
        assertEquals(expected, page.getLastName(), "מחיקת תווים משדה שם המשפחה לא עבדה");
    }

    @Test
    public void testFirstNameErrorMessageRequired() {
        FirstLastName page = navigateToSecondPage();
        page.setFirstName(""); // ערך ריק כדי לגרום לשגיאה
        page.leaveFirstNameField();

        assertTrue(page.isFirstNameErrorDisplayed(), "הודעת שגיאה על שם פרטי לא הופיעה");
        assertTrue(page.isFirstNameErrorRequiredMessage(), "הודעת השגיאה לא מצביעה על שדה חובה");
    }

    @Test
    public void testLastNameErrorMessageRequired() {
        FirstLastName page = navigateToSecondPage();
        page.setLastName(""); // ערך ריק כדי לגרום לשגיאה
        page.leaveLastNameField();

        assertTrue(page.isLastNameErrorDisplayed(), "הודעת שגיאה על שם משפחה לא הופיעה");
        assertTrue(page.isLastNameErrorRequiredMessage(), "הודעת השגיאה לא מצביעה על שדה חובה");
    }

    @Test
    public void testFirstNameErrorMessageHebrewOnly() {
        FirstLastName page = navigateToSecondPage();
        page.setFirstName("abc123"); // ערך לא חוקי
        page.leaveFirstNameField();

        assertTrue(page.isFirstNameErrorDisplayed(), "הודעת שגיאה על שם פרטי לא הופיעה");
        assertTrue(page.isFirstNameErrorHebrewOnlyMessage(), "הודעת השגיאה לא מצביעה על אותיות עבריות בלבד");
    }

    @Test
    public void testLastNameErrorMessageHebrewOnly() {
        FirstLastName page = navigateToSecondPage();
        page.setLastName("123abc"); // ערך לא חוקי
        page.leaveLastNameField();

        assertTrue(page.isLastNameErrorDisplayed(), "הודעת שגיאה על שם משפחה לא הופיעה");
        assertTrue(page.isLastNameErrorHebrewOnlyMessage(), "הודעת השגיאה לא מצביעה על אותיות עבריות בלבד");
    }

    @Test
    public void testFirstNameAriaInvalidAttribute() {
        FirstLastName page = navigateToSecondPage();
        page.setFirstName(""); // ריק - אמור להפעיל שגיאת חובה
        page.leaveFirstNameField();

        assertTrue(page.isFirstNameErrorDisplayed(), "הודעת שגיאה על שם פרטי לא הופיעה");
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@data-testid='applicant.fullName.firstName-input']"));
        assertEquals("true", firstNameInput.getAttribute("aria-invalid"), "שדה השם הפרטי לא מסומן כ-invalid בעת שגיאה");
    }

    @Test
    public void testLastNameAriaInvalidAttribute() {
        FirstLastName page = navigateToSecondPage();
        page.setLastName(""); // ריק - אמור להפעיל שגיאת חובה
        page.leaveLastNameField();

        assertTrue(page.isLastNameErrorDisplayed(), "הודעת שגיאה על שם משפחה לא הופיעה");
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@data-testid='applicant.fullName.lastName-input']"));
        assertEquals("true", lastNameInput.getAttribute("aria-invalid"), "שדה שם המשפחה לא מסומן כ-invalid בעת שגיאה");
    }

    @Test
    public void testFirstNameErrorClearsAfterValidInput() {
        FirstLastName page = navigateToSecondPage();
        page.setFirstName(""); // מפעיל שגיאה
        page.leaveFirstNameField();

        assertTrue(page.isFirstNameErrorDisplayed(), "הודעת שגיאה לא הופיעה");
        page.setFirstName("ישראל"); // מזין ערך תקין
        assertFalse(page.isFirstNameErrorDisplayed(), "הודעת השגיאה לא נעלמה אחרי הזנת ערך תקין");
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@data-testid='applicant.fullName.firstName-input']"));
        assertEquals("false", firstNameInput.getAttribute("aria-invalid"), "שדה השם הפרטי עדיין מסומן כ-invalid אחרי תיקון");
    }

    @Test
    public void testLastNameErrorClearsAfterValidInput() {
        FirstLastName page = navigateToSecondPage();
        page.setLastName(""); // מפעיל שגיאה
        page.leaveLastNameField();

        assertTrue(page.isLastNameErrorDisplayed(), "הודעת שגיאה לא הופיעה");
        page.setLastName("כהן"); // מזין ערך תקין
        assertFalse(page.isLastNameErrorDisplayed(), "הודעת השגיאה לא נעלמה אחרי הזנת ערך תקין");
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@data-testid='applicant.fullName.lastName-input']"));
        assertEquals("false", lastNameInput.getAttribute("aria-invalid"), "שדה שם המשפחה עדיין מסומן כ-invalid אחרי תיקון");
    }
    @Test
    public void testFirstNameWithOnlySpaces() {
        FirstLastName page = navigateToSecondPage();
        page.setFirstName("     ");
        page.leaveFirstNameField();
        assertTrue(page.isFirstNameErrorDisplayed(), "לא הופיעה שגיאה על קלט שהוא רק רווחים");
    }
    @Test
    public void testFirstNameMaxLengthAllowed() {
        FirstLastName page = navigateToSecondPage();
        String validName = "שששששששששששששששששששששדדדדדדדדד"; // 30 תווים
        page.setFirstName(validName);
        assertEquals(validName, page.getFirstName(), "שם תקני באורך מקסימלי לא התקבל");
    }

    @Test
    public void testFirstNameTooLong() {
        FirstLastName page = navigateToSecondPage();
        String longName = "שששששששששששששששששששששדדדדדדדדדד"; // 31 תווים
        page.setFirstName(longName);
        String actual = page.getFirstName();
        assertTrue(actual.length() <= 30, "שדה השם הפרטי קיבל ערך ארוך מהמותר");
    }
    @Test
    public void testFirstNameErrorNotShownWhileTyping() {
        FirstLastName page = navigateToSecondPage();
        page.setFirstName("");
        // בלי לצאת מהשדה
        assertFalse(page.isFirstNameErrorDisplayed(), "שגיאה הופיעה למרות שהשדה בפוקוס");
    }


    @Test
    public void testFirstNameMixedValidAndInvalidCharacters() {
        FirstLastName page = navigateToSecondPage();
        page.setFirstName("ש123ק");
        page.leaveFirstNameField();
        assertTrue(page.isFirstNameErrorDisplayed(), "שגיאה לא הופיעה עבור קלט משולב לא חוקי");
    }
    @Test
    public void testLastNameWithOnlySpaces() {
        FirstLastName page = navigateToSecondPage();
        page.setLastName("     ");
        page.leaveLastNameField();
        assertTrue(page.isLastNameErrorDisplayed(), "לא הופיעה שגיאה על קלט שהוא רק רווחים בשם המשפחה");
    }

    @Test
    public void testLastNameMaxLengthAllowed() {
        FirstLastName page = navigateToSecondPage();
        String validLastName = "שששששששששששששששששששששדדדדדדדדד"; // 30 תווים
        page.setLastName(validLastName);
        assertEquals(validLastName, page.getLastName(), "שם משפחה תקני באורך מקסימלי לא התקבל");
    }

    @Test
    public void testLastNameTooLong() {
        FirstLastName page = navigateToSecondPage();
        String longLastName = "שששששששששששששששששששששדדדדדדדדדד"; // 31 תווים
        page.setLastName(longLastName);
        String actual = page.getLastName();
        assertTrue(actual.length() <= 30, "שדה שם המשפחה קיבל ערך ארוך מהמותר");
    }

    @Test
    public void testLastNameErrorNotShownWhileTyping() {
        FirstLastName page = navigateToSecondPage();
        page.setLastName("");
        // בלי לצאת מהשדה
        assertFalse(page.isLastNameErrorDisplayed(), "שגיאה הופיעה על שם משפחה למרות שהשדה בפוקוס");
    }

    @Test
    public void testLastNameMixedValidAndInvalidCharacters() {
        FirstLastName page = navigateToSecondPage();
        page.setLastName("ש123ק");
        page.leaveLastNameField();
        assertTrue(page.isLastNameErrorDisplayed(), "שגיאה לא הופיעה עבור קלט משולב לא חוקי בשם המשפחה");
    }

}
