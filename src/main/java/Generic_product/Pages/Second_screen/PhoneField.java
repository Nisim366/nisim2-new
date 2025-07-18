package Generic_product.Pages.Second_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class PhoneField extends Generic_BasePage {

    private final By phoneInput = By.id("applicant.mobile.phone");
    private final By phoneTooltipButton = By.cssSelector("[data-testid='applicant.mobile.phone-tooltipToggle']");
    private final By phoneErrorMessage = By.xpath("//p[contains(text(),'יש להקליד מספר נייד תקין')]");
    private final By tooltipText = By.xpath("//span[contains(text(),'לנייד זה יישלח כעת קוד אימות')]");

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private Actions actions;

    private final String fixedPrefix = "+972";

    public PhoneField(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.jsUtil = new JavaScriptUtility(driver);
        this.actions = new Actions(driver);
    }

    private WebElement getPhoneInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
    }

    public String getNormalizedPhoneValue() {
        String rawValue = getPhoneInputValue();
        if (rawValue == null) return "";

        return rawValue
                .replaceAll("\\s+", "")    // הסרת רווחים
                .replace("+972", "0");     // החלפת קידומת בינלאומית לקידומת מקומית
    }

    public String normalizePhoneForComparison(String phone) {
        if (phone == null) return "";

        phone = phone.replaceAll("\\s+", "");

        if (phone.startsWith("+972")) {
            String numberPart = phone.substring(4);
            if (!numberPart.startsWith("0")) {
                numberPart = "0" + numberPart;
            }
            return numberPart;
        }

        return phone;
    }

    private WebElement getPhoneErrorElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneErrorMessage));
    }

    private WebElement getTooltipButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(phoneTooltipButton));
    }

    private WebElement getTooltipTextElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tooltipText));
    }


    public String getPhoneInputValue() {
        try {
            return getPhoneInput().getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isPhoneInputVisible() {
        try {
            return getPhoneInput().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhoneInputEnabled() {
        try {
            return getPhoneInput().isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    private void ensurePrefixPresent() {
        WebElement input = getPhoneInput();
        String val = input.getAttribute("value");
        if (val == null || !val.startsWith(fixedPrefix)) {
            jsUtil.clearUsingCtrlADelete(input);
            input.sendKeys(fixedPrefix);
        }
    }

    public void setPhoneInput(String phoneDigitsOnly) {
        if (phoneDigitsOnly == null) phoneDigitsOnly = "";

        // מסירים כל תו שהוא לא ספרה
        phoneDigitsOnly = phoneDigitsOnly.replaceAll("\\D", "");

        // אם המספר מתחיל ב-0, מסירים אותו כי נתייחס לקידומת +972
        if (phoneDigitsOnly.startsWith("0")) {
            phoneDigitsOnly = phoneDigitsOnly.substring(1);
        }

        // חותכים ל־9 ספרות (מספר טלפון ישראלי ללא הקידומת)
        if (phoneDigitsOnly.length() > 9) {
            phoneDigitsOnly = phoneDigitsOnly.substring(0, 9);
        }

        ensurePrefixPresent();
        WebElement input = getPhoneInput();

        // ניקוי התוכן אחרי הקידומת
        String currentValue = input.getAttribute("value");
        if (currentValue.length() > fixedPrefix.length()) {
            input.sendKeys(Keys.END);
            for (int i = 0; i < currentValue.length() - fixedPrefix.length(); i++) {
                input.sendKeys(Keys.BACK_SPACE);
            }
        }

        // מזין את המספר ללא אפס תחילתי, רק הספרות הבאות
        input.sendKeys(phoneDigitsOnly);
    }


    public void deleteCharsFromPhoneInput(int charsToDelete) {
        WebElement input = getPhoneInput();
        input.sendKeys(Keys.END);

        String val = input.getAttribute("value");
        int deletableChars = Math.max(0, val.length() - fixedPrefix.length());

        int toDelete = Math.min(charsToDelete, deletableChars);
        for (int i = 0; i < toDelete; i++) {
            input.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void clearPhoneInputKeepingPrefix() {
        WebElement input = getPhoneInput();
        ensurePrefixPresent();
        input.sendKeys(Keys.END);

        String val = input.getAttribute("value");
        int lengthToClear = Math.max(0, val.length() - fixedPrefix.length());

        for (int i = 0; i < lengthToClear; i++) {
            input.sendKeys(Keys.BACK_SPACE);
        }
    }

    public boolean isPhoneNumberValid() {
        String val = getPhoneInputValue();
        if (val == null || !val.startsWith(fixedPrefix)) return false;

        String numberPart = val.substring(fixedPrefix.length()).replaceAll("\\D", "");
        return numberPart.length() >= 9 && numberPart.length() <= 10;
    }

    public boolean isPhoneErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneErrorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPhoneErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneErrorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isPhoneErrorMessageCorrect() {
        String expected = "יש להקליד מספר נייד תקין";
        return expected.equals(getPhoneErrorMessage());
    }

    public boolean waitForPhoneErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(phoneErrorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhoneTooltipButtonVisible() {
        try {
            return getTooltipButton().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPhoneTooltipButton() {
        getTooltipButton().click();
    }

    public boolean isTooltipTextVisible() {
        try {
            return getTooltipTextElement().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTooltipText() {
        try {
            return getTooltipTextElement().getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isTooltipClosedOnPageLoad() {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(tooltipText));
        } catch (Exception e) {
            return true;
        }
    }


    public boolean isOnSecondPage() {
        return isPhoneInputVisible();
    }

}
