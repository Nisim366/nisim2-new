package Generic_product.Pages.Eighth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Ninth_Screen.NinthScreen;
import Generic_product.data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EighthScreenFirstPartner extends Generic_BasePage {
    private final By loanAmountInput = By.cssSelector("[data-testid='loanRequest.amount-input']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");

    public EighthScreenFirstPartner(WebDriver driver) {
        super(driver);
    }

    public boolean isOnEighthScreenFirstPartner() {
         return isElementVisible(loanAmountInput);
    }

    public void clickContinueButton() {
        click(continueButton);
    }


    public void enterLoanAmount(String amount) {
        WebElement input = customWait(2).until(ExpectedConditions.elementToBeClickable(loanAmountInput));
        input.sendKeys(amount);

        // שלח TAB כדי לוודא שהערך ננעל בשדה (עוזר כשיש JS מאחורי הקלעים)
        input.sendKeys(Keys.TAB);

        // המתנה קצרה כדי לוודא שה־DOM עודכן
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {

        }
    }
    public NinthScreen completeEighthScreenFirstPartnerFlow() {
        UserData user = new UserData("user2");

        String amount = user.loan.amount;

        try {
            enterLoanAmount(amount);
            clickContinueButton();
            System.out.println("✅ מסך סכום הלוואה הושלם");
            return new NinthScreen(driver);
        } catch (Exception e) {
            throw new RuntimeException("❌ שגיאה בהשלמת מסך סכום הלוואה", e);
        }
    }

    public NinthScreen goToNinthScreen() {
        return completeEighthScreenFirstPartnerFlow();
    }
}
