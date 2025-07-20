package Generic_product.Pages.Nineteenth_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Twentieth_screen.Twentiethscreen;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Nineteenthscreen extends Generic_BasePage {

    private final By pageHeader = By.id("page-header");
    private final By welcomeBackButton = By.cssSelector("[data-testid='onboarding-close-welcome-back-dialog']");
    private final By closeIconButton = By.cssSelector("[data-testid='CloseIcon']");


    public Nineteenthscreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnNineteenthScreen() {
        try {
            Thread.sleep(10000); // לא חובה אם אתה משתמש ב־explicit wait

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
            String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).getText();

            return actualText.contains("נצטרך לבצע בדיקה נוספת");
        } catch (Exception e) {
            return false;
        }
    }
    public void waitForManual() {
        try {
            Thread.sleep(300_000); // 5 דקות = 300,000 מילישניות
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("⚠️ ההמתנה הקשיחה הופסקה: " + e.getMessage());
        }
        }


        public Twentiethscreen completeNineteenthScreenFlow() {
        System.out.println("➡️ התחלת מעבר ממסך 19 למסך 20 (החלטה ידנית)");

        try {
            waitForManual(); // נסה להמתין לפופ-אפ במסך 20
            System.out.println("✅ המתנה הסתיימה - ממשיכים למסך 20");
            return new Twentiethscreen(driver);

        } catch (Exception e) {
            System.err.println("❌ שגיאה בעת ניסיון מעבר ממסך 19 למסך 20:");
            e.printStackTrace(); // מדפיס את השגיאה האמיתית
            throw new RuntimeException("❌ שגיאה בעת מעבר ממסך 19 למסך 20", e);
        }
    }



    public Twentiethscreen goToTwentiethScreen() {
        return completeNineteenthScreenFlow();
    }




}
