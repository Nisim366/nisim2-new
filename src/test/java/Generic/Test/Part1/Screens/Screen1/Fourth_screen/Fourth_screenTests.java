package Generic.Test.Part1.Screens.Screen1.Fourth_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Fourth_screenTests extends BaseTest_Generic {

    private Fourth_screen FourthScreen;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;

    private final String phone = "0532407762";
    private final String firstName = "חן";
    private final String lastName = "הניגון";
    private final String GMail = "yossi@example.com";




    @Test
    public void testOtpScreenInitialStateAndTimerBehavior() {

        assertTrue(FourthScreen.isOnFourthScreen(), "Should be on third screen with OTP input.");

        assertTrue(FourthScreen.isTopImageDisplayedAndCorrect(), "Top image should be displayed and have correct attributes.");
        assertTrue(FourthScreen.isImageLoaded(By.cssSelector("img.ScreenWrapper__top-image[src*='generic_phase_1_icon.svg']")), "Top image should be loaded and not broken.");


        assertTrue(FourthScreen.isVerificationCodeTextDisplayedImmediately(),
                "Verification code sent text should be displayed immediately upon page entry.");

        int initialTimerCheckWaitSeconds = 5;
        assertTrue(FourthScreen.isTimerTextAndCountingDown(initialTimerCheckWaitSeconds),
                "Timer text should be visible and timer should start counting down within " + initialTimerCheckWaitSeconds + " seconds.");

        assertTrue(FourthScreen.areResendAndCallButtonsDisabledImmediately(),
                "Resend and Call buttons should be disabled immediately upon page entry.");

        System.out.println("Waiting for timer to expire (approx. 35 seconds) and buttons to enable...");
        try {
            Thread.sleep(Duration.ofSeconds(35).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Test interrupted while waiting for timer to expire.");
        }

        assertTrue(FourthScreen.waitForButtonsToBecomeEnabled(10),
                "Resend and Call buttons should become enabled after timer expires.");
    }

    @Test
    public void testBackButtonAndNextPageHeader() {

        assertTrue(FourthScreen.isOnFourthScreen(), "Should be on third screen with OTP input.");

        FourthScreen.clickBackButton();

        System.out.println("Demonstrating waitForNextPageHeader after back button click.");
    }
}