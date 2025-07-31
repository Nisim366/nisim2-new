package Generic_product.Pages.Sixth_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Seventh_screen.SeventhScreen;
import Generic_product.config.ClientContext;
import Generic_product.data.Generic_UserData;
import Generic_product.enums.EmploymentStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class Sixthscreen extends Generic_BasePage {

    // 🔽 סטטוס תעסוקתי
    // 🔽 סטטוס תעסוקתי
    private final By employmentStatusSelectBox = By.cssSelector("div[role='combobox'][aria-label='סטטוס תעסוקתי']");
    private final By employmentStatusInput = By.cssSelector("[data-testid='applicant.custom.employmentStatus-input']");
    private final By employmentStatusOptions = By.cssSelector("ul[role='listbox'] li");

    // 🔽 ענף
    private final By occupationSelectBox = By.cssSelector("div[role='combobox'][aria-label='ענף']");
    private final By occupationInput = By.cssSelector("[data-testid='custom.occupation-input']");
    private final By occupationOptions = By.cssSelector("ul[role='listbox'] li");


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


    public void selectComboBoxOption(By selectBoxLocator, By optionsLocator, String value) {
        try {
            // פותח את הדרופדאון
            customWait(2).until(ExpectedConditions.elementToBeClickable(selectBoxLocator)).click();

            // ממתין לאפשרויות
            customWait(2).until(ExpectedConditions.visibilityOfElementLocated(optionsLocator));

            // מחפש אפשרות תואמת
            List<WebElement> options = driver.findElements(optionsLocator);
            for (WebElement option : options) {
                if (value.equalsIgnoreCase(option.getAttribute("data-value"))
                        || value.equalsIgnoreCase(option.getText().trim())) {
                    option.click();
                    return;
                }
            }

            throw new RuntimeException("❌ לא נמצאה אפשרות תואמת: " + value);

        } catch (Exception e) {
            throw new RuntimeException("❌ שגיאה בבחירת אפשרות: " + value, e);
        }
    }




    public String getVisibleOccupation() {
        return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(occupationSelectBox)).getText().trim();
    }
    public String getSelectedEmploymentStatus() {
        try {
            Thread.sleep(5000); // המתנה של 5 שניות
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return driver.findElement(employmentStatusInput).getAttribute("value").trim();
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
    public void selectProfession(String professionValue) {
        customWait(2).until(ExpectedConditions.elementToBeClickable(professionSelectBox)).click();
        By professionOption = By.xpath("//li[@role='option' and normalize-space()='" + professionValue + "']");
        customWait(2).until(ExpectedConditions.elementToBeClickable(professionOption)).click();
    }




    public SeventhScreen completeSixthScreenHappyFlow() {
        Generic_UserData user = new Generic_UserData(ClientContext.getClient());

// סטטוס תעסוקתי
        selectComboBoxOption(employmentStatusSelectBox, employmentStatusOptions, EmploymentStatus.fromEnv(user.employment.status).getValue());

// ענף
        selectComboBoxOption(occupationSelectBox, occupationOptions, user.employment.occupation);

// מקצוע
        selectComboBoxOption(professionSelectBox, occupationOptions, user.employment.profession);



        // שאר השדות
        setAverageIncome(user.employment.income);
        clickContinueButton();

        System.out.println("מסך 6 - מהו מצבך תעסוקתי ");

        return new SeventhScreen(driver);
    }


    public SeventhScreen goToSeventhScreen() {
        return completeSixthScreenHappyFlow();
    }

}
