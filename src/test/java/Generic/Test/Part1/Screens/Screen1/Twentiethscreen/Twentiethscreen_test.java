package Generic.Test.Part1.Screens.Screen1.Twentiethscreen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.Eighteenth_screen.Eighteenth_screen;
import Generic_product.Pages.Eighth_Screen.EighthScreenFirstPartner;
import Generic_product.Pages.Eleventh_Screen.EleventhScreen;
import Generic_product.Pages.Fifteenth_Screen.Fifteenth_screen;
import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Nineteenth_screen.Nineteenthscreen;
import Generic_product.Pages.Ninth_Screen.NinthScreen;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Seventeenth_Screen.Seventeenth_screen;
import Generic_product.Pages.Seventh_screen.SeventhScreenFirstPartner;
import Generic_product.Pages.Sixteenth_Screen.Sixteenth_Screen;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import Generic_product.Pages.Tenth_Screen.TenthScreen;
import Generic_product.Pages.Third_screen.Third_screen;
import Generic_product.Pages.Thirteen_Screen.Thirteen_Screen;
import Generic_product.Pages.Twelfth_Screen.TwelfthScreenFirstPartner;
import Generic_product.Pages.Twentieth_screen.Twentiethscreen;
import Generic_product.Pages.fourteen_Screen.fourteen_Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Twentiethscreen_test extends BaseTest_Generic {

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
        private Thirteen_Screen thirteenScreen;
        private fourteen_Screen fourteenScreen;
        private Fifteenth_screen fifteenthscreen;
        private Sixteenth_Screen sixteenthScreen;
        private Seventeenth_screen seventeenthScreen;
        private Eighteenth_screen eighteenthscreen;
        private Nineteenthscreen nineteenthscreen;
        private Twentiethscreen twentiethscreen ;

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

        thirteenScreen = twelfthScreen.goTothirteenScreen();
        assertTrue(thirteenScreen.isOnThirteenthScreen(),"❌ לא במסך השלוש־עשר");

        fourteenScreen = thirteenScreen.goToFourteenScreen();

        fifteenthscreen = fourteenScreen.goToFifteenthScreen();
        assertTrue(fifteenthscreen.isOnFifteenthScreen(),"❌ לא במסך החמש־עשר");

        sixteenthScreen = fifteenthscreen.completeFifteenthScreenFlow(3);
        assertTrue(sixteenthScreen.isOnSixteenthScreen(),"❌ לא במסך השש־עשר");

        seventeenthScreen = sixteenthScreen.goToSeventeenthScreen();
        assertTrue(seventeenthScreen.isOnSeventeenthScreen(),"❌ לא במסך השבע־עשר");

        eighteenthscreen = seventeenthScreen.goToNineteenthscreen();
        assertTrue(eighteenthscreen.isOnEighteenthscreen(), "❌ לא במסך השמונה־עשר");

        nineteenthscreen = eighteenthscreen.goToNineteenthScreen();
        assertTrue(nineteenthscreen.isOnNineteenthScreen(),"❌ לא במסך התשע־עשר");

        twentiethscreen = nineteenthscreen.goToTwentiethScreen();
    }
        @Test
        public void a(){
        }
}
