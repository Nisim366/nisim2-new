package Generic.Test.Part1.Screens.Screen1.Second_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Screen2 extends BaseTest_Generic {

    private First firstPage;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;
    private Third_screen thirdPage;
    private final String phone = "0532407762";
    private  final  String  firstName = "חן";
    private  final  String  lastName = "הניגון";
    private  final  String GMail = "yossi@example.com";



    // מתודה שמבצעת ניווט מהמסך הראשון לשני ומחזירה את האובייקט של המסך השני (Second)
    private Second navigateToSecondPage() {
        firstPage = homePage.goToPractice();

        if (!firstPage.isCheckboxSelected()) {
            firstPage.clickCheckbox();
        }
        firstPage.clickContinueButton();

        secondPage = new Second(driver);
        Assertions.assertTrue(secondPage.isOnSecondPage(), "לא הגעת למסך השני בהצלחה");

        // יצירת מופעים של שדות במסך השני
        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);

        return secondPage;
    }

    @BeforeEach
    public void setup() {
        secondPage = navigateToSecondPage();
    }

    @Test
    public void testFillAllFieldsOnSecondScreenAndContinueToThird() {
        // הזנת ערכים בשדות במסך השני
        firstLastName.setFirstName(firstName);
        firstLastName.setLastName(lastName);

        phoneField.setPhoneInput(phone);

        emailFields.setEmail(GMail);
        emailFields.setEmailConfirmation(GMail);

        // בדיקות שהשדות הוזנו כראוי
        Assertions.assertEquals(firstName, firstLastName.getFirstName());
        Assertions.assertEquals(lastName, firstLastName.getLastName());
        Assertions.assertEquals(phoneField.normalizePhoneForComparison(phone), phoneField.getNormalizedPhoneValue());
        Assertions.assertEquals(GMail, emailFields.getEmail());
        Assertions.assertEquals(GMail, emailFields.getEmailConfirmation());

        // מעבר למסך השלישי
        secondPage.clickContinueButton();

        // כאן מוסיפים WebDriverWait עד לכותרת של העמוד השלישי
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("page-header"), "מה קוד האימות שקיבלת"));

        // יצירת מופע של המסך השלישי ובדיקת הגעה אליו
        thirdPage = new Third_screen(driver);

        Assertions.assertTrue(thirdPage.isOnThirdPage(), "לא הגעת למסך השלישי");
    }
}
