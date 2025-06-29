package Generic_product.Pages.Sixth_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public void selectEmploymentStatus(String statusText) {
        wait.until(ExpectedConditions.elementToBeClickable(employmentStatusSelectBox)).click();
        By optionLocator = By.xpath("//li[@role='option' and normalize-space(text())='" + statusText.trim() + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    public String getSelectedEmploymentStatus() {
        By employmentStatusDisplay = By.cssSelector("div[role='combobox'][aria-label='סטטוס תעסוקתי']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(employmentStatusDisplay)).getText().trim();
    }

    public void selectOccupationAccounting() {
        wait.until(ExpectedConditions.elementToBeClickable(occupationSelectBox)).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("ההמתנה בין פתיחת הרשימה ללחיצה נקטעה", e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(accountingOption)).click();
    }

    public String getSelectedOccupation() {
        By occupationDisplay = By.cssSelector("div[role='combobox'][aria-label='ענף']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(occupationDisplay)).getText().trim();
    }

    // 🆕 מקצוע – בחירה בערך ספציפי
    public void selectProfessionAccounting() {
        wait.until(ExpectedConditions.elementToBeClickable(professionSelectBox)).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("ההמתנה בין פתיחת הרשימה ללחיצה נקטעה", e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(professionAccountingOption)).click();
    }

    public String getSelectedProfession() {
        By professionDisplay = By.cssSelector("div[role='combobox'][aria-label='מקצוע']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(professionDisplay)).getText().trim();
    }

    public void setAverageIncome(String incomeValue) {
        wait.until(ExpectedConditions.elementToBeClickable(averageIncomeInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(averageIncomeInput)).sendKeys(incomeValue);
    }

    public String getAverageIncome() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(averageIncomeInput)).getAttribute("value").trim();
    }
    // 🧪 בדיקה מלאה לשדה ממוצע הכנסה
    public boolean isAverageIncomeFieldWorking(String testValue) {
        setAverageIncome(testValue);
        return testValue.equals(getAverageIncome());
    }

    // ✅ שיטת בדיקה מלאה למסך השישי
    public boolean isSixthScreenFullyFunctional(String status, String expectedOccupation, String expectedProfession, String incomeValue) {
        selectEmploymentStatus(status);
        if (!getSelectedEmploymentStatus().equals(status)) return false;

        selectOccupationAccounting();
        if (!getSelectedOccupation().equals(expectedOccupation)) return false;

        selectProfessionAccounting();
        if (!getSelectedProfession().equals(expectedProfession)) return false;

        setAverageIncome(incomeValue);
        return getAverageIncome().equals(incomeValue);
    }

    // 🆕 לחיצה על כפתור "נמשיך"
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}


