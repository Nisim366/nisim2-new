package greenlend;

import greenlend.base.BasePage;
import greenlend.logginPage.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.JavaScriptUtility;

public class GHomePage extends BasePage {

    private final By emailField = By.id("user-email");

    private final JavaScriptUtility jsUtil;

    public GHomePage(WebDriver driver) {
        super(driver);
        this.jsUtil = new JavaScriptUtility(driver);
    }

    public LoginPage ToLogin() {
        jsUtil.scrollToElementJS(emailField);
        click(emailField);
        return new LoginPage(driver);
    }
}
