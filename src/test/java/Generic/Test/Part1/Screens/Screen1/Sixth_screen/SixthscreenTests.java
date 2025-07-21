package Generic.Test.Part1.Screens.Screen1.Sixth_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Seventh_screen.SeventhScreen;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    private SeventhScreen seventhscreen;

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
        seventhscreen = new SeventhScreen(driver);


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
    public  void a (){
        SeventhScreen seventhscreen = new SeventhScreen(driver);
        sixthscreen.goToSeventhScreen();
        assertTrue(seventhscreen.isOnSeventhScreen(), "לא הגענו למסך השביעי של FIRST  לאחר לחיצה על 'נמשיך'");


    }



}
