package com.example;

import demo.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private By userNameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMassage = By.xpath("//h3[@data-test='error']");
    private String array [] = {"standard_user","locked_out_user","problem_user","performance_glitch_user"};

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUserName ( String userName){
        set(userNameField,userName);
    }
    public void setPassword(String password){
        set(passwordField,password);
    }
    public ProductPage clickButton (){
        click(loginButton);
        return new ProductPage(getDriver());
    }
    public ProductPage logIntoApplicasion(String userName, String Password){
        setUserName(userName);
        setPassword(Password);
        return clickButton();
    }

    public String getErrorMessage (){
        return find(errorMassage).getText();
    }


    public void forLoop() {
        for (int i = 0; i < array.length; i++) {
            driver.get("https://www.saucedemo.com/v1/");
            setUserName(array[i]);
            setPassword("secret_sauce");
            click(loginButton);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_list']"))); // לוודא שהדף נטען
            } catch (Exception  e) {
                System.out.println("Page did not load properly for user: " + array[i]);
                System.out.println("Error message: " + getErrorMessage());
                continue;
            }
            if (driver.getPageSource().contains("Products")) {
                System.out.println("Login successful for user: " + array[i]);
                try {
                    driver.findElement(By.xpath("//button[text()='Open Menu']")).click();
                    driver.findElement(By.id("logout_sidebar_link")).click();
                } catch (Exception e) {
                    System.out.println("Error during logout for user: " + array[i]);
                }
            } else {
                System.out.println("Login failed for user: " + array[i]);
                System.out.println("Error message: " + getErrorMessage());
            }
        }
    }


}