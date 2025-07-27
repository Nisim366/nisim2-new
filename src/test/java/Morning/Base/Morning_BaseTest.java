package Morning.Base;

import Generic_product.Generic_HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class Morning_BaseTest {

    protected WebDriver driver;
    protected Generic_HomePage homePage;
    protected JavascriptExecutor js;
    protected JavaScriptUtility jsUtil;
    protected WebDriverWait wait;

    protected final String URL = "https://morning.stage.greenlend.co.il/customer/wizard?code=b86NfisXNB%2FqgO6KttWYatynLTpoDI7VpjaC61NLwywViYR4Hqj3yi38GlCWMjRWmp7zx3suMr32jaDt68p4EdG4ISWxDd80ZpXut39c2C4%3D";


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URL);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        jsUtil = new JavaScriptUtility(driver);
        js = (JavascriptExecutor) driver;

    }
    protected void handleWelcomeBackPopupIfExists(boolean isAlreadyOnScreen) {
        if (isAlreadyOnScreen) {
            System.out.println("✅ כבר במסך הרצוי – לא בודק חזרה לתהליך");
            return;
        }

        try {
            By welcomeBackButton = By.cssSelector("[data-testid='onboarding-close-welcome-back-dialog']");
            WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = popupWait.until(ExpectedConditions.elementToBeClickable(welcomeBackButton));
            button.click();
            System.out.println("🟢 כפתור 'חזרה לתהליך' זוהה ונלחץ");
        } catch (TimeoutException e) {
            System.out.println("ℹ️ כפתור 'חזרה לתהליך' לא הופיע – ממשיכים כרגיל");
        } catch (Exception e) {
            System.out.println("⚠️ שגיאה לא צפויה בעת בדיקת הפופ־אפ: " + e.getMessage());
        }
    }







}
