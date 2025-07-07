package Generic_product.Pages.Ninth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Tenth_Screen.TenthScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    private void clickAndChooseOption(By selectBoxDivLocator) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(selectBoxDivLocator));
        dropdown.click(); // פתיחת הדרופדאון

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform(); // שליחת חץ מטה

        try {
            Thread.sleep(2000); // המתנה של 2 שניות
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        actions.sendKeys(Keys.ENTER).perform(); // אישור הבחירה
    }



    public void selectRepaymentSource() {
        clickAndChooseOption(repaymentSourceSelectBox);

    }


    public String getSelectedRepaymentSource() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(repaymentSourceSelectBox))
                .getText()
                .trim();
    }

    public void selectInstalmentDay() {
        clickAndChooseOption(instalmentDaySelectBox);
    }

    public String getSelectedInstalmentDay() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(instalmentDaySelectBox))
                .getText()
                .trim();
    }

    public void selectGracePeriodOption() {
        clickAndChooseOption(gracePeriodSelectBox);
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

        selectRepaymentSource();
        selectInstalmentDay();
        selectGracePeriodOption();

        clickContinueButton();
        System.out.println("מסך פרטי החזר הלוואה ");

        return new TenthScreen(driver);
    }

    public TenthScreen goToTenthScreen() {
        return completeNinthScreenFlow();
    }
}
