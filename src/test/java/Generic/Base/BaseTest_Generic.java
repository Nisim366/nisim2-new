package Generic.Base;

import Generic_product.Generic_HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail; // ייבוא נוסף

public class BaseTest_Generic {

    protected WebDriver driver;
    protected Generic_HomePage homePage;
    protected JavascriptExecutor js;
    protected JavaScriptUtility jsUtil;
    protected WebDriverWait wait;

    private final String Generic_URL = "https://app.stage.greenlend.co.il/customer/wizard?channel=c4poqltt";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
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