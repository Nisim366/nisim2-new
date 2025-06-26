package Generic.Test.Part1.Screens.Screen1.Third_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
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
import utilities.IsraeliIdGenerator;

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
    private Third_screen thirdScreen;


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
        thirdScreen = new Third_screen(driver);
        assertTrue(thirdScreen.isOnThirdScreen(), "לא במסך השלישי");
    }

    @Test
    @DisplayName("Sanity")
    public void testValidIdNumber_NoErrorMessage() {
        String validId = IsraeliIdGenerator.generateRandomValidIsraeliId();

        thirdScreen.enterIdNumber(validId);  // הזנת ערך תקין
        String actualValue = thirdScreen.getEnteredIdNumber();

        assertEquals(validId, actualValue, "The entered ID number was not set correctly.");

        // ודא שלא מוצגת שגיאה כלל
        boolean isErrorDisplayed = thirdScreen.isIdNumberErrorDisplayed();
        assertFalse(isErrorDisplayed, "Expected no error message for a valid ID, but an error was displayed.");
    }
    @Test
    @DisplayName("Sanity - Valid Birth Date - No Error Message")
    public void testValidBirthDate_NoErrorMessage() {
        // תאריך לידה לדוגמה - 01/01/2000
        String validBirthDate = "01/01/2000";

        thirdScreen.enterBirthDate(validBirthDate); // הזנת תאריך לידה תקין
        String actualValueRaw = thirdScreen.getEnteredBirthDate();
        String actualValue = thirdScreen.cleanDateString(actualValueRaw); // הסרת תווים נסתרים

        assertEquals(validBirthDate, actualValue, "The entered birth date was not set correctly.");

        boolean isErrorDisplayed = thirdScreen.isBirthDateErrorDisplayed();
        assertFalse(isErrorDisplayed, "Expected no error message for a valid birth date, but an error was displayed.");
    }



    @Test
    @DisplayName("Sanity")
    public void testIssueDateToday_NoError() {
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        thirdScreen.enterIssueDate(todayDate);

        String actualDateRaw = thirdScreen.getEnteredIssueDate();
        String actualDate = thirdScreen.cleanDateString(actualDateRaw);

        assertEquals(todayDate, actualDate, "The entered issue date is incorrect.");
        assertFalse(thirdScreen.isIssueDateErrorDisplayed(), "Error message displayed for today's date.");
    }
    @Test
    @DisplayName("Sanity - Select 'זכר' as gender and verify selection without error")
    public void testSelectGenderMale_NoError() {
        // בחר מגדר "זכר" דרך המתודה המיועדת
        thirdScreen.selectMaleGender();

        // ודא שהטקסט הנבחר הוא "זכר"
        String selectedGender = thirdScreen.getSelectedGender();
        assertEquals("זכר", selectedGender, "Gender selection did not match expected value.");

        // ודא שלא מוצגת שגיאה על שדה המגדר
        assertFalse(thirdScreen.isGenderErrorDisplayed(), "Expected no error message for gender, but an error was displayed.");
    }


    @Test
    @DisplayName("Sanity: Valid ID, today's issue date, birth date and gender without errors")
    public void testValidIdIssueAndBirthDatesAndGender_NoErrors() {
        // יצירת ת"ז תקינה אקראית
        String validId = IsraeliIdGenerator.generateRandomValidIsraeliId();

        // הזנת ת"ז ובדיקת התקינות
        thirdScreen.enterIdNumber(validId);
        String actualIdValue = thirdScreen.getEnteredIdNumber();
        assertEquals(validId, actualIdValue, "The entered ID number was not set correctly.");
        assertFalse(thirdScreen.isIdNumberErrorDisplayed(), "Expected no error message for a valid ID, but an error was displayed.");

        // תאריך הנפקה - תאריך של היום
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        thirdScreen.enterIssueDate(todayDate);
        String actualIssueDateRaw = thirdScreen.getEnteredIssueDate();
        String actualIssueDate = thirdScreen.cleanDateString(actualIssueDateRaw);

        assertEquals(todayDate, actualIssueDate, "The entered issue date is incorrect.");
        assertFalse(thirdScreen.isIssueDateErrorDisplayed(), "Error message displayed for today's issue date.");

        // תאריך לידה - לדוגמה 01/01/2000
        String validBirthDate = "01/01/2000";
        thirdScreen.enterBirthDate(validBirthDate);
        String actualBirthDateRaw = thirdScreen.getEnteredBirthDate();
        String actualBirthDate = thirdScreen.cleanDateString(actualBirthDateRaw);

        assertEquals(validBirthDate, actualBirthDate, "The entered birth date is incorrect.");
        assertFalse(thirdScreen.isBirthDateErrorDisplayed(), "Error message displayed for a valid birth date.");

        // בחירת מגדר - זכר
        thirdScreen.selectMaleGender();
        String selectedGender = thirdScreen.getSelectedGender();
        assertEquals("זכר", selectedGender, "Gender selection did not match expected value.");
        assertFalse(thirdScreen.isGenderErrorDisplayed(), "Expected no error message for gender, but an error was displayed.");
    }











    public void setup() {
        try {
            navigateToApplicationUrl();
            waitForManualConsoleInputAndScreenTransition(JS_COMMAND_STEP_SCREEN_4);
            verifyNewScreenHeader(EXPECTED_HEADER_TEXT_SCREEN_4);
            // המתנה ספציפית לאלמנט שדה תאריך לידה כאינדיקטור למסך הרביעי
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            localWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='תאריך לידה']/following-sibling::div//input")));

            thirdScreen = new Third_screen(driver);
            assertTrue(thirdScreen.isOnThirdScreen(), "אובייקט ה-Page Object של המסך הרביעי לא אושר כטוען נכון.");

        } catch (Exception e) {
            fail("❌ כשל בהכנת הסביבה (setup) למסך הרביעי: " + e.getMessage());
        }
    }
}