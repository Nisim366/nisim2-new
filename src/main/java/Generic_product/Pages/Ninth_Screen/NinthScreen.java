package Generic_product.Pages.Ninth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Tenth_Screen.TenthScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NinthScreen extends Generic_BasePage {

    private final By repaymentSourceSelectBox = By.cssSelector("div[aria-label='מקור החזר ההלוואה']");
    private final By instalmentDaySelectBox = By.cssSelector("div[aria-label='יום בחודש לביצוע החיוב']");
    private final By gracePeriodSelectBox = By.cssSelector("div[aria-label='רוצה לדחות את תחילת החזר קרן ההלוואה?']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");

    public NinthScreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnNinthScreen() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(repaymentSourceSelectBox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void clickAndChooseOption(By selectBoxDivLocator, String visibleText) {
        WebElement selectBox = wait.until(ExpectedConditions.elementToBeClickable(selectBoxDivLocator));
        selectBox.click();

        By liLocator = By.xpath("//li[contains(text(), '" + visibleText + "')]");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(liLocator));
        option.click();
    }

    public void selectRepaymentSource(String repaymentSource) {
        clickAndChooseOption(repaymentSourceSelectBox, repaymentSource);
        try {
            Thread.sleep(3000); // המתנה של 3 שניות לאחר הבחירה
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // שמירה על התנהגות תקינה במקרה של קטיעה
        }
    }


    public String getSelectedRepaymentSource() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(repaymentSourceSelectBox))
                .getText()
                .trim();
    }

    public void selectInstalmentDay(String dayText) {
        clickAndChooseOption(instalmentDaySelectBox, dayText);
    }

    public String getSelectedInstalmentDay() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(instalmentDaySelectBox))
                .getText()
                .trim();
    }

    public void selectGracePeriodOption(String optionText) {
        clickAndChooseOption(gracePeriodSelectBox, optionText);
    }

    public String getSelectedGracePeriodOption() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(gracePeriodSelectBox))
                .getText()
                .trim();
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public TenthScreen completeNinthScreenFlow() {
        String repaymentSource = "משכורת";
        String instalmentDay = "2";
        String gracePeriodOption = "לא";

        selectRepaymentSource(repaymentSource);
        if (!getSelectedRepaymentSource().equals(repaymentSource)) {
            throw new AssertionError("❌ מקור ההחזר שהוזן אינו תואם ל־" + repaymentSource);
        }

        selectInstalmentDay(instalmentDay);
        if (!getSelectedInstalmentDay().equals(instalmentDay)) {
            throw new AssertionError("❌ יום החיוב שהוזן אינו תואם ל־" + instalmentDay);
        }

        selectGracePeriodOption(gracePeriodOption);
        if (!getSelectedGracePeriodOption().contains(gracePeriodOption)) {
            throw new AssertionError("❌ אפשרות דחיית ההחזר אינה מכילה את: " + gracePeriodOption);
        }

        clickContinueButton();
        System.out.println("מסך פרטי החזר הלוואה ");

        return new TenthScreen(driver);
    }

    public TenthScreen goToTenthScreen() {
        return completeNinthScreenFlow();
    }
}
