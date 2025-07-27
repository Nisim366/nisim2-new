package Generic_product.Pages.Twelfth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Thirteen_Screen.Thirteen_Screen;
import Generic_product.config.ClientContext;
import Generic_product.data.Generic_UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class TwelfthScreen extends Generic_BasePage {

    private static final Logger logger = Logger.getLogger(TwelfthScreen.class.getName());

    private final By termSlider = By.cssSelector("span[data-testid='loanRequest.term-muiSlider']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");
    private final By actualInstallments = By.cssSelector("p:has(strong) > strong");

    // טקסט חלקי שמופיע במסך הבא לאחר סיום תהליך הזיהוי הידני
    private final String partialTextConfirmation = "המסמכים הוגשו";

    public TwelfthScreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnTwelfthScreen() {
        return isElementVisible(termSlider);
    }

    public void clickContinueButton() {
        click(continueButton);
    }

    public Thirteen_Screen completeTwelfthScreenFlow() {
        Generic_UserData user = new Generic_UserData(ClientContext.getClient());
        System.out.println("מסך 12 - סכום החזר חודשי");

        clickContinueButton();
        return new Thirteen_Screen(driver);
    }

    public Thirteen_Screen goTothirteenScreen() {
        return completeTwelfthScreenFlow();
    }



}
