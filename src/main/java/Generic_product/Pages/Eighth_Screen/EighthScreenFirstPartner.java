package Generic_product.Pages.Eighth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Ninth_Screen.NinthScreen;
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
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLoanAmount(String amount) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(loanAmountInput));
        input.sendKeys(amount);

        // שלח TAB כדי לוודא שהערך ננעל בשדה (עוזר כשיש JS מאחורי הקלעים)
        input.sendKeys(Keys.TAB);

        // המתנה קצרה כדי לוודא שה־DOM עודכן
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
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
        System.out.println("מסך סכום הלוואה ");

        return new NinthScreen(driver);
    }

    public NinthScreen goToNinthScreen() {
        return completeEighthScreenFirstPartnerFlow();
    }
}
