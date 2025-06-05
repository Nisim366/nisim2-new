package Generic_product.Pages.Third_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Third_screen extends Generic_BasePage {

    private final By header = By.id("page-header");
    private WebDriverWait wait;

    public Third_screen(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // אתחול כאן!

    }

    public boolean isOnThirdPage() {
        try {
            String headerText = wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
            System.out.println("Header text: '" + headerText + "'");
            return headerText.contains("מה קוד האימות שקיבלת?");
        } catch (Exception e) {
            return false;
        }
    }


    public boolean waitForThirdPageToLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(header));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
