package Generic.Test.Part1.Screens.Screen1.Sixth_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Seventh_screen.Seventhscreen;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.DevToolsHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SixthscreenTests extends BaseTest_Generic {

    private First firstPage;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;
    private Third_screen thirdScreen;
    private Fourth_screen fourthScreen;
    private Fifth_screen fifthScreen;
    private Sixthscreen sixthscreen;
    private Seventhscreen seventhscreen;

    // 🔒 קבועים לשדה סטטוס תעסוקתי:
    private static final String EMPLOYMENT_STATUS_EMPLOYEE = "שכיר/ה";
    private static final String EMPLOYMENT_STATUS_SELF_EMPLOYED = "עצמאי/ת";
    private static final String EMPLOYMENT_STATUS_SOLDIER = "חייל/ת";
    private static final String EMPLOYMENT_STATUS_STUDENT = "סטודנט/ית";
    private static final String EMPLOYMENT_STATUS_UNEMPLOYED = "לא מועסק/ת";

    private static final String PROFESSION_CONSULTING = "ייעוץ מס";
    private static final String PROFESSION_MANAGEMENT = "הנהלת חשבונות";
    private static final String PROFESSION_ACCOUNTING = "ראיית חשבון";

    @BeforeEach
    public void set() {
        // 👇 פלואו מלא נשמר לשימוש עתידי (מנוטרל זמנית)
        firstPage = new First(driver);
        secondPage = new Second(driver);
        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);
        fifthScreen = new Fifth_screen(driver);
        sixthscreen = new Sixthscreen(driver);
        seventhscreen = new Seventhscreen (driver);


        firstPage.goToSecondScreen();
        assertTrue(secondPage.isOnSecondPage(), "לא במסך השני");

        thirdScreen = secondPage.goTothirdScreen();
        assertTrue(thirdScreen.isOnThirdScreen(), "לא במסך השלישי");

        fourthScreen = thirdScreen.goToFourthScreen();
        assertTrue(fourthScreen.isOnFourthScreen(), "לא במסך הרביעי");

        fifthScreen = fourthScreen.goToFifthScreen();
        assertTrue(fifthScreen.isOnFifthScreen(), "לא במסך החמישי");

        sixthscreen = fifthScreen.goToSixthScreen();
        assertTrue(sixthscreen.isOnSixthScreen(), "לא במסך השישי");

        //sixthscreen = new Sixthscreen(driver);
        //DevToolsHelper devToolsHelper = new DevToolsHelper(driver);
        //devToolsHelper.jumpToScreen("occupationAndProfessionGeneric");
        //assertTrue(sixthscreen.isOnSixthScreen(), "לא במסך השישי (קפיצה דרך JavaScript)");
    }

    @Test
    public void testEmploymentStatusAndOccupationSelection() {
        String testIncomeValue = "12345";

        // שלב 1: סטטוס תעסוקתי - עצמאי/ת
        String expectedStatus = EMPLOYMENT_STATUS_SELF_EMPLOYED;
        sixthscreen.selectEmploymentStatus(expectedStatus);

        // בדיקת טקסט שמוצג במסך
        assertEquals(expectedStatus, sixthscreen.getVisibleEmploymentStatus(), "הסטטוס המוצג לא תואם את הנבחר");

        // בדיקת הערך שנשלח לשרת (value)
        String submittedStatus = sixthscreen.getSelectedEmploymentStatus();
        System.out.println("הערך שנשלח לשרת עבור סטטוס תעסוקתי: " + submittedStatus);
        // אפשר להוסיף השוואה ל־"independent" אם תרצה

        // שלב 2: ענף - חשבונאות
        sixthscreen.selectOccupationAccounting();
        String actualOccupation = sixthscreen.getVisibleOccupation();
        assertEquals("חשבונאות", actualOccupation, "הענף המוצג לא תואם את הנבחר");

        // אפשרי: בדיקת הערך שנשלח
        // System.out.println("Occupation value: " + sixthscreen.getSubmittedOccupation());

        // שלב 3: מקצוע - ראיית חשבון
        sixthscreen.selectProfessionAccounting();
        String actualProfession = sixthscreen.getVisibleProfession();
        assertEquals(PROFESSION_ACCOUNTING, actualProfession, "המקצוע המוצג לא תואם את הנבחר");

        // אפשרי: בדיקת הערך שנשלח
        // System.out.println("Profession value: " + sixthscreen.getSubmittedProfession());

        // שלב 4: הכנסה ממוצעת
        sixthscreen.setAverageIncome(testIncomeValue);
        String actualIncome = sixthscreen.getAverageIncome();
        assertEquals(testIncomeValue, actualIncome, "שדה הכנסה לא תואם לערך שהוזן");

        // שלב 5: ניווט למסך הבא
        sixthscreen.clickContinueButton();
        Seventhscreen seventhscreen = new Seventhscreen(driver);
        assertTrue(seventhscreen.isOnSeventhScreen(), "לא הגענו למסך השביעי לאחר לחיצה על 'נמשיך'");
    }



}
