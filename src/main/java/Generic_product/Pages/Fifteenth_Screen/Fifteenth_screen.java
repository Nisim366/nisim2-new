package Generic_product.Pages.Fifteenth_Screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Seventeenth_Screen.Seventeenth_screen;
import Generic_product.Pages.Sixteenth_Screen.Sixteenth_Screen;
import Generic_product.Pages.fourteen_Screen.fourteen_Screen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Fifteenth_screen extends Generic_BasePage {

    private final By voiceRecordButton = By.cssSelector("[data-testid='voiceRecorder-start-voice-record-button']");
    private final By continueButton = By.cssSelector("[data-testid='continue-button']");


    public Fifteenth_screen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnFifteenthScreen() {
        return  isElementVisible(voiceRecordButton);
    }
    public void startAndStopRecording(int secondsToRecord) {
        click(voiceRecordButton); // התחלת הקלטה
        try {
            Thread.sleep(secondsToRecord * 1000L); // המתנה לפי מספר שניות
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        click(voiceRecordButton); // סיום הקלטה
    }


    public void clickStartRecording() {
        click(voiceRecordButton);
    }
    public void clickContinueButton() {
        customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public Sixteenth_Screen completeFifteenthScreenFlow(int recordingDurationSeconds) {
        customWait(5).until(ExpectedConditions.elementToBeClickable(voiceRecordButton)).click();

        try {
            Thread.sleep(recordingDurationSeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("❌ ההמתנה להקלטה הופסקה", e);
        }
        customWait(2).until(ExpectedConditions.elementToBeClickable(voiceRecordButton)).click();
        System.out.println("מסך 15 - הקלטה קולית");

        customWait(2).until(ExpectedConditions.elementToBeClickable(continueButton)).click();

        return new Sixteenth_Screen(driver);
    }

    public Sixteenth_Screen goToSixteenthScreen() {
        return completeFifteenthScreenFlow(3);
    }



}
