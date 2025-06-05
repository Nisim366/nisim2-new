package Generic_product.Pages.Second_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.JavaScriptUtility;


public class FirstLastName extends Generic_BasePage {

    private JavaScriptUtility js;

    private By firstNameInput = By.xpath("//input[@data-testid='applicant.fullName.firstName-input']");
    private By lastNameInput = By.xpath("//input[@data-testid='applicant.fullName.lastName-input']");

    private By firstNameErrorMessage = By.id(":r3:-helper-text");
    private By lastNameErrorMessage = By.id(":r4:-helper-text");

    public FirstLastName(WebDriver driver) {
        super(driver);
        js = new JavaScriptUtility(driver);
    }

    // --- First Name ---

    private WebElement getFirstNameInput() {
        return driver.findElement(firstNameInput);
    }

    public void setFirstName(String name) {
        WebElement input = getFirstNameInput();

        input.click();  // מביא פוקוס לשדה - חשוב לאתרים שמצפים לפוקוס
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // בוחר את כל הטקסט בשדה
        input.sendKeys(Keys.DELETE);  // מוחק את הבחירה (כל הטקסט)

        input.sendKeys(name);  // מזין את השם החדש
    }


    public String getFirstName() {
        return getFirstNameInput().getAttribute("value");
    }

    public boolean isFirstNameVisible() {
        try {
            return getFirstNameInput().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFirstNameRequired() {
        return getFirstNameInput().getAttribute("required") != null;
    }

    public void clearFirstNameUsingJS() {
        WebElement input = getFirstNameInput();
        js.clearUsingCtrlADelete(input);
    }

    public void deleteFirstNameChars(int charsToDelete) {
        WebElement input = getFirstNameInput();
        for (int i = 0; i < charsToDelete; i++) {
            input.sendKeys(Keys.BACK_SPACE);
        }
    }

    public boolean isFirstNameErrorDisplayed() {
        try {
            WebElement errorElement = driver.findElement(firstNameErrorMessage);
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstNameErrorMessage() {
        try {
            WebElement errorElement = driver.findElement(firstNameErrorMessage);
            return errorElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isFirstNameErrorRequiredMessage() {
        String msg = getFirstNameErrorMessage();
        return msg.contains("שדה זה הוא שדה חובה") || msg.contains("חובה למלא");
    }

    public boolean isFirstNameErrorHebrewOnlyMessage() {
        String msg = getFirstNameErrorMessage();
        return msg.contains("אותיות עבריות בלבד") || msg.contains("עברית בלבד");
    }

    public boolean isFirstNameInvalid() {
        try {
            WebElement input = getFirstNameInput();
            String ariaInvalid = input.getAttribute("aria-invalid");
            return ariaInvalid != null && ariaInvalid.equals("true");
        } catch (Exception e) {
            return false;
        }
    }

    // --- Last Name ---

    private WebElement getLastNameInput() {
        return driver.findElement(lastNameInput);
    }

    public void setLastName(String lastName) {
        WebElement input = getLastNameInput();
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.DELETE);
        input.sendKeys(lastName);
    }

    public String getLastName() {
        return getLastNameInput().getAttribute("value");
    }

    public boolean isLastNameVisible() {
        try {
            return getLastNameInput().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLastNameRequired() {
        return getLastNameInput().getAttribute("required") != null;
    }

    public void clearLastNameUsingJS() {
        WebElement input = getLastNameInput();
        js.clearUsingCtrlADelete(input);
    }

    public void deleteLastNameChars(int charsToDelete) {
        WebElement input = getLastNameInput();
        for (int i = 0; i < charsToDelete; i++) {
            input.sendKeys(Keys.BACK_SPACE);
        }
    }

    public boolean isLastNameErrorDisplayed() {
        try {
            WebElement errorElement = driver.findElement(lastNameErrorMessage);
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLastNameErrorMessage() {
        try {
            WebElement errorElement = driver.findElement(lastNameErrorMessage);
            return errorElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLastNameErrorRequiredMessage() {
        String msg = getLastNameErrorMessage();
        return msg.contains("שדה זה הוא שדה חובה") || msg.contains("חובה למלא");
    }

    public boolean isLastNameErrorHebrewOnlyMessage() {
        String msg = getLastNameErrorMessage();
        return msg.contains("אותיות עבריות בלבד") || msg.contains("עברית בלבד");
    }

    public boolean isLastNameInvalid() {
        try {
            WebElement input = getLastNameInput();
            String ariaInvalid = input.getAttribute("aria-invalid");
            return ariaInvalid != null && ariaInvalid.equals("true");
        } catch (Exception e) {
            return false;
        }
    }

    public By getFirstNameErrorMessageLocator() {
        return firstNameErrorMessage;
    }

    // --- הגדרת מתודה גנרית ליציאה מכל שדה ---

    public void leaveField(By fieldLocator) {
        WebElement input = driver.findElement(fieldLocator);
        input.sendKeys(Keys.TAB);
    }
    public void leaveFirstNameField() {
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@data-testid='applicant.fullName.firstName-input']"));
        firstNameInput.sendKeys(Keys.TAB);
    }

    public void leaveLastNameField() {
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@data-testid='applicant.fullName.lastName-input']"));
        lastNameInput.sendKeys(Keys.TAB);
    }


}
