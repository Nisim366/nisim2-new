package Generic.Test.Part1.Screens.Screen1.Sixth_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    // 🔒 קבועים לשדה סטטוס תעסוקתי:
    private static final String EMPLOYMENT_STATUS_EMPLOYEE = "שכיר/ה";
    private static final String EMPLOYMENT_STATUS_SELF_EMPLOYED = "עצמאי/ת";
    private static final String EMPLOYMENT_STATUS_SOLDIER = "חייל/ת";
    private static final String EMPLOYMENT_STATUS_STUDENT = "סטודנט/ית";
    private static final String EMPLOYMENT_STATUS_UNEMPLOYED = "לא מועסק/ת";

    @BeforeEach
    public void set() {
        firstPage = new First(driver);
        secondPage = new Second(driver);
        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);
        fifthScreen = new Fifth_screen(driver);
        sixthscreen = new Sixthscreen(driver);

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
    }

    @Test
    public void testSelectEmploymentStatusAndContinue() {
        sixthscreen.selectEmploymentStatus(EMPLOYMENT_STATUS_EMPLOYEE);
    }
}
