package Morning.Processes.Sole;

import Morning.Base.Morning_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Third_screen extends Morning_BasePage {

    private final By firstNameField = By.cssSelector("[data-testid='applicant.fullName.firstName-input']");

    public Third_screen(WebDriver driver) {
        super(driver);
    }

    // ✅ זיהוי מסך שלישי
    public boolean isOnThirdPage() {
        return isOnCorrectScreen(3);
    }

    // ✅ מעבר למסך הרביעי
    private Fourth_screen completeFourthScreenHappyFlow() {
        handleWelcomeBackPopupIfExists();
        clickContinueButton();
        System.out.println("מסך 3 - נתחיל בהיכרות");
        return new Fourth_screen(driver); // ⬅️ מעביר הלאה
    }

    public Fourth_screen goToFourthScreen() {
        return completeFourthScreenHappyFlow();
    }
}
