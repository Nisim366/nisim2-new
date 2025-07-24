package Morning.Processes.Sole;

import Morning.Base.Morning_BasePage;
import org.openqa.selenium.WebDriver;

public class First_screen extends Morning_BasePage {

    public First_screen(WebDriver driver) {
        super(driver);
    }

    private Second_screen completeSecondScreenHappyFlow(){

        handleWelcomeBackPopupIfExists();
        clickContinueButton();
        System.out.println("מסך 1 - שמחים שהגעת אלינו ");
        return new Second_screen(driver);
    }

    public Second_screen goToSecondScreen() {
        return completeSecondScreenHappyFlow();
    }
}
