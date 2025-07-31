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
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BaseTest_Generic {

    protected static WebDriver driver;
    protected static JavascriptExecutor js;
    protected static JavaScriptUtility jsUtil;
    protected static WebDriverWait wait;
    protected static Generic_HomePage homePage;



    protected static final String KABLANIM = "jaqp7673";
    protected static final String FIRST = "c4poqltt";
    protected static final String GREENLEND = "9qj3xzud";
    protected static final String RESULT = "b6q6w49y";
    protected static final String TWIST = "7syhrhck";
    protected  static final String BALKAR = "6y94l81n";
    protected static  final String BASE_URL = "https://app.stage.greenlend.co.il/customer/wizard?channel=" + FIRST;

    @BeforeAll
    public static void setUp() {
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
        driver.get(BASE_URL);

        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        jsUtil = new JavaScriptUtility(driver);
        homePage = new Generic_HomePage(driver);
        js = (JavascriptExecutor) driver;
    }

//    @AfterEach
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}