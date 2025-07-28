package Generic_product.Pages.First_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Second_screen.Second;
import Morning.Processes.Sole.Second_screen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

public class First extends Generic_BasePage {

    private final JavaScriptUtility js;
    private WebDriverWait wait;

    // לוקייטור לצ'קבוקס במסך הראשון (בהתאם ל-input שסיפקת)
    private final By checkboxInput = By.id("meta.consents.privacyNote-checkbox");
    private final By checkboxLabel = By.xpath("//label[@id='meta.consents.privacyNote-label']//span[contains(@class, 'MuiFormControlLabel-label')]");
    private final By continueButton = By.xpath("//button[@data-testid='continue-button']");



    public First(WebDriver driver) {
        super(driver);
        js = new JavaScriptUtility(driver);

    }

    public boolean isOnFirstPage() {
        try {
            customWait(5).until(ExpectedConditions.visibilityOfElementLocated(checkboxInput));
            WebElement checkbox = driver.findElement(checkboxInput);
            return checkbox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }




    public boolean clickCheckbox() {
        try {
            WebElement label = customWait(2).until(ExpectedConditions.elementToBeClickable(checkboxLabel));
            label.click();
            return true;
        } catch (Exception e1) {
            try {
                WebElement inputCheckbox = customWait(2).until(ExpectedConditions.presenceOfElementLocated(checkboxInput));
                js.clickElementByJS(checkboxInput);
                System.out.println("Clicked checkbox input using JavaScript.");
                return true;
            } catch (Exception e2) {
                System.out.println("Failed to click checkbox input: " + e2.getMessage());
                return false;
            }
        }
    }

    public String getCheckboxLabelText() {
        try {
            WebElement label = customWait(2).until(ExpectedConditions.visibilityOfElementLocated(checkboxLabel));
            return label.getText();
        } catch (Exception e) {
            System.out.println("Label not found or error: " + e.getMessage());
            return "";
        }
    }

    public boolean isCheckboxSelected() {
        try {
            WebElement checkbox = customWait(10).until(ExpectedConditions.presenceOfElementLocated(checkboxInput));
            return checkbox.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButton() {
        try {
            customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        } catch (Exception e) {
            System.out.println("Failed to click 'Continue' button: " + e.getMessage());
        }
    }





    public Second completeFirstPageHappyFlow() {
        try {
            if (!isCheckboxSelected()) {
                clickCheckbox();
            }
            clickContinueButton();
            System.out.println("מסך 1 - זו תקופה מרגשת, ממש! ");

            Second secondPage = new Second(driver);
            return secondPage;

        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("כשל כללי ב-Happy Flow של המסך הראשון.", e);
        }
    }


    public Second goToSecondScreen() {
        return completeFirstPageHappyFlow ();
    }

}
