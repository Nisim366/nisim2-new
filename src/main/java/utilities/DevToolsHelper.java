package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class DevToolsHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public DevToolsHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openDevToolsAndGoToConsole() {
        try {
            Robot robot = new Robot();

            // Ctrl + Shift + J – פותח DevTools
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_J);
            robot.keyRelease(KeyEvent.VK_J);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // ממתינים לפתיחת DevTools – דרך JavaScript לודא פתיחה
            wait.until(driver1 -> {
                Object devToolsOpen = ((JavascriptExecutor) driver).executeScript(
                        "return window.devtools && window.devtools.opened;");
                return devToolsOpen != null && devToolsOpen.equals(true);
            });

            // Ctrl + 2 – מעבר ל־Console (בתנאי שזה מקונפג ב־DevTools)
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_CONTROL);

        } catch (AWTException e) {
            System.out.println("⚠️ Failed to use Robot for DevTools: " + e.getMessage());
        } catch (TimeoutException te) {
            System.out.println("⚠️ DevTools did not open in time.");
        }
    }

    public void jumpToScreen(String stepName) {
        String script = String.format("ezbob.actions.userState.setCurrentStepByName('%s')", stepName);
        ((JavascriptExecutor) driver).executeScript(script);
        waitForStepToLoad(stepName);
    }

    private void waitForStepToLoad(String stepName) {
        By stepLocator = By.cssSelector("[data-step-name='" + stepName + "']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(stepLocator));
        } catch (TimeoutException e) {
            System.out.println("⚠️ Timeout waiting for step: " + stepName);
        }
    }
}
