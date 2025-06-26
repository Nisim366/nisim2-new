package Generic_product.Pages.Third_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Third_screen extends Generic_BasePage {

    private final By idNumberInput = By.cssSelector("[data-testid='applicant.identifier-input']");
    private final By issueDateInput = By.cssSelector("[data-testid='applicant.identifierIssueDate-datePicker']");
    private final By issueDateCalendarIcon = By.cssSelector("[data-testid='applicant.identifierIssueDate-datePicker'] + div button");
    private final By birthDateInput = By.xpath("//label[text()='תאריך לידה']/following-sibling::div//input");
    private final By birthDateCalendarIcon = By.cssSelector("[data-testid='applicant.birthDate-datePicker'] + div button");
    private final By genericRequiredFieldErrorMessages = By.xpath("//p[contains(text(),'שדה זה הוא שדה חובה') and starts-with(@aria-describedby, 'applicant.')]");
    private final By idNumberNineDigitsError = By.xpath("//p[@id=':r9:-helper-text' and contains(text(),'יש להזין 9 ספרות') and @aria-describedby='applicant.identifier-label']");
    private final By idNumberInvalidIdError = By.xpath("//p[contains(text(),'תעודת זהות לא תקינה') and @aria-describedby='applicant.identifier-label']");
    private final By datePickerMonthYearHeader = By.cssSelector(".MuiPickersCalendarHeader-label");
    private final By datePickerYearSelectionButton = By.cssSelector("button.MuiPickersCalendarHeader-switchViewButton");
    private final By datePickerPrevMonthButton = By.cssSelector("button[aria-label='חודש קודם']");
    private final By datePickerNextMonthButton = By.cssSelector("button[aria-label='חודש הבא']");
    private final By datePickerDay = By.cssSelector(".MuiPickersDay-root:not(.Mui-disabled)");
    private final By datePickerYearInYearView = By.cssSelector(".MuiYearCalendar-root button.MuiPickersYear-yearButton");
    private final By datePickerView = By.cssSelector(".MuiDateCalendar-root");
    private final By datePickerMonthButtons = By.cssSelector("button.MuiPickersMonth-monthButton");
    private final By continueButton = By.xpath("//*[@data-testid='continue-button']");
    private final By SpecificRequiredErrorGeneric = By.xpath("//p[contains(text(),'שדה זה הוא שדה חובה')]");
    private final By genderSelect = By.cssSelector("div[role='combobox'][aria-label='מגדר']");
    private final By genderOptionMale = By.xpath("//li[normalize-space()='זכר']");
    private final By genderOptionFemale = By.xpath("//li[normalize-space()='נקבה']");

    public Third_screen(WebDriver driver) {
        super(driver);
        // משנה את ההמתנה הכללית ל-10 שניות במקום 30
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // --- מתודות קיימות ---

    public boolean isOnThirdScreen() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInput));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
    public void clickContinueButton() {
        click(continueButton);
    }

    // בחירת מגדר מתוך הרשימה - "זכר" או "נקבה"
    private void selectGender(String genderText) {
        click(genderSelect); // פותח את הרשימה

        By optionLocator;
        switch (genderText) {
            case "זכר":
                optionLocator = genderOptionMale;
                break;
            case "נקבה":
                optionLocator = genderOptionFemale;
                break;
            default:
                throw new IllegalArgumentException("Unsupported gender option: " + genderText);
        }

        click(optionLocator); // בחירת הערך מהרשימה
    }

    // מחזיר את הטקסט הנבחר מתוך שדה המגדר (לדוגמה "זכר" או "נקבה")
    public String getSelectedGender() {
        return find(genderSelect).getText();
    }

    // בודק אם מוצגת הודעת שגיאה על שדה המגדר (שדה חובה שלא מולא)
    public boolean isGenderErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions
                    .visibilityOfElementLocated(SpecificRequiredErrorGeneric)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
    public void selectMaleGender() {
        selectGender("זכר");
    }

    public void selectFemaleGender() {
        selectGender("נקבה");
    }



    public void enterIssueDate(String date) {
        click(issueDateInput);
        clear(issueDateInput);
        set(issueDateInput, date);
    }

    public String getEnteredIssueDate() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(issueDateInput))
                .getAttribute("value");
    }

    public boolean isIssueDateErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(),'שדה זה הוא שדה חובה') and starts-with(@aria-describedby,'applicant.identifierIssueDate')]")
            )).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
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

    public void clickIdNumberInput() {
        click(idNumberInput);
    }

    public WebElement getIdNumberInputField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInput));
    }

    public void enterIdNumber(String idNumber) {
        clear(idNumberInput);
        set(idNumberInput, idNumber);
    }

    public String getEnteredIdNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInput)).getAttribute("value");
    }

    public boolean isIdNumberValueDisplayed(String expectedIdNumber) {
        try {
            WebElement idField = getIdNumberInputField();
            String actualValue = idField.getAttribute("value");
            return actualValue.equals(expectedIdNumber);
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public String getIdNumberRequiredErrorText() {
        try {
            click(idNumberInput);
            getIdNumberInputField().sendKeys("\t");
            wait.until(ExpectedConditions.visibilityOfElementLocated(genericRequiredFieldErrorMessages));
            return driver.findElement(SpecificRequiredErrorGeneric).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String getIdNumberNineDigitsErrorText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(idNumberNineDigitsError));
            return driver.findElement(idNumberNineDigitsError).getText();
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Expected 'יש להזין 9 ספרות' error message was not found on the page.", e);
        }
    }

    public void waitForIdNumberErrorToBe(String expectedText) {
        wait.until(ExpectedConditions.textToBe(idNumberNineDigitsError, expectedText));
    }

    public String getIdNumberInvalidIdErrorText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInvalidIdError));
            return driver.findElement(idNumberInvalidIdError).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public boolean isIdNumberErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[starts-with(@aria-describedby,'applicant.identifier-label')]"))).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    // --- מתודות תאריך לידה ---

    public void enterBirthDate(String date) {
        click(birthDateInput);
        clear(birthDateInput);
        set(birthDateInput, date);
    }

    public String getEnteredBirthDate() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(birthDateInput))
                .getAttribute("value");
    }

    public boolean isBirthDateErrorDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(),'שדה זה הוא שדה חובה') and starts-with(@aria-describedby,'applicant.birthDate')]")
            )).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String cleanDateString(String rawDate) {
        if (rawDate == null) return null;
        return rawDate.replaceAll("[\\p{Cf}]", "").trim();
    }
}
