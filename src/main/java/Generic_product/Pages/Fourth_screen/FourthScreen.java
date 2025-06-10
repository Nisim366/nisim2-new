package Generic_product.Pages.Fourth_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FourthScreen extends Generic_BasePage {

    // Locators for existing fields
    private final By idNumberInput = By.id(":rf:");
    private final By idNumberErrorText = By.id(":rf:-helper-text");
    private final By continueButton = By.xpath("//*[@data-testid='continue-button']");

    // NEW LOCATORS for Issue Date
    private final By issueDateInput = By.id(":rg:");
    private final By issueDateCalendarIcon = By.cssSelector("[data-testid='applicant.identifierIssueDate-datePicker'] + div button");
    private final By issueDateErrorText = By.id(":rg:-helper-text");

    // NEW LOCATORS for Birth Date
    private final By birthDateInput = By.xpath("//label[text()='תאריך לידה']/following-sibling::div//input");
    private final By birthDateCalendarIcon = By.cssSelector("[data-testid='applicant.birthDate-datePicker'] + div button");
    private final By birthDateErrorText = By.id(":ri:-helper-text");

    // Locators for common DatePicker elements
    private final By datePickerMonthYearHeader = By.cssSelector(".MuiPickersToolbar-root h4");
    private final By datePickerPrevMonthButton = By.cssSelector(".MuiCalendarPicker-root button[title='Previous month']");
    private final By datePickerNextMonthButton = By.cssSelector(".MuiCalendarPicker-root button[title='Next month']");
    private final By datePickerYearSelectionButton = By.cssSelector(".MuiPickersCalendarHeader-switchHeader button[aria-label^='Select year']");
    private final By datePickerDay = By.cssSelector(".MuiDayPicker-root button:not(.Mui-disabled)");
    private final By datePickerYearInYearView = By.cssSelector(".MuiYearPicker-root button");
    private final By datePickerView = By.cssSelector(".MuiDialog-container");

    public FourthScreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnFourthScreen() {
        try {
            WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-header")));
            return headerElement.getText().contains("עכשיו כמה פרטים מזהים");
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(idNumberErrorText));
            return getText(idNumberErrorText);
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(issueDateErrorText));
            return getText(issueDateErrorText);
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(birthDateErrorText));
            return getText(birthDateErrorText);
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
        click(datePickerYearSelectionButton);
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
            for (int i = 0; i > monthsToGo; i--) {
                click(datePickerPrevMonthButton);
            }
        }
    }

    public void selectDateFromDatePicker(String fullDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate targetDate;
        try {
            targetDate = LocalDate.parse(fullDate, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected DD/MM/YYYY. Provided: " + fullDate, e);
        }

        click(datePickerYearSelectionButton);
        selectYearInDatePicker(String.valueOf(targetDate.getYear()));

        String currentMonthYearText = getText(datePickerMonthYearHeader);

        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMMître"); // Modified pattern to prevent potential issues
        LocalDate currentPickerDate;
        try {
            currentPickerDate = LocalDate.parse("01 " + currentMonthYearText, DateTimeFormatter.ofPattern("dd MMMMître"));
        } catch (DateTimeParseException e) {
            System.err.println("Could not parse DatePicker header with 'MMMMître': " + currentMonthYearText);
            currentPickerDate = LocalDate.now();
        }

        int monthsDifference = (targetDate.getYear() - currentPickerDate.getYear()) * 12 +
                (targetDate.getMonthValue() - currentPickerDate.getMonthValue());

        navigateMonthsInDatePicker(monthsDifference);

        selectDayInDatePicker(String.valueOf(targetDate.getDayOfMonth()));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(datePickerView));
    }

    public void clickContinueButton() {
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

    public void fillAllRequiredFieldsWithValidData() {
        enterIdNumber("123456789");

        LocalDate issueDate = LocalDate.now().minusYears(2);
        enterIssueDate(issueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        LocalDate birthDate = LocalDate.now().minusYears(25);
        enterBirthDate(birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}