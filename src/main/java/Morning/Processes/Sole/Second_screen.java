package Morning.Processes.Sole;

import Morning.Base.Morning_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Second_screen extends Morning_BasePage {

    private final By secondScreenTitle = By.xpath("//h2[text()='כמה שלבים פשוטים']");

    public Second_screen(WebDriver driver) {
        super(driver);
    }
    public int expectedStep() {
        return 2; // או מספר מדויק לפי הקונבנציה שלך
    }
    // ✅ בדיקת נוכחות במסך השני
    public boolean isOnSecondPage() {
        return isOnPage(2);
    }


    // ✅ זרימת המשך למסך השלישי
    private Third_screen completeThirdScreenHappyFlow() {
        handleWelcomeBackPopupIfExists();
        clickContinueButton();
        System.out.println("מסך 2 - שמחים שהגעת אלינו");
        return new Third_screen(driver);
    }

    public Third_screen goToThirdScreen() {
        return completeThirdScreenHappyFlow();
    }
}
