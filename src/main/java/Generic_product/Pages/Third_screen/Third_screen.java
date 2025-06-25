package Generic_product.Pages.Third_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import utilities.IsraeliIdGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Third_screen extends Generic_BasePage {

    // Locators for existing fields
    private final By idNumberInput = By.cssSelector("[data-testid='applicant.identifier-input']");
    private final By continueButton = By.xpath("//*[@data-testid='continue-button']");

    // NEW LOCATORS for Issue Date
    private final By issueDateInput = By.cssSelector("[data-testid='applicant.identifierIssueDate-datePicker']");
    private final By issueDateCalendarIcon = By.cssSelector("[data-testid='applicant.identifierIssueDate-datePicker'] + div button");

    // NEW LOCATORS for Birth Date
    private final By birthDateInput = By.xpath("//label[text()='תאריך לידה']/following-sibling::div//input");
    private final By birthDateCalendarIcon = By.cssSelector("[data-testid='applicant.birthDate-datePicker'] + div button");

    // Generic required field error messages for all applicant fields
    private final By genericRequiredFieldErrorMessages = By.xpath("//p[contains(text(),'שדה זה הוא שדה חובה') and starts-with(@aria-describedby, 'applicant.')]");

    // Locators for DatePicker components
    private final By datePickerMonthYearHeader = By.cssSelector(".MuiPickersCalendarHeader-label");
    private final By datePickerYearSelectionButton = By.cssSelector("button.MuiPickersCalendarHeader-switchViewButton");
    private final By datePickerPrevMonthButton = By.cssSelector("button[aria-label='חודש קודם']");
    private final By datePickerNextMonthButton = By.cssSelector("button[aria-label='חודש הבא']");
    private final By datePickerDay = By.cssSelector(".MuiPickersDay-root:not(.Mui-disabled)");
    private final By datePickerYearInYearView = By.cssSelector(".MuiYearCalendar-root button.MuiPickersYear-yearButton");
    private final By datePickerView = By.cssSelector(".MuiDateCalendar-root");

    private final By datePickerMonthButtons = By.cssSelector("button.MuiPickersMonth-monthButton");


    public Third_screen(WebDriver driver) {
        super(driver);
    }
    public enum DatePickerView {
        YEAR,
        MONTH,
        DAY
    }

    public DatePickerView getCurrentDatePickerView() {
        // תנסה לבדוק מה מוצג כרגע ע"י מציאת אלמנטים ייחודיים לכל תצוגה
        if (driver.findElements(By.cssSelector(".MuiYearCalendar-root")).size() > 0) {
            return DatePickerView.YEAR;
        } else if (driver.findElements(By.cssSelector(".MuiMonthCalendar-root")).size() > 0) {
            return DatePickerView.MONTH;
        } else {
            return DatePickerView.DAY;
        }
    }

    public void switchToYearViewIfNeeded() {
        DatePickerView currentView = getCurrentDatePickerView();
        if (currentView != DatePickerView.YEAR) {
            // לחץ על הכותרת פעם אחת כדי לעבור לתצוגת שנה
            click(datePickerMonthYearHeader);
            wait.until(driver -> getCurrentDatePickerView() == DatePickerView.YEAR);
        }
    }

    public void switchToMonthViewIfNeeded() {
        DatePickerView currentView = getCurrentDatePickerView();
        if (currentView != DatePickerView.MONTH) {
            // לחץ על הכותרת פעם אחת כדי לעבור לתצוגת חודש
            click(datePickerMonthYearHeader);
            wait.until(driver -> getCurrentDatePickerView() == DatePickerView.MONTH);
        }
    }


    public boolean isOnThirdScreen() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInput));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getIdNumberInputField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInput));
    }

    public void enterIdNumber(String idNumber) {
        set(idNumberInput, idNumber);
    }

    public String getIdNumberErrorText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(genericRequiredFieldErrorMessages));
            return driver.findElement(By.xpath("//p[@aria-describedby='applicant.identifier-label' and contains(text(),'שדה זה הוא שדה חובה')]")).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public WebElement getIssueDateInputField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(issueDateInput));
    }

    public void enterIssueDate(String dateToEnter) {
        set(issueDateInput, dateToEnter);
    }

    public String getIssueDateErrorText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(genericRequiredFieldErrorMessages));
            return driver.findElement(By.xpath("//p[@aria-describedby='applicant.identifierIssueDate-label' and contains(text(),'שדה זה הוא שדה חובה')]")).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public void clickIssueDateCalendarIcon() {
        click(issueDateCalendarIcon);
        wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerView));
    }

    public WebElement getBirthDateInputField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(birthDateInput));
    }

    public void enterBirthDate(String dateToEnter) {
        set(birthDateInput, dateToEnter);
    }

    public String getBirthDateErrorText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(genericRequiredFieldErrorMessages));
            return driver.findElement(By.xpath("//p[@aria-describedby='applicant.birthDate-label' and contains(text(),'שדה זה הוא שדה חובה')]")).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public void clickBirthDateCalendarIcon() {
        click(birthDateCalendarIcon);
        wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerView));
    }

    public void selectDayInDatePicker(String day) {
        By dayLocator = By.xpath(String.format("//button[text()='%s' and not(contains(@class, 'Mui-disabled'))]", day));
        click(dayLocator);
    }

    public void selectYearInDatePicker(String year) {
        By yearLocator = By.xpath(String.format("//button[text()='%s']", year));
        wait.until(ExpectedConditions.elementToBeClickable(yearLocator)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(datePickerYearInYearView));
    }

    public void navigateMonthsInDatePicker(int monthsToGo) {
        if (monthsToGo > 0) {
            for (int i = 0; i < monthsToGo; i++) {
                click(datePickerNextMonthButton);
            }
        } else if (monthsToGo < 0) {
            for (int i = 0; i < -monthsToGo; i++) {
                click(datePickerPrevMonthButton);
            }
        }
    }
    private String etHebrewMonthLabel(int monthNumber) {
        return switch (monthNumber) {
            case 1  -> "ינואר";
            case 2  -> "פברואר";
            case 3  -> "מרץ";
            case 4  -> "אפריל";
            case 5  -> "מאי";
            case 6  -> "יוני";
            case 7  -> "יולי";
            case 8  -> "אוגוסט";
            case 9  -> "ספטמבר";
            case 10 -> "אוקטובר";
            case 11 -> "נובמבר";
            case 12 -> "דצמבר";
            default -> throw new IllegalArgumentException("Invalid month number: " + monthNumber);
        };
    }

    public void selectDateFromDatePicker(String fullDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate targetDate = LocalDate.parse(fullDate, formatter);

        // ודא שאתה בתצוגת יום תחילה (כשפותחים את לוח השנה)
        wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerView));

        // עבור לתצוגת שנה
        switchToYearViewIfNeeded();

        selectYearInDatePicker(String.valueOf(targetDate.getYear()));

        // עבור לתצוגת חודש
        switchToMonthViewIfNeeded();

        selectMonthInDatePicker(etHebrewMonthLabel(targetDate.getMonthValue()));

        // עכשיו בחירת יום
        selectDayInDatePicker(String.valueOf(targetDate.getDayOfMonth()));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(datePickerView));
    }

    private void waitUntilInputValueIsSet(By locator, String expectedValue) {
        wait.until(driver -> {
            String actualValue = driver.findElement(locator).getAttribute("value");
            return actualValue != null && actualValue.equals(expectedValue);
        });
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        // המתן שכל השדות יכילו ערכים תקינים לפני לחיצה
        waitUntilInputValueIsSet(idNumberInput, driver.findElement(idNumberInput).getAttribute("value"));
        waitUntilInputValueIsSet(issueDateInput, driver.findElement(issueDateInput).getAttribute("value"));
        waitUntilInputValueIsSet(birthDateInput, driver.findElement(birthDateInput).getAttribute("value"));

        click(continueButton);
    }

    public boolean isContinueButtonEnabled() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(continueButton)).isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isIdNumberFieldDisplayed() {
        try {
            return getIdNumberInputField().isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean isIssueDateFieldDisplayed() {
        try {
            return getIssueDateInputField().isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean isBirthDateFieldDisplayed() {
        try {
            return getBirthDateInputField().isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean isIssueDateCalendarIconDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(issueDateCalendarIcon)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean isBirthDateCalendarIconDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(birthDateCalendarIcon)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
    public By getMonthButtonByLabel(String hebrewMonth) {
        return By.cssSelector("button[aria-label='" + hebrewMonth + "']");
    }

    public void selectMonthInDatePicker(String hebrewMonthLabel) {
        try {
            By monthLocator = getMonthButtonByLabel(hebrewMonthLabel);
            wait.until(ExpectedConditions.elementToBeClickable(monthLocator)).click();
        } catch (Exception e) {
            System.err.println("Failed to select month in DatePicker: " + e.getMessage());
            throw e;
        }
    }
}
