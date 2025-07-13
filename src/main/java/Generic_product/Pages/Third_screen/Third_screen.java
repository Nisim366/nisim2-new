package Generic_product.Pages.Third_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.IsraeliIdGenerator;

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
    }

    // --- מתודות קיימות ---

    public boolean isOnThirdScreen() {
        try {
            WebElement element = longwait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInput));
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
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            return shortWait.until(ExpectedConditions
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
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(issueDateInput))
                .getAttribute("value");
    }

    public boolean isIssueDateErrorDisplayed() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(
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
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInput));
    }

    public void enterIdNumber(String idNumber) {
        clear(idNumberInput);
        set(idNumberInput, idNumber);
    }

    public String getEnteredIdNumber() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(idNumberInput)).getAttribute("value");
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
            customWait(2).until(ExpectedConditions.visibilityOfElementLocated(genericRequiredFieldErrorMessages));
            return driver.findElement(SpecificRequiredErrorGeneric).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String getIdNumberNineDigitsErrorText() {

        try {
            customWait(2).until(ExpectedConditions.visibilityOfElementLocated(idNumberNineDigitsError));
            return driver.findElement(idNumberNineDigitsError).getText();
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Expected 'יש להזין 9 ספרות' error message was not found on the page.", e);
        }
    }

    public void waitForIdNumberErrorToBe(String expectedText) {
        customWait(2).until(ExpectedConditions.textToBe(idNumberNineDigitsError, expectedText));
    }

    public String getIdNumberInvalidIdErrorText() {
        try {
            customWait(2).until(ExpectedConditions.visibilityOfElementLocated(idNumberInvalidIdError));
            return driver.findElement(idNumberInvalidIdError).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public boolean isIdNumberErrorDisplayed() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        try {
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(
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
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(birthDateInput))
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


    public Fourth_screen completeThirdScreenHappyFlow() {
        // שליפת ערכים מה־user1.properties
        UserData user = new UserData("user1");

        String validId = IsraeliIdGenerator.generateRandomValidIsraeliId(); // ת"ז אקראית תקינה
        String validIssueDate = user.idCard.issueDate; // מה־user1
        String validBirthDate = user.idCard.birthDate; // מה־user1
        String gender = user.idCard.gender;            // מה־env

        try {
            enterIdNumber(validId);
            String actualIdValue = getEnteredIdNumber();
            if (!actualIdValue.equals(validId)) {
                throw new IllegalStateException("שגיאה: תעודת הזהות לא הוזנה נכון.");
            }
            if (isIdNumberErrorDisplayed()) {
                throw new IllegalStateException("שגיאה: הודעת שגיאה מוצגת עבור תעודת זהות תקינה.");
            }

            enterIssueDate(validIssueDate);
            String actualIssueDateRaw = getEnteredIssueDate();
            String actualIssueDate = cleanDateString(actualIssueDateRaw);
            if (!actualIssueDate.equals(validIssueDate)) {
                throw new IllegalStateException("שגיאה: תאריך הנפקה לא הוזן נכון.");
            }
            if (isIssueDateErrorDisplayed()) {
                throw new IllegalStateException("שגיאה: הודעת שגיאה מוצגת עבור תאריך הנפקה תקין.");
            }

            enterBirthDate(validBirthDate);
            String actualBirthDateRaw = getEnteredBirthDate();
            String actualBirthDate = cleanDateString(actualBirthDateRaw);
            if (!actualBirthDate.equals(validBirthDate)) {
                throw new IllegalStateException("שגיאה: תאריך לידה לא הוזן נכון.");
            }
            if (isBirthDateErrorDisplayed()) {
                throw new IllegalStateException("שגיאה: הודעת שגיאה מוצגת עבור תאריך לידה תקין.");
            }

            if ("זכר".equals(gender)) {
                selectMaleGender();
            } else {
                selectFemaleGender();
            }
            String selectedGender = getSelectedGender();
            if (!selectedGender.equals(gender)) {
                throw new IllegalStateException("שגיאה: בחירת המגדר לא תקינה.");
            }
            if (isGenderErrorDisplayed()) {
                throw new IllegalStateException("שגיאה: הודעת שגיאה מוצגת עבור מגדר תקין.");
            }

            clickContinueButton();
            System.out.println("מסך 3 - עכשיו כמה פרטים מזהים");

            // המתנה למסך הרביעי
            Fourth_screen fourthScreen = new Fourth_screen(driver);

            return fourthScreen;

        } catch (Exception e) {
            throw new RuntimeException("❌ כשל כללי ב-Happy Flow של המסך השלישי.", e);
        }
    }


    public Fourth_screen goToFourthScreen() {
        return completeThirdScreenHappyFlow(); // בלי פרמטרים
    }




}
