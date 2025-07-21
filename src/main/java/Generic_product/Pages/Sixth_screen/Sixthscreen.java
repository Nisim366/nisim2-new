package Generic_product.Pages.Sixth_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Seventh_screen.SeventhScreen;
import Generic_product.data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;


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
        customWait(2).until(ExpectedConditions.elementToBeClickable(selectBox)).click();

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
        return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(occupationSelectBox)).getText().trim();
    }

    public String getSubmittedOccupation() {
        return driver.findElement(occupationInput).getAttribute("value").trim();
    }

    public void selectProfessionAccounting() {
        waitUntilFieldIsEnabled(professionSelectBox);
        customWait(2).until(ExpectedConditions.elementToBeClickable(professionSelectBox)).click();
        customWait(2).until(ExpectedConditions.elementToBeClickable(professionAccountingOption)).click();
    }

    private void waitUntilFieldIsEnabled(By fieldLocator) {
        customWait(2).until(driver -> {
            try {
                return driver.findElement(fieldLocator).isDisplayed()
                        && driver.findElement(fieldLocator).isEnabled();
            } catch (Exception e) {
                return false;
            }
        });
    }

    public String getVisibleProfession() {
        return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(professionSelectBox)).getText().trim();
    }

    public String getSubmittedProfession() {
        return driver.findElement(professionInput).getAttribute("value").trim();
    }

    public void setAverageIncome(String incomeValue) {
        customWait(2).until(ExpectedConditions.elementToBeClickable(averageIncomeInput)).clear();
        customWait(2).until(ExpectedConditions.elementToBeClickable(averageIncomeInput)).sendKeys(incomeValue);
    }

    public String getAverageIncome() {
        return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(averageIncomeInput)).getAttribute("value").trim();
    }



    public void clickContinueButton() {
        customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getVisibleEmploymentStatus() {
        return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(employmentStatusSelectBox)).getText().trim();
    }



    public SeventhScreen completeSixthScreenHappyFlow() {
        UserData user = new UserData("user2");

        String incomeValue = user.employment.income;           // לדוגמה: "12000"

        selectComboBoxWithArrowAndEnter(employmentStatusSelectBox);
        selectComboBoxWithArrowAndEnter(occupationSelectBox);
        selectComboBoxWithArrowAndEnter(professionSelectBox);
        setAverageIncome(incomeValue);
        clickContinueButton();
        System.out.println("✅ מסך מצב תעסוקתי הושלם");

        return new SeventhScreen(driver);
    }


    public SeventhScreen goToSeventhScreen() {
        return completeSixthScreenHappyFlow();
    }

}
