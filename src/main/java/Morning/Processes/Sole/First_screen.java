package Morning.Processes.Sole;

import Morning.Base.Morning_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class First_screen extends Morning_BasePage {
    private final By privacyCheckbox = By.cssSelector("span.MuiCheckbox-root");



    public First_screen(WebDriver driver) {
        super(driver);
    }
    public void clickPrivacyCheckbox() {
        click(privacyCheckbox); // מתודה מתוך Morning_BasePage
    }


    private Second_screen completeSecondScreenHappyFlow(){

        handleWelcomeBackPopupIfExists();
        clickPrivacyCheckbox();
        clickContinueButton();
        System.out.println("מסך 1 - שמחים שהגעת אלינו ");
        return new Second_screen(driver);
    }

    public Second_screen goToSecondScreen() {
        return completeSecondScreenHappyFlow();
    }
}
