package Generic.Test.Part1.Screens.Screen1.Third_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class Third_screenTest extends BaseTest_Generic {

    private First firstPage;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;
    private Third_screen ThirdScreen;


    private final String EXPECTED_HEADER_TEXT_SCREEN_4 = "עכשיו כמה פרטים מזהים";
    private final String JS_COMMAND_STEP_SCREEN_4 = "identificationDetailsGeneric";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final By ERROR_MESSAGE_XPATH = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 MaterialTypography muirtl-mmrqvv' and contains(text(), 'הפרטים שהוזנו אינם נכונים, נא לנסות שנית')]");


    @BeforeEach
    public void set() {
        firstPage = new First(driver);
        secondPage = new Second(driver);
        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);

        firstPage.goToSecondScreen();
        assertTrue(secondPage.isOnSecondPage(), "לא במסך השני");

        secondPage.goTothirdScreen();
        ThirdScreen = new Third_screen(driver);
        assertTrue(ThirdScreen.isOnThirdScreen(), "לא במסך השלישי");
    }


    @Test
    @DisplayName("ID Number: Verify 'Required field' message for empty input")
    void testIdNumber_RequiredFieldErrorMessage() {
        ThirdScreen.enterIdNumber("");
        ThirdScreen.getIdNumberInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getIdNumberErrorText();
        assertEquals("שדה זה הוא שדה חובה", actualErrorMessage);
    }

    @Test
    @DisplayName("ID Number: Verify 'Enter 9 digits' message for less than 9 digits")
    void testIdNumber_LessThan9DigitsErrorMessage() {
        ThirdScreen.enterIdNumber("12345678");
        ThirdScreen.getIdNumberInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getIdNumberErrorText();
        assertEquals("יש להזין 9 ספרות", actualErrorMessage);
    }

    @Test
    @DisplayName("ID Number: Verify 'Invalid ID' message for more than 9 digits")
    void testIdNumber_MoreThan9DigitsErrorMessage() {
        ThirdScreen.enterIdNumber("1234567890");
        ThirdScreen.getIdNumberInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getIdNumberErrorText();
        assertEquals("תעודת זהות לא תקינה", actualErrorMessage);
    }

    @Test
    @DisplayName("ID Number: Verify non-numeric input is not allowed")
    void testIdNumber_NonNumericInput() {
        String inputWithLetters = "abc123def";
        ThirdScreen.enterIdNumber(inputWithLetters);
        String actualValue = ThirdScreen.getIdNumberInputField().getAttribute("value");
        assertEquals("", actualValue, "שדה מספר תעודת זהות אמור לקבל ספרות בלבד");
    }

    @Test
    @DisplayName("ID Number: Verify no error message for valid 9 digits")
    void testIdNumber_Valid9Digits_NoError() {
        ThirdScreen.enterIdNumber("123456789");
        ThirdScreen.getIdNumberInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getIdNumberErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה עבור ת.ז. תקינה");
    }

    @Test
    @DisplayName("Issue Date: Verify 'Required field' message for empty input")
    void testIssueDate_RequiredFieldErrorMessage() {
        ThirdScreen.enterIssueDate("");
        ThirdScreen.getIssueDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getIssueDateErrorText();
        assertEquals("שדה זה הוא שדה חובה", actualErrorMessage);
    }

    @Test
    @DisplayName("Issue Date: Verify 'Enter digits or select from calendar' message for invalid date format/value")
    void testIssueDate_InvalidDateFormatOrValue() {
        ThirdScreen.enterIssueDate("31/02/2025");
        ThirdScreen.getIssueDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getIssueDateErrorText();
        assertEquals("יש להקליד ספרות בלבד או לבחור תאריך ביומן", actualErrorMessage);
    }

    @Test
    @DisplayName("Issue Date: Verify 'Future date not allowed' message for future date input")
    void testIssueDate_FutureDateInput() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        String futureDateStr = futureDate.format(formatter);
        ThirdScreen.enterIssueDate(futureDateStr);
        ThirdScreen.getIssueDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getIssueDateErrorText();
        assertEquals("לא ניתן לבחור תאריך עתידי", actualErrorMessage);
    }

    @Test
    @DisplayName("Issue Date: Verify future dates are disabled in DatePicker")
    void testIssueDate_DatePickerFutureDatesDisabled() {
        ThirdScreen.clickIssueDateCalendarIcon();
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
        ThirdScreen.enterIssueDate("01/01/1903");
        ThirdScreen.getIssueDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getIssueDateErrorText();
        assertEquals("התאריך אינו תקין", actualErrorMessage);
    }


    @Test
    @DisplayName("Issue Date: Verify selecting a valid date from DatePicker")
    void testIssueDate_SelectValidDateFromDatePicker() {
        // נבחר תאריך לפני שנה, 3 חודשים ו־10 ימים
        LocalDate dateToSelect = LocalDate.now().minusYears(1).minusMonths(3).minusDays(10);
        String dateToSelectStr = dateToSelect.format(formatter);

        // פתיחת ה־DatePicker
        ThirdScreen.clickIssueDateCalendarIcon();

        // מעבר לתצוגת שנה (פעם אחת בלבד)
        driver.findElement(By.cssSelector("button.MuiPickersCalendarHeader-switchViewButton")).click();

        // בחירת השנה
        ThirdScreen.selectYearInDatePicker(String.valueOf(dateToSelect.getYear()));

        // בחירת היום ישירות (בלי לעבור לתצוגת חודש)
        ThirdScreen.selectDayInDatePicker(String.valueOf(dateToSelect.getDayOfMonth()));

        // אימות הערך בשדה
        String actualValue = ThirdScreen.getIssueDateInputField().getAttribute("value");
        assertEquals(dateToSelectStr, actualValue);

        // אימות שאין שגיאה
        String actualErrorMessage = ThirdScreen.getIssueDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה לאחר בחירה תקינה מיומן");
    }



    @Test
    @DisplayName("Issue Date: Verify selecting oldest allowed date (01/01/1904) from DatePicker")
    void testIssueDate_SelectOldestAllowedDate_1904() {
        ThirdScreen.clickIssueDateCalendarIcon();
        ThirdScreen.selectDateFromDatePicker("01/01/1904");
        String actualValue = ThirdScreen.getIssueDateInputField().getAttribute("value");
        assertEquals("01/01/1904", actualValue);
        String actualErrorMessage = ThirdScreen.getIssueDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה עבור 01/01/1904");
    }

    @Test
    @DisplayName("Birth Date: Verify 'Required field' message for empty input")
    void testBirthDate_RequiredFieldErrorMessage() {
        ThirdScreen.enterBirthDate("");
        ThirdScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getBirthDateErrorText();
        assertEquals("שדה זה הוא שדה חובה", actualErrorMessage);
    }

    @Test
    @DisplayName("Birth Date: Verify 'Loan for 18+ only' message for age less than 18")
    void testBirthDate_AgeLessThan18() {
        LocalDate youngDate = LocalDate.now().minusYears(10);
        String youngDateStr = youngDate.format(formatter);
        ThirdScreen.enterBirthDate(youngDateStr);
        ThirdScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getBirthDateErrorText();
        assertEquals("הלוואה ניתנת מגיל 18 בלבד", actualErrorMessage);
    }

    @Test
    @DisplayName("Birth Date: Verify 'Loan for 18+ only' message for future date input")
    void testBirthDate_FutureDateInput() {
        LocalDate futureDate = LocalDate.now().plusDays(5);
        String futureDateStr = futureDate.format(formatter);
        ThirdScreen.enterBirthDate(futureDateStr);
        ThirdScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getBirthDateErrorText();
        assertEquals("הלוואה ניתנת מגיל 18 בלבד", actualErrorMessage);
    }

    @Test
    @DisplayName("Birth Date: Verify 'Enter digits or select from calendar' message for invalid date format/value")
    void testBirthDate_InvalidDateFormatOrValue() {
        ThirdScreen.enterBirthDate("31/02/2000");
        ThirdScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getBirthDateErrorText();
        assertEquals("יש להקליד ספרות בלבד או לבחור תאריך ביומן", actualErrorMessage);
    }

    @Test
    @DisplayName("Birth Date: Verify error message for date older than 1907")
    void testBirthDate_OlderThan1907Input() {
        ThirdScreen.enterBirthDate("01/01/1906");
        ThirdScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getBirthDateErrorText();
        assertNotNull(actualErrorMessage, "צפויה הודעת שגיאה עבור תאריך לידה מוקדם מ-1907");
    }

    @Test
    @DisplayName("Birth Date: Verify valid date input (age 18+) shows no error")
    void testBirthDate_ValidDateInput_Age18Plus() {
        LocalDate adultDate = LocalDate.now().minusYears(25);
        String adultDateStr = adultDate.format(formatter);
        ThirdScreen.enterBirthDate(adultDateStr);
        ThirdScreen.getBirthDateInputField().sendKeys("\t");
        String actualErrorMessage = ThirdScreen.getBirthDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה עבור תאריך לידה תקין (18+)");
    }

    @Test
    @DisplayName("Birth Date: Verify selecting a valid date from DatePicker")
    void testBirthDate_SelectValidDateFromDatePicker() {
        LocalDate dateToSelect = LocalDate.now().minusYears(30).minusMonths(2).minusDays(5);
        String dateToSelectStr = dateToSelect.format(formatter);
        ThirdScreen.clickBirthDateCalendarIcon();
        ThirdScreen.selectDateFromDatePicker(dateToSelectStr);
        String actualValue = ThirdScreen.getBirthDateInputField().getAttribute("value");
        assertEquals(dateToSelectStr, actualValue);
        String actualErrorMessage = ThirdScreen.getBirthDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה לאחר בחירה תקינה מיומן תאריך לידה");
    }

    @Test
    @DisplayName("Birth Date: Verify selecting oldest allowed date (01/01/1907) from DatePicker")
    void testBirthDate_SelectOldestAllowedDate_1907() {
        ThirdScreen.clickBirthDateCalendarIcon();
        ThirdScreen.selectDateFromDatePicker("01/01/1907");
        String actualValue = ThirdScreen.getBirthDateInputField().getAttribute("value");
        assertEquals("01/01/1907", actualValue);
        String actualErrorMessage = ThirdScreen.getBirthDateErrorText();
        assertNull(actualErrorMessage, "לא אמורה להיות הודעת שגיאה עבור 01/01/1907");
    }

    @Test
    @DisplayName("Verify all required fields and calendar icons are displayed")
    void testAllRequiredFieldsDisplayed() {
        assertTrue(ThirdScreen.isIdNumberFieldDisplayed(), "שדה מספר ת.ז. לא מוצג");
        assertTrue(ThirdScreen.isIssueDateFieldDisplayed(), "שדה תאריך הנפקה לא מוצג");
        assertTrue(ThirdScreen.isIssueDateCalendarIconDisplayed(), "אייקון יומן תאריך הנפקה לא מוצג");
        assertTrue(ThirdScreen.isBirthDateFieldDisplayed(), "שדה תאריך לידה לא מוצג");
        assertTrue(ThirdScreen.isBirthDateCalendarIconDisplayed(), "אייקון יומן תאריך לידה לא מוצג");
    }

    @Test
    @DisplayName("Verify 'Continue' button is disabled initially")
    void testContinueButtonDisabledInitially() {
        assertFalse(ThirdScreen.isContinueButtonEnabled(), "כפתור 'המשך' אמור להיות מדוסבל בעת טעינת המסך");
    }


    @Test
    @DisplayName("Verify transition to Fourth screen after filling all valid inputs in Third screen")
    void testTransitionToFourthScreenAfterValidInputs() {
        Fourth_screen fourthScreen = ThirdScreen.fillAllRequiredFieldsWithValidData();


    }




    public void setup() {
        try {
            navigateToApplicationUrl();
            waitForManualConsoleInputAndScreenTransition(JS_COMMAND_STEP_SCREEN_4);
            verifyNewScreenHeader(EXPECTED_HEADER_TEXT_SCREEN_4);
            // המתנה ספציפית לאלמנט שדה תאריך לידה כאינדיקטור למסך הרביעי
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            localWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='תאריך לידה']/following-sibling::div//input")));

            ThirdScreen = new Third_screen(driver);
            assertTrue(ThirdScreen.isOnThirdScreen(), "אובייקט ה-Page Object של המסך הרביעי לא אושר כטוען נכון.");

        } catch (Exception e) {
            fail("❌ כשל בהכנת הסביבה (setup) למסך הרביעי: " + e.getMessage());
        }
    }
}