package Generic_product.Pages.Seventh_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Eighth_Screen.EighthScreenFirstPartner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeventhScreenFirstPartner extends Generic_BasePage {

    // אלמנט מזהה למסך (הכפתור של מימון חופשת לידה)
    private final By firstLoanPurposeMaternityOption = By.cssSelector("button[data-testid='loanRequest.loanPurpose-toggleButtonGroup-MATERNITY_LEAVE']");
    private final By selectedMaternityOption = By.cssSelector("div[role='radio'][aria-checked='true'] button[data-testid='loanRequest.loanPurpose-toggleButtonGroup-MATERNITY_LEAVE']");
    private final By continueButton = By.cssSelector("button[data-testid='continue-button']");


    public SeventhScreenFirstPartner(WebDriver driver) {
        super(driver);
    }
    public void waitForEigthScreen() {
        new WebDriverWait(driver, Duration.ofSeconds(90)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='loanRequest.amount-input']")));
    }

    // בדיקה האם המסך השביעי של FIRST פתוח
    public boolean isOnSeventhScreenFirstPartner() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(firstLoanPurposeMaternityOption)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // לחיצה על מימון חופשת לידה
    public void selectFirstPartnerMaternityOption() {
        wait.until(ExpectedConditions.elementToBeClickable(firstLoanPurposeMaternityOption)).click();
    }

    // בדיקה אם נבחר מימון חופשת לידה
    public boolean isFirstPartnerMaternityOptionSelected() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(selectedMaternityOption)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public EighthScreenFirstPartner completeSeventhScreenFirstPartnerHappyFlow() {
        selectFirstPartnerMaternityOption();

        if (!isFirstPartnerMaternityOptionSelected()) {
            throw new AssertionError("❌ השותף FIRST לא בחר בהצלחה את מימון חופשת לידה");
        }

        clickContinueButton();
        System.out.println("מסך מטרת הלוואה ");
        return new EighthScreenFirstPartner(driver);
    }

    public EighthScreenFirstPartner goToEighthScreen() {
        waitForEigthScreen();
        return completeSeventhScreenFirstPartnerHappyFlow();
    }


}
