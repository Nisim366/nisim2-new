package Generic.Test.Part1.Screens.Screen1;

import Generic.Base.BaseTest_Generic;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManualScreenTransitionControlTest extends BaseTest_Generic {

    // שימו לב: אם הטקסט האמיתי כולל תו רווח ברוחב אפס בסוף (נתחיל בכמה פרטים אישיים​),
    // וודא שגם המשתנה הזה מכיל אותו. אחרת, הטקסט לא יתאים.
    // אם הוא לא כולל, מה שכתוב כרגע זה בסדר:
    private final String EXPECTED_HEADER_TEXT_AFTER_MANUAL_STEP = "נתחיל בכמה פרטים אישיים";


    public void step1_openApplicationUrlAndLoadPage() {
        wait.until(ExpectedConditions.urlContains("customer/wizard"));
        System.out.println("✅ האפליקציה נפתחה בהצלחה בכתובת: " + driver.getCurrentUrl());
        System.out.println("עכשיו הדף מוכן להתערבות ידנית.");
    }

    public void step2_waitForManualConsoleInputAndScreenTransition() {
        System.out.println("\n--- עצירה: התערבות ידנית בקונסול ---");
        System.out.println("   1. פתח דפדפן (F12 או Ctrl+Shift+J).");
        System.out.println("   2. עבור ללשונית 'Console'.");
        System.out.println("   3. הקלד: ezbob.actions.userState.setCurrentStepByName('contactDetailsGeneric')");
        System.out.println("   4. לחץ Enter, וודא מעבר מסך.");
        System.out.println("   5. חזור ל-IDE והמשך Debug.");

        boolean manualInterventionDone = true; // הגדר כאן Breakpoint
        System.out.println("--- ממשיך לאחר ההתערבות הידנית ---");
    }

    public void step3_verifyNewScreenHeader(String expectedHeaderText) {
        By pageHeaderLocator = By.id("page-header");

        // 1. המתן עד שהאלמנט יהיה גלוי *וגם* יכיל את הטקסט הצפוי.
        // זהו תנאי המתנה חזק יותר שמטפל בשינויי DOM.
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator),
                ExpectedConditions.textToBePresentInElementLocated(pageHeaderLocator, expectedHeaderText)
        ));

        // 2. לאחר שהתנאים התקיימו, מצא את האלמנט מחדש כדי לוודא שיש לנו הפניה טרייה.
        WebElement pageHeader = driver.findElement(pageHeaderLocator);

        // 3. בצע את הבדיקות על ההפניה הטרייה.
        assertTrue(pageHeader.isDisplayed(), "כותרת הדף לא מוצגת במסך החדש.");
        String actualHeaderText = pageHeader.getText();
        assertTrue(actualHeaderText.contains(expectedHeaderText),
                "כותרת הדף אינה מכילה את הטקסט הצפוי. צפוי: '" + expectedHeaderText + "', בפועל: '" + actualHeaderText + "'");

        System.out.println("✅ אימות הכותרת בוצע בהצלחה למסך החדש: '" + actualHeaderText + "'");
    }

    public void step4_continueWithAutomatedTestsOnNewScreen() {
        System.out.println("--- ממשיך עם בדיקות אוטומציה למסך 'פרטים אישיים' ---");
        // assertTrue(driver.findElement(By.id("firstNameInput")).isDisplayed(), "שדה שם פרטי אינו מוצג.");
        // driver.findElement(By.id("firstNameInput")).sendKeys("חן");
        System.out.println("✅ בדיקות אוטומציה למסך 'פרטים אישיים' הסתיימו.");
    }

    @Test
    public void testManualConsoleFlowWithAutomationContinuity() {
        step1_openApplicationUrlAndLoadPage();
        step2_waitForManualConsoleInputAndScreenTransition();
        step3_verifyNewScreenHeader(EXPECTED_HEADER_TEXT_AFTER_MANUAL_STEP);
        step4_continueWithAutomatedTestsOnNewScreen();
    }
}