package Generic.Test.Part1.Screens.Screen1.Fourth_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.Fourth_screen.FourthScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class FourthScreenTests extends BaseTest_Generic {

    private FourthScreen fourthScreen;

    private final String EXPECTED_HEADER_TEXT_SCREEN_4 = "עכשיו כמה פרטים מזהים";
    private final String JS_COMMAND_STEP_SCREEN_4 = "identificationDetailsGeneric";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // נתיב זה נשאר למרות שיש הודעות שגיאה ספציפיות יותר ב-PO
    private final By ERROR_MESSAGE_XPATH = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 MaterialTypography muirtl-mmrqvv' and contains(text(), 'הפרטים שהוזנו אינם נכונים, נא לנסות שנית')]");


    @BeforeEach
    public void setup() {
        try {
            navigateToApplicationUrl();
            waitForManualConsoleInputAndScreenTransition(JS_COMMAND_STEP_SCREEN_4);
            verifyNewScreenHeader(EXPECTED_HEADER_TEXT_SCREEN_4);
            // המתנה ספציפית לאלמנט שדה תאריך לידה כאינדיקטור למסך הרביעי
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            localWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='תאריך לידה']/following-sibling::div//input")));

            fourthScreen = new FourthScreen(driver);
            assertTrue(fourthScreen.isOnFourthScreen(), "אובייקט ה-Page Object של המסך הרביעי לא אושר כטוען נכון.");

        } catch (Exception e) {
            fail("❌ כשל בהכנת הסביבה (setup) למסך הרביעי: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("ID Number: Verify 'Required field' message for empty input")
    void testIdNumber_RequiredFieldErrorMessage() {
        fourthScreen.enterIdNumber("");
        fourthScreen.getIdNumberInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getIdNumberErrorText();
        assertEquals("שדה זה הוא שדה חובה", actualErrorMessage);
    }

    @Test
    @DisplayName("ID Number: Verify 'Enter 9 digits' message for less than 9 digits")
    void testIdNumber_LessThan9DigitsErrorMessage() {
        fourthScreen.enterIdNumber("12345678");
        fourthScreen.getIdNumberInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getIdNumberErrorText();
        assertEquals("יש להזין 9 ספרות", actualErrorMessage);
    }

    @Test
    @DisplayName("ID Number: Verify 'Invalid ID' message for more than 9 digits")
    void testIdNumber_MoreThan9DigitsErrorMessage() {
        fourthScreen.enterIdNumber("1234567890");
        fourthScreen.getIdNumberInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getIdNumberErrorText();
        assertEquals("תעודת זהות לא תקינה", actualErrorMessage);
    }

    @Test
    @DisplayName("ID Number: Verify non-numeric input is not allowed")
    void testIdNumber_NonNumericInput() {
        String inputWithLetters = "abc123def";
        fourthScreen.enterIdNumber(inputWithLetters);
        String actualValue = fourthScreen.getIdNumberInputField().getAttribute("value");
        assertEquals("", actualValue, "שדה מספר תעודת זהות אמור לקבל ספרות בלבד");
    }

    @Test
    @DisplayName("ID Number: Verify no error message for valid 9 digits")
    void testIdNumber_Valid9Digits_NoError() {
        fourthScreen.enterIdNumber("123456789");
        fourthScreen.getIdNumberInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getIdNumberErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה עבור ת.ז. תקינה");
    }

    @Test
    @DisplayName("Issue Date: Verify 'Required field' message for empty input")
    void testIssueDate_RequiredFieldErrorMessage() {
        fourthScreen.enterIssueDate("");
        fourthScreen.getIssueDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getIssueDateErrorText();
        assertEquals("שדה זה הוא שדה חובה", actualErrorMessage);
    }

    @Test
    @DisplayName("Issue Date: Verify 'Enter digits or select from calendar' message for invalid date format/value")
    void testIssueDate_InvalidDateFormatOrValue() {
        fourthScreen.enterIssueDate("31/02/2025");
        fourthScreen.getIssueDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getIssueDateErrorText();
        assertEquals("יש להקליד ספרות בלבד או לבחור תאריך ביומן", actualErrorMessage);
    }

    @Test
    @DisplayName("Issue Date: Verify 'Future date not allowed' message for future date input")
    void testIssueDate_FutureDateInput() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        String futureDateStr = futureDate.format(formatter);
        fourthScreen.enterIssueDate(futureDateStr);
        fourthScreen.getIssueDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getIssueDateErrorText();
        assertEquals("לא ניתן לבחור תאריך עתידי", actualErrorMessage);
    }

    @Test
    @DisplayName("Issue Date: Verify future dates are disabled in DatePicker")
    void testIssueDate_DatePickerFutureDatesDisabled() {
        fourthScreen.clickIssueDateCalendarIcon();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        boolean isTomorrowEnabled = false;
        try {
            isTomorrowEnabled = driver.findElement(
                            By.xpath(String.format("//div[contains(@class, 'MuiPickersDay-root') and .//text()='%d']", tomorrow.getDayOfMonth())))
                    .isEnabled();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            isTomorrowEnabled = false;
        }

        assertFalse(isTomorrowEnabled, "תאריכים עתידיים ב-DatePicker אמורים להיות מדוסבלים");
        driver.findElement(By.cssSelector("body")).sendKeys(org.openqa.selenium.Keys.ESCAPE);
    }

    @Test
    @DisplayName("Issue Date: Verify 'Invalid date' message for date older than 1904")
    void testIssueDate_OlderThan1904Input() {
        fourthScreen.enterIssueDate("01/01/1903");
        fourthScreen.getIssueDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getIssueDateErrorText();
        assertEquals("התאריך אינו תקין", actualErrorMessage);
    }

    @Test
    @DisplayName("Issue Date: Verify selecting a valid date from DatePicker")
    void testIssueDate_SelectValidDateFromDatePicker() {
        LocalDate dateToSelect = LocalDate.now().minusYears(1).minusMonths(3).minusDays(10);
        String dateToSelectStr = dateToSelect.format(formatter);
        fourthScreen.clickIssueDateCalendarIcon();
        fourthScreen.selectDateFromDatePicker(dateToSelectStr);
        String actualValue = fourthScreen.getIssueDateInputField().getAttribute("value");
        assertEquals(dateToSelectStr, actualValue);
        String actualErrorMessage = fourthScreen.getIssueDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה לאחר בחירה תקינה מיומן");
    }

    @Test
    @DisplayName("Issue Date: Verify selecting oldest allowed date (01/01/1904) from DatePicker")
    void testIssueDate_SelectOldestAllowedDate_1904() {
        fourthScreen.clickIssueDateCalendarIcon();
        fourthScreen.selectDateFromDatePicker("01/01/1904");
        String actualValue = fourthScreen.getIssueDateInputField().getAttribute("value");
        assertEquals("01/01/1904", actualValue);
        String actualErrorMessage = fourthScreen.getIssueDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה עבור 01/01/1904");
    }

    @Test
    @DisplayName("Birth Date: Verify 'Required field' message for empty input")
    void testBirthDate_RequiredFieldErrorMessage() {
        fourthScreen.enterBirthDate("");
        fourthScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getBirthDateErrorText();
        assertEquals("שדה זה הוא שדה חובה", actualErrorMessage);
    }

    @Test
    @DisplayName("Birth Date: Verify 'Loan for 18+ only' message for age less than 18")
    void testBirthDate_AgeLessThan18() {
        LocalDate youngDate = LocalDate.now().minusYears(10);
        String youngDateStr = youngDate.format(formatter);
        fourthScreen.enterBirthDate(youngDateStr);
        fourthScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getBirthDateErrorText();
        assertEquals("הלוואה ניתנת מגיל 18 בלבד", actualErrorMessage);
    }

    @Test
    @DisplayName("Birth Date: Verify 'Loan for 18+ only' message for future date input")
    void testBirthDate_FutureDateInput() {
        LocalDate futureDate = LocalDate.now().plusDays(5);
        String futureDateStr = futureDate.format(formatter);
        fourthScreen.enterBirthDate(futureDateStr);
        fourthScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getBirthDateErrorText();
        assertEquals("הלוואה ניתנת מגיל 18 בלבד", actualErrorMessage);
    }

    @Test
    @DisplayName("Birth Date: Verify 'Enter digits or select from calendar' message for invalid date format/value")
    void testBirthDate_InvalidDateFormatOrValue() {
        fourthScreen.enterBirthDate("31/02/2000");
        fourthScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getBirthDateErrorText();
        assertEquals("יש להקליד ספרות בלבד או לבחור תאריך ביומן", actualErrorMessage);
    }

    @Test
    @DisplayName("Birth Date: Verify error message for date older than 1907")
    void testBirthDate_OlderThan1907Input() {
        fourthScreen.enterBirthDate("01/01/1906");
        fourthScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getBirthDateErrorText();
        assertNotNull(actualErrorMessage, "צפויה הודעת שגיאה עבור תאריך לידה מוקדם מ-1907");
    }

    @Test
    @DisplayName("Birth Date: Verify valid date input (age 18+) shows no error")
    void testBirthDate_ValidDateInput_Age18Plus() {
        LocalDate adultDate = LocalDate.now().minusYears(25);
        String adultDateStr = adultDate.format(formatter);
        fourthScreen.enterBirthDate(adultDateStr);
        fourthScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = fourthScreen.getBirthDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה עבור תאריך לידה תקין (18+)");
    }

    @Test
    @DisplayName("Birth Date: Verify selecting a valid date from DatePicker")
    void testBirthDate_SelectValidDateFromDatePicker() {
        LocalDate dateToSelect = LocalDate.now().minusYears(30).minusMonths(2).minusDays(5);
        String dateToSelectStr = dateToSelect.format(formatter);
        fourthScreen.clickBirthDateCalendarIcon();
        fourthScreen.selectDateFromDatePicker(dateToSelectStr);
        String actualValue = fourthScreen.getBirthDateInputField().getAttribute("value");
        assertEquals(dateToSelectStr, actualValue);
        String actualErrorMessage = fourthScreen.getBirthDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה לאחר בחירה תקינה מיומן תאריך לידה");
    }

    @Test
    @DisplayName("Birth Date: Verify selecting oldest allowed date (01/01/1907) from DatePicker")
    void testBirthDate_SelectOldestAllowedDate_1907() {
        fourthScreen.clickBirthDateCalendarIcon();
        fourthScreen.selectDateFromDatePicker("01/01/1907");
        String actualValue = fourthScreen.getBirthDateInputField().getAttribute("value");
        assertEquals("01/01/1907", actualValue);
        String actualErrorMessage = fourthScreen.getBirthDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה עבור 01/01/1907");
    }

    @Test
    @DisplayName("Verify all required fields and calendar icons are displayed")
    void testAllRequiredFieldsDisplayed() {
        assertTrue(fourthScreen.isIdNumberFieldDisplayed(), "שדה מספר ת.ז. לא מוצג");
        assertTrue(fourthScreen.isIssueDateFieldDisplayed(), "שדה תאריך הנפקה לא מוצג");
        assertTrue(fourthScreen.isIssueDateCalendarIconDisplayed(), "אייקון יומן תאריך הנפקה לא מוצג");
        assertTrue(fourthScreen.isBirthDateFieldDisplayed(), "שדה תאריך לידה לא מוצג");
        assertTrue(fourthScreen.isBirthDateCalendarIconDisplayed(), "אייקון יומן תאריך לידה לא מוצג");
    }

    @Test
    @DisplayName("Verify 'Continue' button is disabled initially")
    void testContinueButtonDisabledInitially() {
        assertFalse(fourthScreen.isContinueButtonEnabled(), "כפתור 'המשך' אמור להיות מדוסבל בעת טעינת המסך");
    }

    @Test
    @DisplayName("Verify 'Continue' button is enabled after all valid inputs")
    void testContinueButtonEnabledAfterAllValidInputs() {
        fourthScreen.fillAllRequiredFieldsWithValidData();
        fourthScreen.getIdNumberInputField().sendKeys("\t");
        fourthScreen.getIssueDateInputField().sendKeys("\t");
        fourthScreen.getBirthDateInputField().sendKeys("\t");
        assertTrue(fourthScreen.isContinueButtonEnabled(), "כפתור 'המשך' אמור להיות מופעל לאחר הזנת כל הנתונים התקינים");
    }
}