package Generic_product.Pages.Ninth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Tenth_Screen.TenthScreen;
import Generic_product.config.ClientContext;
import Generic_product.data.Generic_UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NinthScreen extends Generic_BasePage {

    private final By repaymentSourceSelectBox = By.cssSelector("div[aria-label='מקור החזר ההלוואה']");
    private final By instalmentDaySelectBox = By.cssSelector("div[aria-label='יום בחודש לביצוע החיוב']");
    private final By gracePeriodSelectBox = By.cssSelector("div[aria-label='רוצה לדחות את תחילת החזר קרן ההלוואה?']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");

    public NinthScreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnNinthScreen() {
        return  isElementVisible(repaymentSourceSelectBox);
    }

    public void clickAndChooseOption(By selectBoxDivLocator) {
        WebElement dropdown = customWait(2).until(ExpectedConditions.elementToBeClickable(selectBoxDivLocator));
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
        return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(repaymentSourceSelectBox))
                .getText()
                .trim();
    }

    public void selectInstalmentDay() {
        clickAndChooseOption(instalmentDaySelectBox);
    }

    public String getSelectedInstalmentDay() {
        return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(instalmentDaySelectBox))
                .getText()
                .trim();
    }

    public void selectGracePeriodOption() {
        clickAndChooseOption(gracePeriodSelectBox);
    }

    public String getSelectedGracePeriodOption() {
        return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(gracePeriodSelectBox))
                .getText()
                .trim();
    }

    public void clickContinueButton() {
        customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public TenthScreen completeNinthScreenFlow() {
        // טעינת נתוני הלקוח – גם אם כרגע לא בשימוש ישיר כאן
        Generic_UserData user = new Generic_UserData(ClientContext.getClient());

        selectRepaymentSource();     // ייתכן משתמש ב־user
        selectInstalmentDay();       // כנ"ל
        selectGracePeriodOption();   // כנ"ל

        clickContinueButton();
        System.out.println("מסך 9 - מהם פרטי החזר הלוואה ? ");

        return new TenthScreen(driver);
    }

    public TenthScreen goToTenthScreen() {
        return completeNinthScreenFlow();
    }
}
