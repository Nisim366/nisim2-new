package Generic_product.Pages.fourteen_Screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class fourteen_Screen extends Generic_BasePage {

    private final By continueButton = By.cssSelector("[data-testid='continue-button']");


    public fourteen_Screen(WebDriver driver) {
        super(driver);
    }
    private void waitForManualProcess() {
        System.out.println("⏳ ממתין 3 דקות לסיום תהליך הזיהוי...");
        try {
            Thread.sleep(150_000); // 3 דקות
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("❌ ההמתנה להמשך תהליך הופסקה", e);
        }
        System.out.println("✅ ההמתנה הסתיימה. ממשיכים ללחוץ על הכפתור.");
    }

    public void clickContinueButtonAfterManualProcess() {
        waitForManualProcess();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }








}
