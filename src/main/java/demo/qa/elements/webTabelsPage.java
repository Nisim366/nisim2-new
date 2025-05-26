package demo.qa.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class webTabelsPage extends ElementsPage {
    private By editButton = By.xpath("//div[text()='alden@example.com']//following::span[@title='Edit']");
    private By ageField = By.xpath("//input[@id='age']");
    private By salaryField = By.xpath("//input[@id='salary']");
    private By submitButton = By.xpath("//button[@id='submit']");
    private WebDriverWait wait;


    public webTabelsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickEdit(String email) {
        try {
            By findEditButton = By.xpath("//div[contains(text(),'" + email + "')]//following::span[@title='Edit']");
            wait.until(ExpectedConditions.elementToBeClickable(findEditButton)).click();
        } catch (Exception e) {
            System.err.println("Failed to click edit button for email: " + email + ". Error: " + e.getMessage());
        }
    }

    public void setAge(String age) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ageField)).clear();
            wait.until(ExpectedConditions.visibilityOfElementLocated(ageField)).sendKeys(age);
        } catch (Exception e) {
            System.err.println("Failed to set age. Error: " + e.getMessage());
        }
    }
    public void setSalary (String salary){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(salaryField)).clear();
            wait.until(ExpectedConditions.visibilityOfElementLocated(salaryField)).sendKeys(salary);
        }catch (Exception e) {
            System.err.println("Failed to set salary. Error: " + e.getMessage());
        }
    }

    public void clickSubmitButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        } catch (Exception e) {
            System.err.println("Failed to click submit button. Error: " + e.getMessage());
        }
    }
    public String getTableAge(String email) {
        By rowLocator = By.xpath("//div[@class='rt-tr-group'][.//div[text()='" + email + "']]");
        WebElement row = find(rowLocator);
        WebElement ageColumn = row.findElement(By.xpath(".//div[@class='rt-td'][position()=3]"));
        return ageColumn.getText();
    }
    public String getTableSalary(String email) {
        By rowLocator = By.xpath("//div[@class='rt-tr-group'][.//div[text()='" + email + "']]");
        WebElement row = find(rowLocator);
        WebElement ageColumn = row.findElement(By.xpath(".//div[@class='rt-td'][position()=5]"));
        return ageColumn.getText();

    }

}