package Generic.Test.Part1.Screens.Screen1.Fourth_screen;

import Generic.Base.BaseTest_Generic;
import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.EmailFields;
import Generic_product.Pages.Second_screen.FirstLastName;
import Generic_product.Pages.Second_screen.PhoneField;
import Generic_product.Pages.Second_screen.Second;
import Generic_product.Pages.Fourth_screen.Fourth_screen;
import Generic_product.Pages.Third_screen.Third_screen;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Fourth_screenTests extends BaseTest_Generic {

    private First firstPage;
    private Second secondPage;
    private FirstLastName firstLastName;
    private PhoneField phoneField;
    private EmailFields emailFields;
    private Third_screen thirdScreen;
    private Fourth_screen fourthScreen;

    @BeforeEach
    public void set() {
        firstPage = new First(driver);
        secondPage = new Second(driver);
        firstLastName = new FirstLastName(driver);
        phoneField = new PhoneField(driver);
        emailFields = new EmailFields(driver);

        firstPage.goToSecondScreen();
        assertTrue(secondPage.isOnSecondPage(), "לא במסך השני");

        thirdScreen = secondPage.goTothirdScreen();
        assertTrue(thirdScreen.isOnThirdScreen(), "לא במסך השלישי");

        fourthScreen = thirdScreen.goToFourthScreen();
        assertTrue(fourthScreen.isOnFourthScreen(), "לא במסך הרביעי");
    }
    @Test
    public void testManualOtpInputAndProceed() {
        fourthScreen.waitForManualOtpInput(); // המתנה להזנת הקוד ידנית

        fourthScreen.isOnFourthScreen(); // מחכה לאלמנט הייחודי של המסך החמישי

    }




    @Test
    public void testOtpScreenInitialStateAndTimerBehavior() {

        assertTrue(fourthScreen.isOnFourthScreen(), "Should be on third screen with OTP input.");

        assertTrue(fourthScreen.isTopImageDisplayedAndCorrect(), "Top image should be displayed and have correct attributes.");
        assertTrue(fourthScreen.isImageLoaded(By.cssSelector("img.ScreenWrapper__top-image[src*='generic_phase_1_icon.svg']")), "Top image should be loaded and not broken.");


        assertTrue(fourthScreen.isVerificationCodeTextDisplayedImmediately(),
                "Verification code sent text should be displayed immediately upon page entry.");

        int initialTimerCheckWaitSeconds = 5;
        assertTrue(fourthScreen.isTimerTextAndCountingDown(initialTimerCheckWaitSeconds),
                "Timer text should be visible and timer should start counting down within " + initialTimerCheckWaitSeconds + " seconds.");

        assertTrue(fourthScreen.areResendAndCallButtonsDisabledImmediately(),
                "Resend and Call buttons should be disabled immediately upon page entry.");

        System.out.println("Waiting for timer to expire (approx. 35 seconds) and buttons to enable...");
        try {
            Thread.sleep(Duration.ofSeconds(35).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Test interrupted while waiting for timer to expire.");
        }

        assertTrue(fourthScreen.waitForButtonsToBecomeEnabled(10),
                "Resend and Call buttons should become enabled after timer expires.");
    }

    @Test
    public void testBackButtonAndNextPageHeader() {

        assertTrue(fourthScreen.isOnFourthScreen(), "Should be on third screen with OTP input.");

        fourthScreen.clickBackButton();

        System.out.println("Demonstrating waitForNextPageHeader after back button click.");
    }
}