package Generic_product.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import utilities.JavaScriptUtility; // Import the utility class

public class Generic_BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavaScriptUtility jsUtility; // Add an instance of JavaScriptUtility

    private final By backButton = By.cssSelector("[data-testid='back-button']");

    public Generic_BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        this.jsUtility = new JavaScriptUtility(driver); // Initialize JavaScriptUtility
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected WebElement find(By elementLocator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    protected void set(By elementLocator, String textToSet) {
        WebElement element = find(elementLocator); // Find the element first
        jsUtility.clearUsingCtrlADelete(element);   // Use JS utility to clear
        element.sendKeys(textToSet);                // Then send keys
    }
    public boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    protected boolean isElementEnabled(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    protected String getElementAttribute(By locator, String attribute) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.getAttribute(attribute);
        } catch (Exception e) {
            return null;
        }
    }


    // This method is now redundant since 'set' handles clearing
    // but leaving it in case there's a specific reason to clear without setting
    protected void clear(By locator) {
        WebElement element = find(locator);
        jsUtility.clearUsingCtrlADelete(element);
    }


    protected void click(By elementLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator)).click();
    }

    protected String getText(By elementLocator) {
        return find(elementLocator).getText();
    }

    public void clickBackButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
    }
}