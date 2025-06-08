package Generic_product.Pages.Third_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Third_screen extends Generic_BasePage {

    private final By header = By.id("page-header");
    private final By backButton = By.cssSelector("button[data-testid='back-button']");
    private WebDriverWait wait;

    public Third_screen(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOnThirdPage() {
        try {
            String headerText = wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
            return headerText.contains("מה קוד האימות שקיבלת?");
        } catch (Exception e) {
            System.err.println("Failed to detect third page: " + e.getMessage());
            return false;
        }
    }

    public void waitForManualOtpInput() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(90)).until(d -> true);  // Placeholder wait
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("Manual wait for OTP timed out.");
        } catch (Exception e) {
            System.err.println("Unexpected error during manual OTP wait: " + e.getMessage());
        }
    }

    public void waitForNextPageHeader(String expectedText) {
        try {
            By nextHeader = By.id("page-header");
            WebDriverWait dynamicWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            dynamicWait.until(ExpectedConditions.textToBePresentInElementLocated(nextHeader, expectedText));
        } catch (Exception e) {
            System.err.println("Failed to detect next page header with text '" + expectedText + "': " + e.getMessage());
        }
    }

    public void clickBackButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
        } catch (Exception e) {
            System.err.println("Failed to click back button: " + e.getMessage());
        }
    }
}
