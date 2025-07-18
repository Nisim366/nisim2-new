package Generic.Test.Part1.Screens.Screen1.SeventhScreen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.Eighth_Screen.EighthScreenFirstPartner;
import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Seventh_screen.SeventhScreenFirstPartner;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeventhScreenFirstPartnerTest extends BaseTest_Generic {

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

    @BeforeEach
    public void set() {

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

    /*
    DevToolsHelper devToolsHelper = new DevToolsHelper(driver);
    devToolsHelper.jumpToScreen("loanPurposeGeneric");
    seventhscreen = new SeventhScreenFirstPartner(driver);
    assertTrue(seventhscreen.isOnSeventhScreenFirstPartner(), "לא במסך השביעי");
    */

    }

    @Test
    public void testFirstPartnerMaternitySelection() {
        EighthScreenFirstPartner eighthScreen = seventhscreen.goToEighthScreen();
        assertTrue(eighthScreen.isOnEighthScreenFirstPartner(), "❌ לא הגענו למסך השמיני ");
    }


}
