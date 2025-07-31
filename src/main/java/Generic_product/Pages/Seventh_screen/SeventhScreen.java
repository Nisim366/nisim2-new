package Generic_product.Pages.Seventh_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Eighth_Screen.EighthScreen;
import Generic_product.config.ClientContext;
import Generic_product.data.Generic_UserData;
import Generic_product.enums.EmploymentStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.time.Duration;
import java.util.*;

public class SeventhScreen extends Generic_BasePage {

    // אלמנט מזהה למסך (הכפתור של מימון חופשת לידה)
    private final By selectedMaternityOption = By.cssSelector("div[role='radio'][aria-checked='true'] button[data-testid='loanRequest.loanPurpose-toggleButtonGroup-MATERNITY_LEAVE']");
    private final By continueButton = By.cssSelector("button[data-testid='continue-button']");
    private final By allLoanPurposeOptions = By.cssSelector("button[data-testid^='loanRequest.loanPurpose-toggleButtonGroup-']");

    public SeventhScreen(WebDriver driver) {
        super(driver);
    }

    // בדיקה האם המסך השביעי של FIRST פתוח
    public boolean isOnSeventhScreen() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(allLoanPurposeOptions)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void selectLoanPurpose(String purpose) {
        By option = By.cssSelector("[data-testid='loanRequest.loanPurpose-toggleButtonGroup-" + purpose + "']");

        // המתנה ולחיצה
        customWait(5).until(ExpectedConditions.elementToBeClickable(option)).click();

        System.out.println("✅ נבחר יעד הלוואה: " + purpose);
    }




    // בדיקה אם נבחר מימון חופשת לידה
    public boolean isMaternityOptionSelected() {
        try {
            return customWait(2).until(ExpectedConditions.visibilityOfElementLocated(selectedMaternityOption)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButton() {
        customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    private String getLoanPurposeFromEnv() {
        String user = System.getProperty("test.user");
        if (user == null || user.isEmpty()) {
            return null;
        }
        String resourcePath = String.format("users/%s.properties", user);
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            Properties prop = new Properties();
            if (input != null) {
                prop.load(input);
                return prop.getProperty("loanPurpose");
            }
        } catch (Exception e) {
            // handle error
        }
        return null;
    }

    /**
     * ממתין לטעינת כל האופציות של מטרת ההלוואה לפני בחירה
     */
    public String selectLoanPurposeOptionFromEnvOrRandom() {
        // המתנה מפורשת לטעינת כל האופציות לפני בחירה
        List<WebElement> options = customWait(10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allLoanPurposeOptions));

        String chosenPurpose = null;
        String envPurpose = getLoanPurposeFromEnv();

        if (envPurpose != null) {
            for (WebElement option : options) {
                String dataTestId = option.getAttribute("data-testid");
                if (dataTestId != null && dataTestId.endsWith(envPurpose)) {
                    option.click();
                    chosenPurpose = envPurpose;
                    break;
                }
            }
        }

        if (chosenPurpose == null && !options.isEmpty()) {
            Random rand = new Random();
            WebElement randomOption = options.get(rand.nextInt(options.size()));
            randomOption.click();
            String dataTestId = randomOption.getAttribute("data-testid");
            chosenPurpose = dataTestId.substring(dataTestId.lastIndexOf('-') + 1);
        }

        System.out.println("נבחרה אופציה: " + chosenPurpose);
        return chosenPurpose;
    }
    public void selectOptionByTestId(String testId) {
        By option = By.cssSelector("[data-testid='" + testId + "']");
        customWait(2).until(ExpectedConditions.elementToBeClickable(option)).click();
    }


    public EighthScreen completeSeventhScreenHappyFlow() {
        isOnSeventhScreen();

        Generic_UserData user = new Generic_UserData(ClientContext.getClient());

        EmploymentStatus.LoanPurpose chosenPurpose = user.loanPurpose;
        By option = By.cssSelector("[data-testid='loanRequest.loanPurpose-toggleButtonGroup-" + chosenPurpose.getValue() + "']");
        customWait(1).until(ExpectedConditions.elementToBeClickable(option)).click();

        clickContinueButton();
        System.out.println("מסך 7 - מטרת הלוואה, נבחר: " + chosenPurpose.getValue());

        return new EighthScreen(driver);
    }


    public EighthScreen goToEighthScreen() {
        return completeSeventhScreenHappyFlow();
    }
}
