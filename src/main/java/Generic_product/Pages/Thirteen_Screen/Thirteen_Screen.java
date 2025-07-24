package Generic_product.Pages.Thirteen_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.fourteen_Screen.fourteen_Screen;
import Generic_product.config.ClientContext;
import Generic_product.data.UserData;
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
    public boolean isOnThirteenthScreen() {
        By instructionHeader = By.xpath("//*[contains(text(), 'חשוב לשים לב')]");
        return isElementVisible(instructionHeader, 90); // שימוש במתודה הגנרית
    }


    public void clickContinueButton() {
        click(continueButton);
    }


    public fourteen_Screen completeThirteenScreenFlow() {
        UserData user = new UserData(ClientContext.getClient());
        System.out.println("מסך 13 - מסך תחילת אוטנטיקס");

        clickContinueButton();
        return new fourteen_Screen(driver);
    }
    public fourteen_Screen goToFourteenScreen() {
        return completeThirteenScreenFlow();
    }

}
