package demo.qa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
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
