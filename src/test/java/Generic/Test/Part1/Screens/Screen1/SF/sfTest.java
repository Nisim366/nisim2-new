package Generic.Test.Part1.Screens.Screen1.SF;

import Generic.Base.BaseTest_Generic;
import Generic_product.SF.sf;
import org.junit.jupiter.api.Test;

public class sfTest  extends BaseTest_Generic {

    @Test
    public void testClickFirstFailedRuleButton() {
        sf sfPage = new sf(driver); // יצירת מופע של הדף

        // ניסיון ללחוץ על הכפתור הראשון
        sfPage.waitForFailedRuleSection();

        // פעולה לבדיקה
        sfPage.clickFirstFailedRuleButton();

        // כאן אפשר להוסיף בדיקה אם עברת למסך הנכון, למשל:
        // Assert.assertTrue(driver.getCurrentUrl().contains("expected-part-of-url"));

        // או המתנה לאלמנט מסוים שמעיד על מעבר דף
        // WebElement header = new WebDriverWait(driver, Duration.ofSeconds(5))
        //         .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.expected-header")));
        // Assert.assertEquals("Expected Title", header.getText());

        System.out.println("🧪 הבדיקה הסתיימה – נלחץ על הכפתור הראשון");
    }

}
