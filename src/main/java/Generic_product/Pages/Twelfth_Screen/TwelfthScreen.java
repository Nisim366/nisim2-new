package Generic_product.Pages.Twelfth_Screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TwelfthScreen extends Generic_BasePage {

    private final By termSlider = By.cssSelector("span[data-testid='loanRequest.term-muiSlider']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");


    public TwelfthScreen(WebDriver driver) {
        super(driver);
    }
    public boolean isOnTwelfthScreen() {
        return isElementVisible(termSlider);
    }
    public void clickContinueButton() {
        click(continueButton); // click() מגיע מה־Generic_BasePage
    }


}
