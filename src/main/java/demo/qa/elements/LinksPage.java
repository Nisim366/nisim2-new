package demo.qa.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class LinksPage extends ElementsPage {

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private By badRequestLink = By.id("bad-request");
    private By createdLink = By.id("created");
    private By ResponseLink = By.id("linkResponse"); // אני מניח שזה נכון

    public LinksPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickBadRequestLink() {
        jsUtil.scrollToElementJS(badRequestLink);
        click(badRequestLink);
    }

    public void clickCreatedLink() {
        jsUtil.scrollToElementJS(createdLink);
        click(createdLink);
    }

    public String getErrorResponse() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ResponseLink));
        return find(ResponseLink).getText();
    }

    public String getCreatedResponse() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ResponseLink));
        return find(ResponseLink).getText();
    }
}
