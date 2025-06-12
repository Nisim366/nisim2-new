package Generic.Test.Part1.Screens.Screen1;

import Generic.Base.BaseTest_Generic;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManualScreenTransitionControlTest extends BaseTest_Generic {

    private final String EXPECTED_HEADER_TEXT_SCREEN_2 = "נתחיל בכמה פרטים אישיים";
    private final String JS_COMMAND_STEP_SCREEN_2 = "contactDetailsGeneric";

    private final String EXPECTED_HEADER_TEXT_SCREEN_3 = "מה קוד האימות שקיבלת?";
    private final String JS_COMMAND_STEP_SCREEN_3 = "otpPageGeneric";

    private final String EXPECTED_HEADER_TEXT_SCREEN_4 = "עכשיו כמה פרטים מזהים";
    private final String JS_COMMAND_STEP_SCREEN_4 = "identificationDetailsGeneric";


    public void step1_openApplicationUrlAndLoadPage() {
        wait.until(ExpectedConditions.urlContains("customer/wizard"));
    }

    public void step2_waitForManualConsoleInputAndScreenTransition(String jsCommandToEnter) {
        System.out.println("   3. הקלד: " + jsCommandToEnter);
        boolean manualInterventionDone = true;
    }

    public void step3_verifyNewScreenHeader(String expectedHeaderText) {
        By pageHeaderLocator = By.id("page-header");

        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator),
                ExpectedConditions.textToBePresentInElementLocated(pageHeaderLocator, expectedHeaderText)
        ));

        WebElement pageHeader = driver.findElement(pageHeaderLocator);

        assertTrue(pageHeader.isDisplayed(), "כותרת הדף לא מוצגת במסך החדש.");
        String actualHeaderText = pageHeader.getText();
        assertTrue(actualHeaderText.contains(expectedHeaderText),
                "כותרת הדף אינה מכילה את הטקסט הצפוי. צפוי: '" + expectedHeaderText + "', בפועל: '" + actualHeaderText + "'");
    }

    public void step4_continueWithAutomatedTestsOnNewScreen(String screenName) {
        // הוסף כאן בדיקות ספציפיות למסך
    }

    @Test
    public void testManualConsoleFlowForScreen2() {
        step1_openApplicationUrlAndLoadPage();
        step2_waitForManualConsoleInputAndScreenTransition( JS_COMMAND_STEP_SCREEN_2);
        step3_verifyNewScreenHeader(EXPECTED_HEADER_TEXT_SCREEN_2);
        step4_continueWithAutomatedTestsOnNewScreen("פרטים אישיים");
    }

    @Test
    public void testManualConsoleFlowForScreen3() {
        step1_openApplicationUrlAndLoadPage();
        step2_waitForManualConsoleInputAndScreenTransition(JS_COMMAND_STEP_SCREEN_3 );
        step3_verifyNewScreenHeader(EXPECTED_HEADER_TEXT_SCREEN_3);
        step4_continueWithAutomatedTestsOnNewScreen("קוד אימות");
    }

    @Test
    public void testManualConsoleFlowForScreen4() {
        step1_openApplicationUrlAndLoadPage();
        // התיקון בשורה הבאה: שינוי JS_COMMAND_STEP_4 ל- JS_COMMAND_STEP_SCREEN_4
        step2_waitForManualConsoleInputAndScreenTransition(JS_COMMAND_STEP_SCREEN_4 );
        step3_verifyNewScreenHeader(EXPECTED_HEADER_TEXT_SCREEN_4);
        step4_continueWithAutomatedTestsOnNewScreen("פרטים מזהים");
    }
}