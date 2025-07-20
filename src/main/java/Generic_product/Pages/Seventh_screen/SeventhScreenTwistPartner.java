package Generic_product.Pages.Seventh_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Eighth_Screen.EighthScreenFirstPartner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SeventhScreenTwistPartner extends Generic_BasePage {

    // לוקייטור של כפתור "רכב"
    private final By twistLoanPurposeVehicleOption = By.cssSelector("[data-testid='loanRequest.loanPurpose-toggleButtonGroup-VEHICLE']");

    // לוקייטור של כפתור "רכב" נבחר
    private final By selectedVehicleOption = By.cssSelector("div[aria-checked='true'].option-selected [data-testid='loanRequest.loanPurpose-toggleButtonGroup-VEHICLE']");
    private final By continueButton = By.cssSelector("button[data-testid='continue-button']");


    public SeventhScreenTwistPartner(WebDriver driver) {
        super(driver);
    }

    // בדיקה האם הגענו למסך השביעי של השותף הראשון
    public boolean isOnSeventhScreenFirstPartner() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(twistLoanPurposeVehicleOption)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // לחיצה על אופציית "רכב"
    public void selectVehicleOption() {
        customWait(2).until(ExpectedConditions.elementToBeClickable(twistLoanPurposeVehicleOption)).click();
    }

    // בדיקה אם "רכב" נבחר
    public boolean isVehicleOptionSelected() {
        try {
            return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(selectedVehicleOption)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickContinueButton() {
        customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public EighthScreenTwistPartner completeSeventhScreenTwistPartnerHappyFlow() {
        selectVehicleOption();

        if (!isVehicleOptionSelected()) {
            throw new AssertionError("❌ השותף FIRST לא בחר בהצלחה את מימון חופשת לידה");
        }

        clickContinueButton();
        System.out.println("מסך מטרת הלוואה ");
        return new EighthScreenTwistPartner(driver);
    }

    public EighthScreenTwistPartner goToEighthScreen() {
        return completeSeventhScreenTwistPartnerHappyFlow();
    }

}
