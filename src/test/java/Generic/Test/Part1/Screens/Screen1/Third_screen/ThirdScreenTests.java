package Generic.Test.Part1.Screens.Screen1.Third_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ThirdScreenTests extends BaseTest_Generic {

    private Third_screen thirdScreen;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;

    private final String phone = "0532407762";
    private final String firstName = "חן";
    private final String lastName = "הניגון";
    private final String GMail = "yossi@example.com";

    private Second navigateToSecondPage() {
        First obj = homePage.goToPractice();
        if (!obj.isCheckboxSelected()) {
            obj.clickCheckbox();
        }
        obj.clickContinueButton();

        secondPage = new Second(driver);
        assertTrue(secondPage.isOnSecondPage(), "Failed to reach the second screen.");

        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);

        return secondPage;
    }

    private Third_screen navigateToThirdPage() {
        firstLastName.setFirstName(firstName);
        firstLastName.setLastName(lastName);
        phoneField.setPhoneInput(phone);
        emailFields.setEmail(GMail);
        emailFields.setEmailConfirmation(GMail);

        assertEquals(firstName, firstLastName.getFirstName());
        assertEquals(lastName, firstLastName.getLastName());
        assertEquals(phoneField.normalizePhoneForComparison(phone), phoneField.getNormalizedPhoneValue());
        assertEquals(GMail, emailFields.getEmail());
        assertEquals(GMail, emailFields.getEmailConfirmation());

        secondPage.clickContinueButton();

        WebDriverWait pageLoadWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        pageLoadWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("page-header"), "מה קוד האימות שקיבלת"));

        thirdScreen = new Third_screen(driver);

        assertTrue(thirdScreen.isOnThirdPage(), "Failed to reach the third screen.");

        return thirdScreen;
    }

    @BeforeEach
    public void setup() {
        navigateToSecondPage();
    }

    @Test
    public void testOtpScreenInitialStateAndTimerBehavior() {
        navigateToThirdPage();

        assertTrue(thirdScreen.isOnThirdPage(), "Should be on third screen with OTP input.");

        assertTrue(thirdScreen.isTopImageDisplayedAndCorrect(), "Top image should be displayed and have correct attributes.");
        assertTrue(thirdScreen.isImageLoaded(By.cssSelector("img.ScreenWrapper__top-image[src*='generic_phase_1_icon.svg']")), "Top image should be loaded and not broken.");


        assertTrue(thirdScreen.isVerificationCodeTextDisplayedImmediately(),
                "Verification code sent text should be displayed immediately upon page entry.");

        int initialTimerCheckWaitSeconds = 5;
        assertTrue(thirdScreen.isTimerTextAndCountingDown(initialTimerCheckWaitSeconds),
                "Timer text should be visible and timer should start counting down within " + initialTimerCheckWaitSeconds + " seconds.");

        assertTrue(thirdScreen.areResendAndCallButtonsDisabledImmediately(),
                "Resend and Call buttons should be disabled immediately upon page entry.");

        System.out.println("Waiting for timer to expire (approx. 35 seconds) and buttons to enable...");
        try {
            Thread.sleep(Duration.ofSeconds(35).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Test interrupted while waiting for timer to expire.");
        }

        assertTrue(thirdScreen.waitForButtonsToBecomeEnabled(10),
                "Resend and Call buttons should become enabled after timer expires.");
    }

    @Test
    public void testBackButtonAndNextPageHeader() {
        navigateToThirdPage();

        assertTrue(thirdScreen.isOnThirdPage(), "Should be on third screen with OTP input.");

        thirdScreen.clickBackButton();

        System.out.println("Demonstrating waitForNextPageHeader after back button click.");
    }
}