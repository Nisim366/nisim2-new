package Generic.Base;

import Generic_product.Generic_HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException; // ייבוא נוסף
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail; // ייבוא נוסף

public class BaseTest_Generic {

    protected WebDriver driver;
    protected Generic_HomePage homePage;
    protected JavascriptExecutor js;
    protected JavaScriptUtility jsUtil;
    protected WebDriverWait wait;

    protected final String first = "c4poqltt";
    protected final String twist = "7syhrhck";
    protected final String contractors = "jaqp7673";
    protected final String greenlend = "9qj3xzud";
    protected final String easyResult = "b6q6w49y"; // שנה כאן לכל לקוח

    protected final String Generic_URL = "https://app.stage.greenlend.co.il/customer/wizard?channel=" + first;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // הגדרות למניעת פופאפים של הרשאות דפדפן
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 1);      // לאשר מיקרופון
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);   // לאשר מצלמה
        prefs.put("profile.default_content_setting_values.geolocation", 1);           // לאשר מיקום (אם נדרש)
        prefs.put("profile.default_content_setting_values.notifications", 1);         // לאשר התראות

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(Generic_URL);

        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        jsUtil = new JavaScriptUtility(driver);
        homePage = new Generic_HomePage(driver);
        js = (JavascriptExecutor) driver;
    }



    public void navigateToApplicationUrl() {
        try {
            wait.until(ExpectedConditions.urlContains("customer/wizard"));
        } catch (TimeoutException e) {
            fail("❌ טעינת כתובת ה-URL נכשלה. לא הגענו ל-customer/wizard בזמן. " + e.getMessage());
        }
    }

    public void waitForManualConsoleInputAndScreenTransition(String jsCommandToEnter) {
        System.out.println(jsCommandToEnter);
        boolean manualInterventionDone = true; // הגדר כאן Breakpoint
        // אין צורך בטיפול שגיאות נוסף כאן, כי זהו שלב המתנה ידני
    }

    public void verifyNewScreenHeader(String expectedHeaderText) {
        By pageHeaderLocator = By.id("page-header");
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator),
                    ExpectedConditions.textToBePresentInElementLocated(pageHeaderLocator, expectedHeaderText)
            ));
            WebElement pageHeader = driver.findElement(pageHeaderLocator);
            assertTrue(pageHeader.isDisplayed(), "כותרת הדף לא מוצגת במסך החדש.");
            String actualHeaderText = pageHeader.getText();
            assertTrue(actualHeaderText.contains(expectedHeaderText),
                    "כותרת הדף אינה מכילה את הטקסט הצפוי. צפוי: '" + expectedHeaderText + "', בפועל: '" + actualHeaderText + "'");
        } catch (TimeoutException e) {
            fail("❌ אימות כותרת המסך נכשל: הכותרת '" + expectedHeaderText + "' לא נמצאה או לא הפכה גלויה בזמן. " + e.getMessage());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            fail("❌ אימות כותרת המסך נכשל: לוקטור הכותרת 'page-header' לא נמצא בדף. " + e.getMessage());
        }
    }
}