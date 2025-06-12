package Generic.Base;

import Generic_product.Generic_HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;
import java.time.Duration;

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
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(Generic_URL);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        jsUtil = new JavaScriptUtility(driver);
        homePage = new Generic_HomePage(driver);
        js = (JavascriptExecutor) driver;

    }

    @AfterEach
    public void globalTearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("❌ Error in globalTearDown:");
            e.printStackTrace();
        }
    }
}
