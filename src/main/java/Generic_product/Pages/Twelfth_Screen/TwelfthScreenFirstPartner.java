package Generic_product.Pages.Twelfth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Thirteen_Screen.Thirteen_Screen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class TwelfthScreenFirstPartner extends Generic_BasePage {

    private static final Logger logger = Logger.getLogger(TwelfthScreenFirstPartner.class.getName());

    private final By termSlider = By.cssSelector("span[data-testid='loanRequest.term-muiSlider']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");
    private final By actualInstallments = By.cssSelector("p:has(strong) > strong");

    // טקסט חלקי שמופיע במסך הבא לאחר סיום תהליך הזיהוי הידני
    private final String partialTextConfirmation = "המסמכים הוגשו";

    public TwelfthScreenFirstPartner(WebDriver driver) {
        super(driver);
    }

    public boolean isOnTwelfthScreen() {
        return isElementVisible(termSlider);
    }

    public void clickContinueButton() {
        click(continueButton);
    }

    public Thirteen_Screen completeTwelfthScreenFirstPartnerFlow() {
        clickContinueButton();
        return new Thirteen_Screen(driver);
    }

    public Thirteen_Screen goTothirteenScreen() {
        return completeTwelfthScreenFirstPartnerFlow();
    }



}
