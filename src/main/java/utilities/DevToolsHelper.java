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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // WebDriverWait with Duration
    }

    public void openDevToolsAndGoToConsole() {
        try {
            Robot robot = new Robot();

            // Sending Ctrl + Shift + J to open DevTools
            try {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_J);
                robot.keyRelease(KeyEvent.VK_J);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                System.out.println("✅ DevTools open shortcut sent.");
            } catch (Exception e) {
                System.err.println("❌ Failed sending DevTools shortcut.");
                e.printStackTrace();
                throw new RuntimeException("Failed to send DevTools shortcut", e);
            }

            // Wait for DevTools to open
            try {
                wait.until(driver1 -> {
                    Object result = ((JavascriptExecutor) driver)
                            .executeScript("return window.outerHeight - window.innerHeight > 100");
                    return result != null && result.toString().equals("true");
                });
                System.out.println("✅ DevTools opened confirmed via window height check.");
            } catch (TimeoutException te) {
                System.err.println("❌ Timeout: DevTools height check failed.");
                te.printStackTrace();
                throw new RuntimeException("Timeout while waiting for DevTools to open", te);
            }

            // Switch to Console tab (Ctrl + 2)
            try {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                System.out.println("✅ Switched to Console tab.");
            } catch (Exception e) {
                System.err.println("❌ Failed switching to Console tab.");
                e.printStackTrace();
                throw new RuntimeException("Failed to switch to Console tab", e);
            }

        } catch (AWTException e) {
            System.err.println("❌ Robot initialization failed.");
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Robot", e);
        } catch (Exception e) {
            System.err.println("❌ General error in openDevToolsAndGoToConsole.");
            e.printStackTrace();
            throw e;
        }
    }

    public void jumpToScreen(String stepName, By locatorToWaitFor) {
        try {
            String script = String.format("ezbob.actions.userState.setCurrentStepByName('%s')", stepName);
            ((JavascriptExecutor) driver).executeScript(script);
            System.out.println("✅ JavaScript command sent to jump to: " + stepName);
        } catch (JavascriptException e) {
            System.err.println("❌ JavaScript execution failed: jumpToScreen - " + stepName);
            e.printStackTrace();
            throw new RuntimeException("JavaScript error jumping to screen: " + stepName, e);
        } catch (Exception e) {
            System.err.println("❌ Unexpected error in jumpToScreen - " + stepName);
            e.printStackTrace();
            throw e;
        }

        waitForStepToLoad(locatorToWaitFor, stepName);
    }

    private void waitForStepToLoad(By locator, String stepName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("Step loaded: " + stepName);
        } catch (TimeoutException e) {
            System.err.println("⚠️ Timeout waiting for step to load: " + stepName);
            e.printStackTrace();
            throw new RuntimeException("Timeout waiting for step to load: " + stepName, e);
        } catch (Exception e) {
            System.err.println("⚠️ Unexpected error while waiting for step to load: " + stepName);
            e.printStackTrace();
            throw e;
        }
    }
}
