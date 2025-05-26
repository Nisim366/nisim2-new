package greenlend.Tests.Part1;

import greenlend.Base.BaseTest_greenlend;
import greenlend.GHomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest extends BaseTest_greenlend {

    @Test
    public void loginFlow() {
        String email = "nisimb@greenlend.co.il";
        String password = "Hilasabaniss6_";

        var loginPage = homePage_greenlend.ToLogin();

        loginPage.fillEmailField(email);
        loginPage.doubleClickNextWithDelay();
        loginPage.clickContinueWithAtlassian();
        loginPage.clickContinueSecond();
        loginPage.fillPasswordFieldWhenFocused(password);
        loginPage.clickLoginButton();

        // המתנה ובדיקת הכתובת URL לוודא שהכניסה הצליחה
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean urlContainsPortal = wait.until(ExpectedConditions.urlContains("servicedesk"));

        Assertions.assertTrue(urlContainsPortal, "Login flow did not complete successfully");
    }
}
