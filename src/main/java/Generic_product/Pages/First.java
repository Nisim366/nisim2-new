package Generic_product.Pages;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class First extends Generic_BasePage {

    private JavaScriptUtility js;

    private final By checkboxInput = By.xpath("//input[@id='meta.consents.privacyNote-checkbox']");
    private final By checkboxLabel = By.xpath("//label[@id='meta.consents.privacyNote-label']//span[contains(@class, 'MuiFormControlLabel-label')]");
    private final By checkboxContainer = By.xpath("//span[@validationstatus='valid']");
    private final By checkboxSpan = By.xpath("//span[input[@id='meta.consents.privacyNote-checkbox']]");


    private final By continueButton = By.xpath("//button[@data-testid='continue-button']");
    private final By headerFirstPage = By.xpath("//h1[@id='page-header' and contains(text(), 'זו תקופה מרגשת')]");

    public First(WebDriver driver) {
        super(driver);
        js = new JavaScriptUtility(driver);
    }

    public boolean clickCheckbox() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            // First, click the label (visual clickable element)
            WebElement label = wait.until(ExpectedConditions.elementToBeClickable(checkboxLabel));
            label.click();
            System.out.println("Clicked checkbox label successfully.");
            return true;
        } catch (Exception e1) {
            System.out.println("Failed to click label: " + e1.getMessage());
            try {
                // Fallback: click the input directly using JavaScript
                WebElement inputCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxInput));
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
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxLabel));
            return label.getText();
        } catch (Exception e) {
            System.out.println("Label not found or error: " + e.getMessage());
            return "";
        }
    }


    public boolean isCheckboxSelected() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxInput));
            return checkbox.isSelected();
        } catch (Exception e) {
            return false;
        }
    }



    public void clickContinueButton() {
        try {
            getDriver().findElement(continueButton).click();
            System.out.println("Clicked on 'Continue' button.");
        } catch (Exception e) {
            System.out.println("Failed to click 'Continue' button: " + e.getMessage());
            System.out.println("ssss");
        }
    }

    public boolean isOnFirstPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(headerFirstPage));
            String text = getText(headerFirstPage);
            return text.contains("זו תקופה מרגשת");
        } catch (Exception e) {
            return false;
        }
    }
    public boolean verifyReturnedToFirstPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(headerFirstPage));
            String text = getText(headerFirstPage);
            boolean result = text.contains("זו תקופה מרגשת");
            System.out.println("Verify returned to first page: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("Not returned to first page: " + e.getMessage());
            return false;
        }
    }

}
