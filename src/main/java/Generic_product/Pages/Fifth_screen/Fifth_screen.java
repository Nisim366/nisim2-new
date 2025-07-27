package Generic_product.Pages.Fifth_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import Generic_product.config.ClientContext;
import Generic_product.data.Generic_UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Fifth_screen extends Generic_BasePage {

    private final By townInput = By.cssSelector("[data-testid='applicant.address.town-input']");
    private final By streetInput = By.cssSelector("[data-testid='applicant.address.line1-input']");
    private final By houseNumberInput = By.cssSelector("[data-testid='applicant.address.line2-input']");
    private final By apartmentInput = By.cssSelector("[data-testid='applicant.address.line3-input']");
    private final By zipCodeInput = By.cssSelector("[data-testid='applicant.address.postcode-input']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");


    public Fifth_screen(WebDriver driver) {
        super(driver);
    }




    public boolean isOnFifthScreen() {
        return isElementVisible(townInput);
    }
    public void waitForSixScreen() {
        new WebDriverWait(driver, Duration.ofSeconds(90)).until(ExpectedConditions.visibilityOfElementLocated(townInput));
    }

    // --- מתודות עבור שדה עיר ---




    public void clearTownInput() {
        clear(townInput);
    }

    public void enterTown(String town) {
        set(townInput, town);
    }

    private void enterTextAndSelectFromAutoComplete(By inputLocator, String textToEnter) {
        WebElement input = customWait(2).until(ExpectedConditions.elementToBeClickable(inputLocator));
        input.sendKeys(textToEnter);

        try {
            Thread.sleep(4000); // המתנה 4 שניות לפני חץ מטה
            input.sendKeys(Keys.ARROW_DOWN);

            Thread.sleep(2000); // המתנה 2 שניות לפני אנטר
            input.sendKeys(Keys.ENTER);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void enterTownAndSelect(String town) {
        enterTextAndSelectFromAutoComplete(townInput, town);
    }
    public void enterstreetAndSelect(String town) {
        enterTextAndSelectFromAutoComplete(streetInput, town);
    }

    public void enterHouseNumber(String number) {
        set(houseNumberInput, number);
    }

    public boolean isApartmentInputVisible() {
        return isElementVisible(apartmentInput);
    }

    public void enterApartment(String apartment) {
        set(apartmentInput, apartment);
    }

    public boolean isZipCodeInputVisible() {
        return isElementVisible(zipCodeInput);
    }

    public void enterZipCode(String zip) {
        set(zipCodeInput, zip);
    }

    // --- מתודות שימושיות כלליות ---
    public void clickContinueButton() {
        click(continueButton);
    }


    public Sixthscreen completeFifthScreenHappyFlow() {
        Generic_UserData user = new Generic_UserData(ClientContext.getClient());

        String town = user.address.town;
        String street = user.address.street;
        String houseNumber = user.address.houseNumber;
        String apartment = user.address.apartment;
        String zipCode = user.address.zipCode;

        try {
            enterTownAndSelect(town);
            enterstreetAndSelect(street);
            enterHouseNumber(houseNumber);
            enterApartment(apartment);
            enterZipCode(zipCode);

            clickContinueButton();
            System.out.println("מסך 5 - מהי כתובת המגורים שלך ?");
            return new Sixthscreen(driver);
        } catch (Exception e) {
            throw new RuntimeException("❌ שגיאה בהשלמת מסך הכתובת (המסך החמישי).", e);
        }
    }

    public Sixthscreen goToSixthScreen() {

        waitForSixScreen();
        return completeFifthScreenHappyFlow();
    }

}
