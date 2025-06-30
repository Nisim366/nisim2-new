package Generic_product.Pages.Eighth_Screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EighthScreenFirstPartner extends Generic_BasePage {
    private final By loanAmountInput = By.cssSelector("[data-testid='loanRequest.amount-input']");
    private final By continueButton = By.cssSelector("button[data-testid='continue-button']"); // 🔽 כפתור "נמשיך"




    public EighthScreenFirstPartner(WebDriver driver) {
        super(driver);
    }

    public boolean isOnEighthScreenFirstPartner() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void enterLoanAmount(String amount) {
        wait.until(ExpectedConditions.elementToBeClickable(loanAmountInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(loanAmountInput)).sendKeys(amount);
    }
    public boolean isLoanAmountCorrectlyEntered(String expectedAmount) {
        String actualWithCommas = wait
                .until(ExpectedConditions.visibilityOfElementLocated(loanAmountInput))
                .getAttribute("value")
                .trim();

        String actual = actualWithCommas.replace(",", "");
        return expectedAmount.equals(actual);
    }
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
    public NinthScreen completeEighthScreenFirstPartnerFlow() {
        String loanAmount = "100000";
        enterLoanAmount(loanAmount);

        if (!isLoanAmountCorrectlyEntered(loanAmount)) {
            throw new AssertionError("❌ סכום ההלוואה שהוזן בפועל אינו תואם ל־" + loanAmount);
        }

        clickContinueButton();
        return new NinthScreen(driver);
    }

    public NinthScreen goToNinthScreen() {
        return completeEighthScreenFirstPartnerFlow();
    }


}
