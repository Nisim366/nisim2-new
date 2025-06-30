package Generic_product.Pages.Eleventh_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Twelfth_Screen.TwelfthScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EleventhScreen extends Generic_BasePage {

    private final By tooltipToggleButton = By.cssSelector("button[data-testid='custom.initialOfferGeneric-tooltipToggle']");
    private final By continueButton = By.cssSelector("button[data-testid='continue-button']");



    public EleventhScreen(WebDriver driver) {
        super(driver);
    }
    public boolean isOnEleventhScreen() {
        return isElementVisible(tooltipToggleButton);
    }
    public void clickContinueButton() {
        click(continueButton); // שימוש ב־Generic_BasePage
    }
    public TwelfthScreen completeEleventhScreenFlow() {


        clickContinueButton();
        System.out.println("מסך הצעת הלוואה ");


        return new TwelfthScreen(driver);
    }
    public TwelfthScreen goToTwelfthScreen() {
        return completeEleventhScreenFlow();
    }





}
