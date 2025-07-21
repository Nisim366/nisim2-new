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
    private final By checkboxInput = By.id("meta.consents.privacyNote-checkbox");


    // בודק האם הכותרת של העמוד הראשי קיימת ומכילה את הטקסט הצפוי
    private final By headerFirstPage = By.xpath("//h1[@id='page-header' and contains(text(), 'זו תקופה מרגשת')]");

    public DevToolsHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * האם העמוד הראשי מוגדר תקין?
     * @return true אם הכותרת הראשית מוצגת עם הטקסט הצפוי, אחרת false
     */
    public boolean isOnFirstPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxInput));
            WebElement checkbox = driver.findElement(checkboxInput);
            return checkbox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * פותח את DevTools ועובר ל-console
     */
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

    /**
     * קפיצה למסך מסוים לפי שם שלב
     * @param stepName שם השלב אליו רוצים לקפוץ
     */
    public void jumpToScreen(String stepName) {
        long start = System.currentTimeMillis();
        long timeout = 10000; // 10 שניות
        while (!isOnFirstPage() && System.currentTimeMillis() - start < timeout) {
            try {
                Thread.sleep(200); // המתנה קצרה בין בדיקות
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        String script = String.format("ezbob.actions.userState.setCurrentStepByName('%s')", stepName);
        System.out.println("🔵 Trying to jump to screen: " + stepName);
        System.out.println("🔵 Script: " + script);
        try {
            ((JavascriptExecutor) driver).executeScript(script);
            System.out.println("🟢 Script executed via JavascriptExecutor.");
            waitForStepToLoad(stepName);
        } catch (Exception e) {
            System.out.println("🔴 Failed to execute script directly: " + e.getMessage());
            // אם לא הצליח להריץ ישירות, פותח DevTools, עובר ל-console, ומדביק את הפקודה
            openDevToolsAndGoToConsole();
            try {
                Robot robot = new Robot();
                // Ctrl+V (הדבקה של allow pasting)
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);

                // קודם כל לרשום allow pasting
                String allowPasting = "allow pasting";
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                        new java.awt.datatransfer.StringSelection(allowPasting), null);

                // Ctrl+V (הדבקה של allow pasting)
                System.out.println("🟡 Pasting 'allow pasting' in DevTools console...");
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);

                // Enter להריץ את allow pasting
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                // עכשיו להדביק את הסקריפט
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                        new java.awt.datatransfer.StringSelection(script), null);

                // Ctrl+V (הדבקה של הסקריפט)
                System.out.println("🟡 Pasting script in DevTools console...");
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);

                // Enter להריץ את הסקריפט
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                System.out.println("🟢 Script pasted and executed in DevTools console.");
                waitForStepToLoad(stepName);
            } catch (Exception ex) {
                System.out.println("⚠️ Failed to paste and run script in DevTools console: " + ex.getMessage());
            }
        }
    }

    /**
     * ממתין לטעינת המסך לפי שם השלב
     * @param stepName שם השלב
     */
    private void waitForStepToLoad(String stepName) {
        By stepLocator = By.cssSelector("[data-step-name='" + stepName + "']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(stepLocator));
        } catch (TimeoutException e) {
            System.out.println("allow pasting");
            System.out.println("ezbob.actions.userState.setCurrentStepByName('" + stepName + "')");
        }
    }
}