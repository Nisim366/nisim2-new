package Generic_product.Pages.Sixteenth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Seventeenth_Screen.Seventeenth_screen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Sixteenth_Screen extends Generic_BasePage {

    private final By residentYes = By.cssSelector("[data-testid='custom.israelResidentForTax-true']");
    private final By launderingYes = By.cssSelector("[data-testid='custom.prohibitionOfMoneyLaunderingNotDenied-true']");
    private final By notPublicFigureYes = By.cssSelector("[data-testid='custom.notPublicFigure-true']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");

    public Sixteenth_Screen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnSixteenthScreen() {
        return isElementVisible(residentYes);
    }

    public void selectAllAnswersAsTrue() {
        click(residentYes);
        click(launderingYes);
        click(notPublicFigureYes);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public Seventeenth_screen goToSeventeenthScreen() {
        selectAllAnswersAsTrue();
        clickContinue();
        return new Seventeenth_screen(driver);
    }
}
