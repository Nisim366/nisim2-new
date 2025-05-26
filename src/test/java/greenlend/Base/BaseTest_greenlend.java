package greenlend.Base;

import demo.qa.HomePage;
import demo.qa.base.BasePage;
import greenlend.GHomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest_greenlend {

    protected WebDriver driver;
    protected BasePage basePage;
    protected GHomePage homePage_greenlend;
    private final String GREENLEND_URL = "https://ezbob.atlassian.net/servicedesk/customer/portals";
    protected JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(GREENLEND_URL);
        basePage = new BasePage(driver);
        homePage_greenlend = new GHomePage(driver);
        js = (JavascriptExecutor) driver;
    }

//    @AfterEach
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
