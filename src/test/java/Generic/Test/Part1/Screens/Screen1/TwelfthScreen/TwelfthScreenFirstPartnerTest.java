package Generic.Test.Part1.Screens.Screen1.TwelfthScreen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.Eighth_Screen.EighthScreenFirstPartner;
import Generic_product.Pages.Eleventh_Screen.EleventhScreen;
import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Ninth_Screen.NinthScreen;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Seventh_screen.SeventhScreenFirstPartner;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import Generic_product.Pages.Tenth_Screen.TenthScreen;
import Generic_product.Pages.Third_screen.Third_screen;
import Generic_product.Pages.Twelfth_Screen.TwelfthScreenFirstPartner;
import Generic_product.Pages.Thirteen_Screen.Thirteen_Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwelfthScreenFirstPartnerTest extends BaseTest_Generic {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TwelfthScreenFirstPartnerTest.class.getName());

    private First firstPage;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;
    private Third_screen thirdScreen;
    private Fourth_screen fourthScreen;
    private Fifth_screen fifthScreen;
    private Sixthscreen sixthscreen;
    private SeventhScreenFirstPartner seventhscreen;
    private EighthScreenFirstPartner eighthScreenFirstPartner;
    private NinthScreen ninthScreen;
    private TenthScreen tenthScreen;
    private EleventhScreen eleventhScreen;
    private TwelfthScreenFirstPartner twelfthScreen;

    @BeforeEach
    public void set() {
        // הגעה רגילה עד מסך 12
        firstPage = new First(driver);
        assertTrue(firstPage.isOnFirstPage(), "❌ לא במסך הראשון");

        secondPage = firstPage.goToSecondScreen();
        assertTrue(secondPage.isOnSecondPage(), "❌ לא במסך השני");

        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);

        thirdScreen = secondPage.goTothirdScreen();
        assertTrue(thirdScreen.isOnThirdScreen(), "❌ לא במסך השלישי");

        fourthScreen = thirdScreen.goToFourthScreen();
        assertTrue(fourthScreen.isOnFourthScreen(), "❌ לא במסך הרביעי");

        fifthScreen = fourthScreen.goToFifthScreen();
        assertTrue(fifthScreen.isOnFifthScreen(), "❌ לא במסך החמישי");

        sixthscreen = fifthScreen.goToSixthScreen();
        assertTrue(sixthscreen.isOnSixthScreen(), "❌ לא במסך השישי");

        seventhscreen = sixthscreen.goToSeventhScreen();
        assertTrue(seventhscreen.isOnSeventhScreenFirstPartner(), "❌ לא במסך השביעי");

        eighthScreenFirstPartner = seventhscreen.goToEighthScreen();
        assertTrue(eighthScreenFirstPartner.isOnEighthScreenFirstPartner(), "❌ לא במסך השמיני");

        ninthScreen = eighthScreenFirstPartner.goToNinthScreen();
        assertTrue(ninthScreen.isOnNinthScreen(), "❌ לא במסך התשיעי");

        tenthScreen = ninthScreen.goToTenthScreen();
        assertTrue(tenthScreen.isOnTenthScreen(), "❌ לא במסך העשירי");

        eleventhScreen = tenthScreen.goToEleventhScreen();
        assertTrue(eleventhScreen.isOnEleventhScreen(), "❌ לא במסך ה־11");

        twelfthScreen = eleventhScreen.goToTwelfthScreen();
        assertTrue(twelfthScreen.isOnTwelfthScreen(), "❌ לא במסך השנים־עשר");
    }

    @Test
    public void testThirteenthScreenNavigation() {
        // שלב 1: יצירת מופע למסך ה־13 דרך לחיצה על "נמשיך"
        Thirteen_Screen thirteenthScreen = twelfthScreen.goTothirteenScreen();

        // שלב 2: בדיקת נוכחות במסך ה־13
        assertTrue(thirteenthScreen.isOnThirteenthScreen(), "❌ לא הגענו למסך ה־13 לאחר לחיצה על 'נמשיך'");
    }





}
