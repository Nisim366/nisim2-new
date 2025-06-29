package Generic_product.Pages.Sixth_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Sixthscreen extends Generic_BasePage {

    // ללחיצה לפתיחת הרשימה
    private final By employmentStatusSelectBox = By.cssSelector("div[role='combobox'][aria-label='סטטוס תעסוקתי']");

    // לבדיקה או קבלת ערך נבחר
    private final By employmentStatusInput = By.cssSelector("[data-testid='applicant.custom.employmentStatus-input']");
    // ללחיצה (אם יופעל בעתיד)
    private final By occupationSelectBox = By.cssSelector("div[role='combobox'][aria-label='ענף']");

    // לבדיקה/קריאה של ערך (read-only)
    private final By occupationInput = By.cssSelector("[data-testid='custom.occupation-input']");
    // ללחיצה (אם יופעל בעתיד)
    private final By professionSelectBox = By.cssSelector("div[role='combobox'][aria-label='מקצוע']");

    // לבדיקה/קריאה של ערך (read-only)
    private final By professionInput = By.cssSelector("[data-testid='custom.profession-input']");
    // להזנת ערך או בדיקה
    private final By averageIncomeInput = By.cssSelector("[data-testid='loanee.monthlyAvgIncome.average-input']");


    public Sixthscreen(WebDriver driver) {
        super(driver);
    }
    public boolean isOnSixthScreen() {
        return isElementVisible(employmentStatusSelectBox);
    }
    public void selectEmploymentStatus(String statusText) {
        // לחץ על הקומבובוקס של סטטוס תעסוקתי
        wait.until(ExpectedConditions.elementToBeClickable(employmentStatusSelectBox)).click();

        // בחר את האפשרות על בסיס טקסט – מנקה רווחים מההתחלה והסוף
        By optionLocator = By.xpath("//li[@role='option' and normalize-space(text())='" + statusText.trim() + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }
    public String getSelectedEmploymentStatus() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(employmentStatusInput)).getAttribute("value");
    }




}
