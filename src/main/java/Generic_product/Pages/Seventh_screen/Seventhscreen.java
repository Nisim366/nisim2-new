package Generic_product.Pages.Seventh_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Seventhscreen extends Generic_BasePage {

    // מזהה עיקרי במסך השביעי לאימות הגעה
    private final By pageHeader = By.cssSelector("#page-header");
    private static final String EXPECTED_HEADER_TEXT = "סיימנו את השלב הראשון!";

    public Seventhscreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnSeventhScreen() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            String headerText = localWait
                    .until(ExpectedConditions.visibilityOfElementLocated(pageHeader))
                    .getText().trim();
            return headerText.contains(EXPECTED_HEADER_TEXT);
        } catch (Exception e) {
            return false;
        }
    }

}
