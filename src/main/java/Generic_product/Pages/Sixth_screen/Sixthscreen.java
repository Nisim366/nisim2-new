package Generic_product.Pages.Sixth_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Seventh_screen.SeventhScreenFirstPartner;
import Generic_product.data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Sixthscreen extends Generic_BasePage {

    // 🔽 סטטוס תעסוקתי
    private final By employmentStatusSelectBox = By.cssSelector("div[role='combobox'][aria-label='סטטוס תעסוקתי']");
    private final By employmentStatusInput = By.cssSelector("[data-testid='applicant.custom.employmentStatus-input']");

    // 🔽 ענף
    private final By occupationSelectBox = By.cssSelector("div[role='combobox'][aria-label='ענף']");
    private final By occupationInput = By.cssSelector("[data-testid='custom.occupation-input']");
    private final By accountingOption = By.xpath("//li[@role='option' and normalize-space()='חשבונאות']");

    // 🔽 מקצוע
    private final By professionSelectBox = By.cssSelector("div[role='combobox'][aria-label='מקצוע']");
    private final By professionInput = By.cssSelector("[data-testid='custom.profession-input']");
    private final By professionAccountingOption = By.xpath("//li[@role='option' and normalize-space()='ראיית חשבון']");

    // 🔽 הכנסה ממוצעת
    private final By averageIncomeInput = By.cssSelector("[data-testid='loanee.monthlyAvgIncome.average-input']");
    private final By continueButton = By.cssSelector("button[data-testid='continue-button']");

    public Sixthscreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnSixthScreen() {
        return isElementVisible(employmentStatusSelectBox);
    }



    public String getSelectedEmploymentStatus() {
        try {
            Thread.sleep(5000); // המתנה של 5 שניות
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return driver.findElement(employmentStatusInput).getAttribute("value").trim();
    }

    public void selectComboBoxWithArrowAndEnter(By selectBox) {
        wait.until(ExpectedConditions.elementToBeClickable(selectBox)).click();

        try {
            Thread.sleep(2000); // המתנה לאחר פתיחת התפריט
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("ההמתנה נקטעה לאחר פתיחת הרשימה", e);
        }

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform();

        try {
            Thread.sleep(2000); // המתנה לאחר חץ מטה
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("ההמתנה נקטעה לאחר חץ מטה", e);
        }
        actions.sendKeys(Keys.ENTER).perform();
    }



    public String getVisibleOccupation() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(occupationSelectBox)).getText().trim();
    }

    public String getSubmittedOccupation() {
        return driver.findElement(occupationInput).getAttribute("value").trim();
    }

    public void selectProfessionAccounting() {
        waitUntilFieldIsEnabled(professionSelectBox);
        wait.until(ExpectedConditions.elementToBeClickable(professionSelectBox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(professionAccountingOption)).click();
    }

    private void waitUntilFieldIsEnabled(By fieldLocator) {
        wait.until(driver -> {
            try {
                return driver.findElement(fieldLocator).isDisplayed()
                        && driver.findElement(fieldLocator).isEnabled();
            } catch (Exception e) {
                return false;
            }
        });
    }

    public String getVisibleProfession() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(professionSelectBox)).getText().trim();
    }

    public String getSubmittedProfession() {
        return driver.findElement(professionInput).getAttribute("value").trim();
    }

    public void setAverageIncome(String incomeValue) {
        wait.until(ExpectedConditions.elementToBeClickable(averageIncomeInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(averageIncomeInput)).sendKeys(incomeValue);
    }

    public String getAverageIncome() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(averageIncomeInput)).getAttribute("value").trim();
    }



    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getVisibleEmploymentStatus() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(employmentStatusSelectBox)).getText().trim();
    }



    public SeventhScreenFirstPartner completeSixthScreenHappyFlow() {
        UserData user = new UserData("user2");

        String incomeValue = user.employment.income;           // לדוגמה: "12000"

        selectComboBoxWithArrowAndEnter(employmentStatusSelectBox);
        selectComboBoxWithArrowAndEnter(occupationSelectBox);
        selectComboBoxWithArrowAndEnter(professionSelectBox);
        setAverageIncome(incomeValue);
        clickContinueButton();
        System.out.println("✅ מסך מצב תעסוקתי הושלם");

        return new SeventhScreenFirstPartner(driver);
    }


    public SeventhScreenFirstPartner goToSeventhScreen() {
        waitForSevenScreen();
        return completeSixthScreenHappyFlow();
    }

}
