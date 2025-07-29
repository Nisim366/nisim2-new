package Generic_product.Pages.Seventeenth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Eighteenth_screen.Eighteenth_screen;
import Generic_product.config.ClientContext;
import Generic_product.data.Generic_UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Seventeenth_screen extends Generic_BasePage {

    private final By securityQuestionDropdown = By.cssSelector("[aria-label*='שאלת הזדהות'][role='combobox']");
    private final By securityAnswerInput = By.cssSelector("[data-testid='applicant.custom.security_answer-input']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");




    public Seventeenth_screen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnSeventeenthScreen() {
        return  isElementVisible(securityQuestionDropdown);
    }

    public void selectFirstSecurityQuestionOption() {
        WebElement dropdown = customWait(2).until(ExpectedConditions.elementToBeClickable(securityQuestionDropdown));
        dropdown.click(); // פתיחת הדרופדאון

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform(); // שליחת חץ מטה

        try {
            Thread.sleep(2000); // המתנה של 2 שניות
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        actions.sendKeys(Keys.ENTER).perform(); // אישור הבחירה
    }
    public void enterSecurityAnswer(String answer) {
        WebElement inputField = customWait(2).until(ExpectedConditions.elementToBeClickable(securityAnswerInput));
        inputField.click(); // לוודא שהפוקוס בשדה
        inputField.sendKeys(answer); // הזנת הטקסט
    }

    public void clickContinueButton() {
        customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public Eighteenth_screen completeSeventeenthScreenFlow() {
        Generic_UserData user = new Generic_UserData(ClientContext.getClient());

        selectFirstSecurityQuestionOption(); // בוחר שאלה ראשונה
        enterSecurityAnswer(user.security.securityAnswer);
        System.out.println("מסך 17 - שאלת הזדהות");
        clickContinueButton();
        return new Eighteenth_screen(driver);
    }


    public Eighteenth_screen goToNineteenthscreen() {
        return completeSeventeenthScreenFlow();
    }




}
