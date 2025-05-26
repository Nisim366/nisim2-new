package com.example;

import demo.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product_label")));
            String pageTitle = titleElement.getText();
            return pageTitle.equals("Products");
        } catch (Exception e) {
            System.out.println("Title not found: " + e.getMessage());
            return false;
        }
    }
}
