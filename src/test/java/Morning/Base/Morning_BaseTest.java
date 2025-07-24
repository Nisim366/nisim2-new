package Morning.Base;

import Generic_product.Generic_HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class Morning_BaseTest {

    protected WebDriver driver;
    protected Generic_HomePage homePage;
    protected JavascriptExecutor js;
    protected JavaScriptUtility jsUtil;
    protected WebDriverWait wait;

    protected final String URL = "https://morning.stage.greenlend.co.il/customer/wizard?code=rMhLeN6Dql9MsYMb1VFIT0FrfbPUiJIsSnZn9Mh3CLmDiZsMvNXp1%2FQ8QpqvrgeEtd2zfhF5ZS9W2sk3DnbVLg%2B5r81aJ2JUtNtOmcYvvH8%3D";


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URL);

        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        jsUtil = new JavaScriptUtility(driver);
        js = (JavascriptExecutor) driver;

    }


}
