package Generic_product.Pages.Thirteen_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.fourteen_Screen.fourteen_Screen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Thirteen_Screen extends Generic_BasePage {

    private final By continueButton = By.cssSelector("[data-testid='continue-button']");



    public Thirteen_Screen(WebDriver driver) {
        super(driver);
    }

    public void clickContinueButton() {
        click(continueButton);
    }





    public fourteen_Screen completeThirteenScreenFlow() {
        clickContinueButton();
        return new fourteen_Screen(driver);
    }
    public fourteen_Screen goToFourteenScreen() {
        return completeThirteenScreenFlow();
    }




    public boolean isOnThirteenthScreen() {
        try {
            By instructionHeader = By.xpath("//*[contains(text(), 'חשוב לשים לב')]");
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(instructionHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }





}
