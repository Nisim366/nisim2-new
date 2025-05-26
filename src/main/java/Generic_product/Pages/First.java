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

    private JavaScriptUtility jsUtil;

    private final By checkboxInput = By.xpath("//input[@id='meta.consents.privacyNote-checkbox']");
    private final By checkboxLabel = By.xpath("//label[@id='meta.consents.privacyNote-label']//span[contains(@class, 'MuiFormControlLabel-label')]");
    private final By checkboxContainer = By.xpath("//span[@validationstatus='valid']");
    private final By checkboxSpan = By.xpath("//span[input[@id='meta.consents.privacyNote-checkbox']]");


    private final By continueButton = By.xpath("//button[@data-testid='continue-button']");
    private final By headerFirstPage = By.xpath("//h1[@id='page-header' and contains(text(), 'זו תקופה מרגשת')]");

    public First(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
    }

    public void clickCheckbox() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            WebElement checkboxVisual = wait.until(ExpectedConditions.elementToBeClickable(checkboxSpan));
            checkboxVisual.click();
        } catch (Exception e) {
            System.out.println("Failed to click checkbox: " + e.getMessage());
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
            return getDriver().findElement(checkboxInput).isSelected();
        } catch (Exception e) {
            System.out.println("Checkbox not found or error: " + e.getMessage());
            return false;
        }
    }


    public void clickContinueButton() {
        try {
            getDriver().findElement(continueButton).click();
            System.out.println("Clicked on 'Continue' button.");
        } catch (Exception e) {
            System.out.println("Failed to click 'Continue' button: " + e.getMessage());
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
}
