package Generic_product.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Generic_BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Generic_BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected WebElement find(By elementLocator) {
        return driver.findElement(elementLocator);
    }

    protected void set(By elementLocator, String textToSet) {
        find(elementLocator).clear();
        find(elementLocator).sendKeys(textToSet);
    }

    protected void click(By elementLocator) {
        find(elementLocator).click();
    }

    protected String getText(By elementLocator) {
        return find(elementLocator).getText();
    }
}
