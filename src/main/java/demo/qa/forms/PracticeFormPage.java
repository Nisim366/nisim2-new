package demo.qa.forms;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;
import java.time.Duration;
import java.util.logging.Logger;

public class PracticeFormPage extends FormsPage {

    private final JavaScriptUtility jsUtil;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(PracticeFormPage.class.getName());

    private final By maleRadioLabel = By.xpath("//label[text()='Male']");
    private final By maleRadioInput = By.id("gender-radio-1");
    private final By femaleRadioLabel = By.xpath("//label[text()='Female']");
    private final By femaleRadioInput = By.id("gender-radio-2");
    private final By otherRadioLabel = By.xpath("//label[text()='Other']");
    private final By otherRadioInput = By.id("gender-radio-3");

    private final By sportHobbyInput = By.id("hobbies-checkbox-1");
    private final By readingHobbyInput = By.id("hobbies-checkbox-2");
    private final By musicHobbyInput = By.id("hobbies-checkbox-3");

    private final By sportHobbyLabel = By.cssSelector("label[for='hobbies-checkbox-1']");
    private final By readingHobbyLabel = By.cssSelector("label[for='hobbies-checkbox-2']");
    private final By musicHobbyLabel = By.cssSelector("label[for='hobbies-checkbox-3']");

    private final By firstNameFieldInput = By.id("firstName");
    private final By lastNameFieldInput = By.id("lastName");

    public PracticeFormPage(WebDriver driver) {
        super(driver);
        logger.info("Initializing JavaScriptUtility...");
        jsUtil = new JavaScriptUtility(driver);
        logger.info("JavaScriptUtility initialized successfully.");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private void clickLabel(By labelLocator, By inputLocator) {
        try {
            jsUtil.scrollToElementJS(labelLocator);
            WebElement labelElement = driver.findElement(labelLocator);
            labelElement.click();
            wait.until(ExpectedConditions.elementToBeSelected(inputLocator));
            logger.info("Clicked label: " + labelLocator + " and confirmed selection.");
        } catch (Exception e) {
            logger.severe("Error while clicking the label: " + e.getMessage() + " for locator: " + labelLocator);
            e.printStackTrace();
        }
    }

    private void unClickCheckBox(By labelLocator, By inputLocator) {
        try {
            if (isElementSelected(inputLocator)) {
                logger.info("Checkbox is selected, unclicking now: " + inputLocator);
                jsUtil.scrollToElementJS(labelLocator);
                WebElement labelElement = driver.findElement(labelLocator);
                labelElement.click();
                wait.until(ExpectedConditions.elementSelectionStateToBe(inputLocator, false));
                logger.info("Checkbox has been unclicked: " + inputLocator);
            } else {
                logger.info("Checkbox is already unselected: " + inputLocator);
            }
        } catch (Exception e) {
            logger.severe("Error while unclicking the checkbox: " + e.getMessage() + " for locator: " + inputLocator);
            e.printStackTrace();
        }
    }

    public boolean isElementSelected(By inputLocator) {
        try {
            jsUtil.scrollToElementJS(inputLocator);
            WebElement element = driver.findElement(inputLocator);
            boolean isSelected = element.isSelected();
            logger.info("Element selected: " + isSelected + " for locator: " + inputLocator);
            return isSelected;
        } catch (Exception e) {
            logger.severe("Error while checking if element is selected: " + e.getMessage() + " for locator: " + inputLocator);
            return false;
        }
    }

    public void clickSportCheckBox() {
        clickLabel(sportHobbyLabel, sportHobbyInput);
    }
    public void clickReadingCheckBox() {
        clickLabel(readingHobbyLabel, readingHobbyInput);
    }
    public void clickMusicCheckBox() {
        clickLabel(musicHobbyLabel, musicHobbyInput);
    }



    public void unClickSportCheckBox() {
        unClickCheckBox(sportHobbyLabel, sportHobbyInput);
    }
    public void unClickReadingCheckBox() {
        unClickCheckBox(readingHobbyLabel, readingHobbyInput);
    }
    public void unClickMusicCheckBox() {
        unClickCheckBox(musicHobbyLabel, musicHobbyInput);
    }

    // Methods to check if checkboxes are selected
    public boolean isSportSelected() {
        return isElementSelected(sportHobbyInput);
    }

    public boolean isReadingSelected() {
        return isElementSelected(readingHobbyInput);
    }

    public boolean isMusicSelected() {
        return isElementSelected(musicHobbyInput);
    }


    public void clickFemaleRadioButton() {
        clickLabel(femaleRadioLabel, femaleRadioInput);
    }
    public void clickMaleRadioButton() {
        clickLabel(maleRadioLabel, maleRadioInput);
    }
    public void clickOtherRadioButton() {
        clickLabel(otherRadioLabel, otherRadioInput);
    }
    public WebElement getFemaleRadioButton() {
        return find(femaleRadioLabel);
    }



    public boolean isFemaleRadioButtonSelected() {
        return isElementSelected(femaleRadioInput);
    }
    public boolean isMaleRadioButtonSelected() {
        return isElementSelected(maleRadioInput);
    }
    public boolean isOtherRadioButtonSelected() {
        return isElementSelected(otherRadioInput);
    }

    private void enterValue(By locator, String value){
        try{
            WebElement field   = wait.until(ExpectedConditions.elementToBeClickable(locator));
            field .sendKeys(value);

            String enteredValue = field .getAttribute("value");
            if (!enteredValue.equals(value)) {
                System.err.println("Value mismatch: expected '" + value + "', but found '" + enteredValue + "'");
            } else {
                System.out.println("Value successfully entered: '" + enteredValue + "'");
            }

        } catch (Exception e) {
            System.err.println("Failed to enter value into field: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void enterFirstName(String value){
        enterValue(firstNameFieldInput,value);
    }
    public void enterLasttName(String value){
        enterValue(lastNameFieldInput,value);
    }

    private void clearField(By locator) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
        field.clear();
    }

    public void clearFirstName() {
        clearField(firstNameFieldInput);

    }

    public void clearLastName() {
        clearField(lastNameFieldInput);
    }

    private String getFieldValue(By locator) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return field.getAttribute("value");
    }

    public String getFirstNameValue() {
        return getFieldValue(firstNameFieldInput);
    }

    public String getLastNameValue() {
        return getFieldValue(lastNameFieldInput);
    }

    private void deleteLastCharactersAndCheck(By locator, int numChars) {
        try {
            WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
            String initialValue = field.getAttribute("value");

            for (int i = 0; i < numChars; i++) {
                field.sendKeys(Keys.BACK_SPACE);
            }

            String newValue = field.getAttribute("value");

            if (newValue.equals(initialValue)) {
                System.err.println("No characters were deleted.");
            } else {
                System.out.println("Deleted characters : "+ numChars);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while deleting characters: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteLastCharactersFromFirstName(int numChars) {
        deleteLastCharactersAndCheck(firstNameFieldInput, numChars);
    }

    public void deleteLastCharactersFromLastName(int numChars) {
        deleteLastCharactersAndCheck(lastNameFieldInput, numChars);
    }

    private boolean isValueEntered(By locator) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
        String enteredValue = field.getAttribute("value");
        return enteredValue != null && !enteredValue.isEmpty();
    }
    public boolean isFirstNameEntered() {
        return isValueEntered(firstNameFieldInput);
    }


    public boolean isLastNameEntered() {
        return isValueEntered(lastNameFieldInput);
    }





}
