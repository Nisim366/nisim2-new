package Generic_product.Pages.Eighteenth_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Nineteenth_screen.Nineteenthscreen;
import Generic_product.config.ClientContext;
import Generic_product.data.Generic_UserData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class Eighteenth_screen extends Generic_BasePage {

    private  final By bankInput = By.cssSelector("[data-testid='manualBankAccount.bankCode-input']");
    private  final By branchInput = By.cssSelector("[data-testid='manualBankAccount.branchCode-input']");
    private  final By accountNumberInput = By.cssSelector("[data-testid='manualBankAccount.accountNumber-input']");
    private  final By continueButton = By.cssSelector("[data-testid='continue-button']");


    public Eighteenth_screen(WebDriver driver) {
        super(driver);
    }
    public boolean isOnEighteenthscreen (){
        return  isElementVisible(bankInput);
    }

    public void selectAutocompleteValue(By fieldLocator, String value) {
        try {
            customWait(2).until(ExpectedConditions.elementToBeClickable(fieldLocator));
            WebElement inputField = driver.findElement(fieldLocator);
            inputField.click();
            inputField.sendKeys(value);

            Thread.sleep(2500);
            inputField.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1500);
            inputField.sendKeys(Keys.ENTER);

        }catch (TimeoutException e) {
            throw new RuntimeException("⏳ Timeout – לא נמצאה אפשרות תואמת עבור הערך '" + value + "' בשדה: " + fieldLocator, e);
        }  catch (Exception e) {
            throw new RuntimeException("❌ שגיאה כללית בבחירת ערך אוטומטי בשדה: " + fieldLocator, e);
        }
    }

    public void selectBank(String value) {
        selectAutocompleteValue(bankInput, value);
    }

    public void selectBranch(String value) {
        selectAutocompleteValue(branchInput, value);
    }
    public void enterAccountNumber(String accountNumber) {
        try {
            customWait(2).until(ExpectedConditions.elementToBeClickable(accountNumberInput));
            WebElement inputField = driver.findElement(accountNumberInput);
            inputField.click();
            inputField.sendKeys(accountNumber);

        } catch (TimeoutException e) {
            throw new RuntimeException("⏳ Timeout – שדה מספר החשבון לא הפך קליקבילי בזמן.", e);
        } catch (Exception e) {
            throw new RuntimeException("❌ שגיאה בהזנת מספר חשבון: " + accountNumber, e);
        }
    }

    public void clickContinueButton() {
        customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public Nineteenthscreen completeEighteenthScreenFlow(String bankCode, String branchCode, String accountNumber) {
        selectBank(bankCode);
        selectBranch(branchCode);
        enterAccountNumber(accountNumber);
        clickContinueButton();
        System.out.println("מסך 18 - פרטי בנק");
        return new Nineteenthscreen(driver);
    }



    public Nineteenthscreen goToNineteenthScreen() {
        Generic_UserData user = new Generic_UserData(ClientContext.getClient());
        return completeEighteenthScreenFlow(
                user.bank.bankCode,
                user.bank.branchCode,
                user.bank.accountNumber
        );
    }




}
