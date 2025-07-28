package utilities;

import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Third_screen.Third_screen;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import Generic_product.Pages.Seventh_screen.SeventhScreen;
import Generic_product.Pages.Eighth_Screen.EighthScreen;
import Generic_product.Pages.Ninth_Screen.NinthScreen;
import Generic_product.Pages.Tenth_Screen.TenthScreen;
import Generic_product.Pages.Eleventh_Screen.EleventhScreen;
import Generic_product.Pages.Twelfth_Screen.TwelfthScreen;
import Generic_product.Pages.Thirteen_Screen.Thirteen_Screen;
import Generic_product.Pages.fourteen_Screen.fourteen_Screen;
import Generic_product.Pages.Fifteenth_Screen.Fifteenth_screen;
import Generic_product.Pages.Sixteenth_Screen.Sixteenth_Screen;
import Generic_product.Pages.Seventeenth_Screen.Seventeenth_screen;
import Generic_product.Pages.Eighteenth_screen.Eighteenth_screen;

import org.openqa.selenium.WebDriver;

public class FlowRunner {

    private final WebDriver driver;

    public FlowRunner(WebDriver driver) {
        this.driver = driver;
    }

    public void goUntil(String stopAt) {
        First first = new First(driver);
        Second second = first.goToSecondScreen();
        ensure(second.isOnSecondPage(), "❌ לא במסך השני");
        if (stopAt.equals("Second")) return;

        Third_screen third = second.goTothirdScreen();
        ensure(third.isOnThirdScreen(), "❌ לא במסך השלישי");
        if (stopAt.equals("Third")) return;

        Fourth_screen fourth = third.goToFourthScreen();
        ensure(fourth.isOnFourthScreen(), "❌ לא במסך הרביעי");
        if (stopAt.equals("Fourth")) return;

        Fifth_screen fifth = fourth.goToFifthScreen();
        ensure(fifth.isOnFifthScreen(), "❌ לא במסך החמישי");
        if (stopAt.equals("Fifth")) return;

        Sixthscreen sixth = fifth.goToSixthScreen();
        ensure(sixth.isOnSixthScreen(), "❌ לא במסך השישי");
        if (stopAt.equals("Sixth")) return;

        SeventhScreen seventh = sixth.goToSeventhScreen();
        ensure(seventh.isOnSeventhScreen(), "❌ לא במסך השביעי");
        if (stopAt.equals("Seventh")) return;

        EighthScreen eighth = seventh.goToEighthScreen();
        ensure(eighth.isOnEighthScreen(), "❌ לא במסך השמיני");
        if (stopAt.equals("Eighth")) return;

        NinthScreen ninth = eighth.goToNinthScreen();
        ensure(ninth.isOnNinthScreen(), "❌ לא במסך התשיעי");
        if (stopAt.equals("Ninth")) return;

        TenthScreen tenth = ninth.goToTenthScreen();
        ensure(tenth.isOnTenthScreen(), "❌ לא במסך העשירי");
        if (stopAt.equals("Tenth")) return;

        EleventhScreen eleventh = tenth.goToEleventhScreen();
        ensure(eleventh.isOnEleventhScreen(), "❌ לא במסך ה־11");
        if (stopAt.equals("Eleventh")) return;

        TwelfthScreen twelfth = eleventh.goToTwelfthScreen();
        ensure(twelfth.isOnTwelfthScreen(), "❌ לא במסך ה־12");
        if (stopAt.equals("Twelfth")) return;

        Thirteen_Screen thirteen = twelfth.goTothirteenScreen();
        ensure(thirteen.isOnThirteenthScreen(), "❌ לא במסך ה־13");
        if (stopAt.equals("Thirteenth")) return;

        fourteen_Screen fourteen = thirteen.goToFourteenScreen();
        if (stopAt.equals("Fourteenth")) return;

        Fifteenth_screen fifteenth = fourteen.goToFifteenthScreen();
        ensure(fifteenth.isOnFifteenthScreen(), "❌ לא במסך ה־15");
        if (stopAt.equals("Fifteenth")) return;

        Sixteenth_Screen sixteenth = fifteenth.completeFifteenthScreenFlow(3);
        ensure(sixteenth.isOnSixteenthScreen(), "❌ לא במסך ה־16");
        if (stopAt.equals("Sixteenth")) return;

        Seventeenth_screen seventeenth = sixteenth.goToSeventeenthScreen();
        ensure(seventeenth.isOnSeventeenthScreen(), "❌ לא במסך ה־17");
        if (stopAt.equals("Seventeenth")) return;

        Eighteenth_screen eighteenth = seventeenth.completeSeventeenthScreenFlow();
        ensure(eighteenth.isOnEighteenthscreen(), "❌ לא במסך ה־18");
        if (stopAt.equals("Eighteenth")) return;

        // אפשר להמשיך מכאן למסכים נוספים
    }

    private void ensure(boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalStateException(errorMessage);
        }
    }
}
