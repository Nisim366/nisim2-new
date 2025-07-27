package Morning.Processes.Sole;

import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.config.ClientContext;
import Morning.Base.Morning_BasePage;
import Morning.Data.Morning_UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Fourth_screen extends Morning_BasePage {

    private final By headerText = By.xpath("//h2[contains(text(), 'השלב הבא')]");
    private final By issueDateInput = By.cssSelector("[data-testid='applicant.identifierIssueDate-datePicker']");
    private final By birthDateInput = By.xpath("//label[text()='תאריך לידה']/following-sibling::div//input");

    private final By genderSelect = By.cssSelector("div[role='combobox'][aria-label='מגדר']");
    private final By genderOptionMale = By.xpath("//li[normalize-space()='זכר']");
    private final By genderOptionFemale = By.xpath("//li[normalize-space()='נקבה']");

// שנה לפי כותרת מזהה במסך

    public Fourth_screen(WebDriver driver) {
        super(driver);
    }
    public boolean isOnFourthPage() {
        return isOnCorrectScreen(4);
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

    public void selectGender(String genderText) {
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



    public Fifth_screen goToFifthScreenWithEnvData() {
        Morning_UserData user = new Morning_UserData(ClientContext.getClient());
        String issueDate = user.idCard.issueDate;
        String birthDate = user.idCard.birthDate;
        String gender = user.idCard.gender;

        enterIssueDate(issueDate);
        enterBirthDate(birthDate);
        selectGender(gender);
        clickContinueButton();
        return new Fifth_screen(driver);
    }

    public Fifth_screen goToFifthScreen() {
        return goToFifthScreenWithEnvData();
    }
}
