package Generic.Test.Part1.Screens.Screen1.Second_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Screen2 extends BaseTest_Generic {

    private First firstPage;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;
    private Third_screen thirdPage;


    private final String EXPECTED_HEADER_TEXT_SCREEN_2 = "נתחיל בכמה פרטים אישיים";
    private final String JS_COMMAND_STEP_SCREEN_2 = "ezbob.actions.userState.setCurrentStepByName('contactDetailsGeneric')";

    @BeforeEach
    public void set() {
        firstPage = new First(driver);
        secondPage = new Second(driver);
        thirdPage = new  Third_screen (driver);
        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);

        firstPage.goToSecondScreen();
        assertTrue(secondPage.isOnSecondPage(), "❌ לא במסך  השני");

    }

    @Test
    public void testGoToThirdScreen() {
        secondPage.goTothirdScreen();
        assertTrue(thirdPage.isOnThirdScreen(), "❌ לא הגעת במסך השלישי");

    }

}