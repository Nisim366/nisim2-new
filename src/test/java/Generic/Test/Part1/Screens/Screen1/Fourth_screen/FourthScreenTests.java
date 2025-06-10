package Generic.Test.Part1.Screens.Screen1.Fourth_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Third_screen.Third_screen;
import Generic_product.Pages.Fourth_screen.FourthScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import utilities.DevToolsHelper;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class FourthScreenTests extends BaseTest_Generic {

    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;
    private Second secondPage;
    private Third_screen thirdScreen;
    private FourthScreen fourthScreen;


    private final String phone = "0532407762";
    private final String firstName = "חן";
    private final String lastName = "הניגון";
    private final String GMail = "yossi@example.com";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final By ERROR_MESSAGE_XPATH = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 MaterialTypography muirtl-mmrqvv' and contains(text(), 'הפרטים שהוזנו אינם נכונים, נא לנסות שנית')]");


    private Second navigateToSecondPage() {
        First obj = homePage.goToPractice();
        if (!obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        obj.clickContinueButton();

        Second secondPage = new Second(driver);
        assertTrue(secondPage.isOnSecondPage(), "Failed to reach the second screen.");

        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);

        return secondPage;
    }

    private Third_screen navigateToThirdPage() {
        firstLastName.setFirstName(firstName);
        firstLastName.setLastName(lastName);
        phoneField.setPhoneInput(phone);
        emailFields.setEmail(GMail);
        emailFields.setEmailConfirmation(GMail);

        assertEquals(firstName, firstLastName.getFirstName());
        assertEquals(lastName, firstLastName.getLastName());
        assertEquals(phoneField.normalizePhoneForComparison(phone), phoneField.getNormalizedPhoneValue());
        assertEquals(GMail, emailFields.getEmail());
        assertEquals(GMail, emailFields.getEmailConfirmation());

        secondPage.clickContinueButton();

        thirdScreen = new Third_screen(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'קוד האימות נשלח למספר')]")));
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(., 'לא קיבלתי קוד? שלחו שוב')]")));
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                fail("Failed to reach the third screen within expected time. Neither verification text nor resend button found.");
            }
        }

        // המיקום לבדיקה ולריפרש
        try {
            wait.withTimeout(Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_XPATH));
            driver.navigate().refresh();
            // המתנה מחודשת לאלמנט לאחר הריענון
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'קוד האימות נשלח למספר')]")));
        } catch (TimeoutException | NoSuchElementException e) {
            // ההודעה לא הופיעה, ממשיכים כרגיל
        }

        assertTrue(thirdScreen.isOnThirdPage(), "Failed to confirm being on the third screen after element visibility.");

        thirdScreen.waitForManualOtpInput();



        return thirdScreen;
    }
    private FourthScreen navigateToFourthPageAfterOtp() {
        WebDriverWait waitFourthScreen = new WebDriverWait(driver, Duration.ofSeconds(90));
        driver.navigate().refresh();
        // Using the birthDateInput locator as a reliable indicator for the Fourth Screen's presence
        waitFourthScreen.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='תאריך לידה']/following-sibling::div//input")));

        fourthScreen = new FourthScreen(driver);
        assertTrue(fourthScreen.isOnFourthScreen(), "Failed to reach the fourth screen after OTP submission.");

        return fourthScreen;
    }

    @BeforeEach
    public void setup() {
        secondPage = navigateToSecondPage();
        thirdScreen = navigateToThirdPage();
        fourthScreen = navigateToFourthPageAfterOtp();

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
        String actualValue = fourthScreen.getIdNumberInputField().getAttribute("value"); // Fixed here: was getIssueDateInputField()
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