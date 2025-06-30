package Generic_product.Pages.Fifth_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Sixth_screen.Sixthscreen;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    // --- מתודות עבור שדה עיר ---
    public boolean isTownInputVisible() {
        return isElementVisible(townInput);
    }

    public boolean isTownInputEnabled() {
        return isElementEnabled(townInput);
    }

    public String getTownInputValue() {
        return getElementAttribute(townInput, "value");
    }

    public void clearTownInput() {
        clear(townInput);
    }

    public void enterTown(String town) {
        set(townInput, town);
    }

    private void enterTextAndSelectFromAutoComplete(By inputLocator, String textToEnter) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        input.clear();
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











    // --- מתודות עבור שדה רחוב ---
    public boolean isStreetInputVisible() {
        return isElementVisible(streetInput);
    }

    public boolean isStreetInputEnabled() {
        return isElementEnabled(streetInput);
    }

    public String getStreetInputValue() {
        return getElementAttribute(streetInput, "value");
    }


    public void clearStreetInput() {
        clear(streetInput);
    }

    public void enterStreet(String street) {
        set(streetInput, street);
    }



    // --- מתודות עבור שדות נוספים ---
    public boolean isHouseNumberInputVisible() {
        return isElementVisible(houseNumberInput);
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
        // ערכים קבועים לתרחיש happy path
        String town = "אבו";
        String street = "אבו";
        String houseNumber = "15";
        String apartment = "3";
        String zipCode = "6900000";

        enterTownAndSelect(town);
        enterstreetAndSelect(street);
        enterHouseNumber(houseNumber);
        enterApartment(apartment);
        enterZipCode(zipCode);

        clickContinueButton();
        System.out.println("מסך כתובת מגורים ");


        return new Sixthscreen(driver);
    }


    public Sixthscreen goToSixthScreen() {
        return completeFifthScreenHappyFlow();
    }

}
