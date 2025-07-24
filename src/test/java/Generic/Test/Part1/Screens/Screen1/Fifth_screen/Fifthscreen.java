package Generic.Test.Part1.Screens.Screen1.Fifth_screen;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Fifthscreen extends BaseTest_Generic {

    private First firstPage;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;
    private Third_screen thirdScreen;
    private Fourth_screen fourthScreen;
    private Fifth_screen fifthScreen;

    @BeforeEach
    public void set() {
        firstPage = new First(driver);
        secondPage = new Second(driver);
        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);
        fifthScreen = new Fifth_screen(driver);



        firstPage.goToSecondScreen();
        assertTrue(secondPage.isOnSecondPage(), "לא במסך השני");

        thirdScreen = secondPage.goTothirdScreen();
        assertTrue(thirdScreen.isOnThirdScreen(), "לא במסך השלישי");

        fourthScreen = thirdScreen.goToFourthScreen();
        assertTrue(fourthScreen.isOnFourthScreen(), "לא במסך הרביעי");

        assertTrue(fifthScreen.isOnFifthScreen(),"לא במסך החמישי");


        /*
        DevToolsHelper devToolsHelper = new DevToolsHelper(driver);
        devToolsHelper.jumpToScreen("addressDetailsGeneric");
        fifthScreen = new Fifth_screen(driver);
        assertTrue(fifthScreen.isOnFifthScreen(), "❌ לא במסך החמישי (בקפיצה)");

         */

    }

    @Test
    @DisplayName("מילוי כל השדות הדרושים ומעבר למסך השישי")
    public void testFillAllFieldsAndGoNext() {

        Sixthscreen sixthScreen = fifthScreen.goToSixthScreen();
        assertTrue(sixthScreen.isOnSixthScreen(), "לא במסך השישי");
    }



}
