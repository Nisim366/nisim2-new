package Generic_product.Pages.fourteen_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Fifteenth_Screen.Fifteenth_screen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        System.out.println("מסך 14 - מסך אוטנטיקס");

        customWait(15).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
    public Fifteenth_screen goToFifteenthScreen() {
        clickContinueButtonAfterManualProcess();
        return new Fifteenth_screen(driver);
    }








}
