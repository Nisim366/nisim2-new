package greenlend.logginPage.pages;

import greenlend.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.JavaScriptUtility;

public class LoginPage extends BasePage {

    private final JavaScriptUtility jsUtil;

    private final By emailField = By.id("user-email");
    private final By nextButton = By.xpath("//span[text()='Next']");
    private final By continueWithAtlassianButton = By.xpath("//span[text()='Continue with Atlassian account']");
    private final By continueButtonSecond = By.xpath("//span[text()='Continue']");
    private final By passwordField = By.id("password");
    private final By loginButton = By.xpath("//span[text()='Log in']");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.jsUtil = new JavaScriptUtility(driver);
    }

    public void fillEmailField(String email) {
        WebElement input = find(emailField);
        String currentValue = input.getAttribute("value");

        if (currentValue == null || currentValue.isEmpty() || !currentValue.equals(email)) {
            input.clear();
            input.sendKeys(email);
        }
    }

    public void clickNextButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        jsUtil.scrollToElementJS(nextButton);
        button.click();
    }

    public void doubleClickNextWithDelay() {
        clickNextButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickNextButton();
    }

    public void clickContinueWithAtlassian() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueWithAtlassianButton));
        jsUtil.scrollToElementJS(continueWithAtlassianButton);
        button.click();
    }

    public void clickContinueSecond() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButtonSecond));
            jsUtil.scrollToElementJS(continueButtonSecond);
            button.click();
        } catch (TimeoutException e) {
            System.out.println("Continue button not clickable.");
        }
    }

    public void fillPasswordFieldWhenFocused(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        WebElement active = driver.switchTo().activeElement();
        if (!field.equals(active)) {
            field.click();
        }
        field.sendKeys(password);
    }

    // הוספת המתודה שהיית חסרה:
    public void clickLoginButton() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            jsUtil.scrollToElementJS(loginButton);
            try {
                button.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }
        } catch (TimeoutException e) {
            System.out.println("Login button not clickable or not found.");

        }
    }
}
