package demo.qa.Widgets;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;



public class DatePickerMneuPage extends WidgetsPage {

    private JavaScriptUtility jsUtil;
    private By selectDateField = By.id("datePickerMonthYearInput");
    private By mounthDropDown = By.className("react-datepicker__month-select");
    private By yearDropDown = By.cssSelector(".react-datepicker__year-select");
    private WebDriverWait wait;


    public DatePickerMneuPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private void removeFixedBanner() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('fixedban').remove();");
    }

    private By dayValue(String day) {
        return By.xpath("//div[contains(@class,'react-datepicker__day') and normalize-space(text())='"+day+"']");
    }

    public void clickDay(String day) {
        try {
            click(dayValue(day));
        } catch (Exception e) {
            removeFixedBanner();
            System.out.println("trying to remove Banner and click again.");
            click(dayValue(day));
        }
    }

    public boolean isDayInMonth(String day) {
        try {
            WebElement dayElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'react-datepicker__day') " +
                            "and not(contains(@class,'react-datepicker__day--outside-month')) " +
                            "and normalize-space(text())='" + day + "']")));

            return dayElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void clickSelectDate() {
        try {
            click(selectDateField);
        }catch (Exception e) {
            System.out.println("Error occurred while clicking the date field: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getDate() {
        jsUtil.scrollToElementJS(selectDateField);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectDateField));
        return find(selectDateField).getAttribute("value");
    }


    public void selectMonth(String month) {
        WebElement monthDropdown = driver.findElement(mounthDropDown);
        Select select = new Select(monthDropdown);
        select.selectByVisibleText(month);
        wait.until(ExpectedConditions.textToBePresentInElement(monthDropdown, month));

    }

    public void selectYear(String year) {
        WebElement yearDropdown = driver.findElement(yearDropDown);
        Select select = new Select(yearDropdown);
        select.selectByVisibleText(year);
    }


}

