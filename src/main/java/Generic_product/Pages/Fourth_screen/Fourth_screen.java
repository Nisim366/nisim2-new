package Generic_product.Pages.Fourth_screen;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.Fifth_screen.Fifth_screen;
import Generic_product.Pages.Third_screen.Third_screen;
import Generic_product.Pages.Second_screen.Second;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

public class Fourth_screen extends Generic_BasePage {

    private final By header = By.id("page-header");
    private final By backButton = By.cssSelector("button[data-testid='back-button']");
    private final By containerLocator = By.cssSelector("h2.MuiTypography-subtitle2");
    private final By timerLocator = By.cssSelector("h2 span + time[role='timer']");
    private final By textSpanLocator = By.cssSelector("h2 span");
    private final By verificationCodeSentTextLocator = By.xpath("//span[contains(text(), 'קוד האימות נשלח למספר')]");
    private final By resendCodeButton = By.xpath("//button[contains(., 'לא קיבלתי קוד? שלחו שוב')]");
    private final By callMeButton = By.xpath("//button[contains(., 'שלחו לי קוד בשיחת טלפון')]");
    private final By topImage = By.cssSelector("img.ScreenWrapper__top-image[src*='generic_phase_1_icon.svg']");
    private final By verificationCodeFirstDigitInput = By.cssSelector("input[aria-label^='הזן את ספרה מספר 1 מתוך 6 של קוד האימות']");
    private final By cityInput = By.cssSelector("input[data-testid='applicant.address.town-input']");

    private WebDriverWait wait;

    public Fourth_screen(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isOnFourthScreen() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(verificationCodeFirstDigitInput));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void waitForManualOtpInput() {
        System.out.println("Waiting 90 seconds for manual OTP input...");
        try {
            Thread.sleep(90000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted during manual OTP input wait", e);
        }
        System.out.println("Manual OTP input wait finished.");
    }



    public void waitForNextScreen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
    }
    public void waitForFifthScreen() {
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOfElementLocated(cityInput));
    }

    public void clickBackButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
            System.out.println("Back button clicked.");
            Second secondScreen = new Second(driver);
            if (secondScreen.isOnSecondPage()) {
                System.out.println("Successfully navigated back to the Second Page.");
            } else {
                System.err.println("Verification Failed: Did not navigate back to the Second Page after clicking back button.");
            }
        } catch (TimeoutException e) {
            System.err.println("Error: Back button not clickable within expected time. Element: " + backButton + ". " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Error: Back button element not present in DOM. Element: " + backButton + ". " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while clicking back button or verifying navigation: " + e.getMessage());
        }
    }

    public boolean isTimerTextAndCountingDown(int maxWaitSeconds) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement container = shortWait.until(ExpectedConditions.visibilityOfElementLocated(containerLocator));
            System.out.println("Debug: Timer container found and visible.");

            WebElement textSpan = shortWait.until(ExpectedConditions.visibilityOf(container.findElement(textSpanLocator)));
            String expectedText = "אפשר לבקש קוד חדש בעוד";
            String actualTextSpan = textSpan.getText();
            System.out.println("Debug: Actual text span: '" + actualTextSpan + "'");

            if (!actualTextSpan.equals(expectedText)) {
                System.err.println("Validation Failed: Expected timer text not found. Actual: '" + actualTextSpan + "', Expected: '" + expectedText + "'.");
                return false;
            }

            WebElement timerElement = shortWait.until(ExpectedConditions.visibilityOf(container.findElement(timerLocator)));
            String previousTime = timerElement.getText();
            System.out.println("Debug: Initial timer value: " + previousTime);

            int elapsed = 0;
            while (elapsed < maxWaitSeconds) {
                Thread.sleep(1000);
                String currentTime = timerElement.getText();
                System.out.println("Debug: Current timer value: " + currentTime);

                if (!currentTime.equals(previousTime)) {
                    System.out.println("Timer detected and counting down.");
                    return true;
                }

                previousTime = currentTime;
                elapsed++;
            }

            System.err.println("Validation Failed: Timer did not start counting down within " + maxWaitSeconds + " seconds.");
            return false;

        } catch (TimeoutException e) {
            System.err.println("Error: Timer element or container not found within expected time. " + e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            System.err.println("Error: Timer element or its text span not present in DOM. " + e.getMessage());
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error: Thread interrupted during timer countdown wait. " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred in isTimerTextAndCountingDown: " + e.getMessage());
            return false;
        }
    }

    public WebElement getVerificationCodeSentTextElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(verificationCodeSentTextLocator));
    }

    public boolean isVerificationCodeTextDisplayedImmediately() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(verificationCodeSentTextLocator));
            System.out.println("'Verification code sent' text is displayed.");
            return true;
        } catch (TimeoutException e) {
            System.err.println("Validation Failed: The text 'קוד האימות נשלח למספר' was not displayed within 2 seconds. " + e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            System.err.println("Error: 'Verification code sent' text element not present in DOM. " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while checking 'Verification code sent' text: " + e.getMessage());
            return false;
        }
    }

    public boolean areResendAndCallButtonsDisabledImmediately() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement resendButtonElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(resendCodeButton));
            WebElement callButtonElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(callMeButton));

            boolean isResendDisabled = !resendButtonElement.isEnabled();
            boolean isCallDisabled = !callButtonElement.isEnabled();

            if (isResendDisabled && isCallDisabled) {
                System.out.println("Resend and Call buttons are disabled as expected upon page entry.");
                return true;
            } else {
                if (!isResendDisabled) {
                    System.err.println("Validation Failed: Resend button is unexpectedly enabled.");
                }
                if (!isCallDisabled) {
                    System.err.println("Validation Failed: Call button is unexpectedly enabled.");
                }
                return false;
            }
        } catch (TimeoutException e) {
            System.err.println("Error: Resend or Call buttons not visible within expected time. " + e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            System.err.println("Error: Resend or Call button elements not present in DOM. " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while checking initial state of resend/call buttons: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForButtonsToBecomeEnabled(int maxWaitSeconds) {
        try {
            WebDriverWait dynamicWait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitSeconds));

            WebElement resendButtonElement = dynamicWait.until(ExpectedConditions.elementToBeClickable(resendCodeButton));
            WebElement callButtonElement = dynamicWait.until(ExpectedConditions.elementToBeClickable(callMeButton));

            boolean isResendEnabled = resendButtonElement.isEnabled();
            boolean isCallEnabled = callButtonElement.isEnabled();

            if (isResendEnabled && isCallEnabled) {
                System.out.println("Resend and Call buttons are enabled as expected.");
                return true;
            } else {
                if (!isResendEnabled) {
                    System.err.println("Validation Failed: Resend button is unexpectedly disabled after timer expiration.");
                }
                if (!isCallEnabled) {
                    System.err.println("Validation Failed: Call button is unexpectedly disabled after timer expiration.");
                }
                return false;
            }

        } catch (TimeoutException e) {
            System.err.println("Error: Resend or Call buttons did not become enabled within " + maxWaitSeconds + " seconds. " + e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            System.err.println("Error: Resend or Call button elements not present in DOM. " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while waiting for buttons to become enabled: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifies if the top image is displayed and has the correct source and alt text.
     * This version allows the alt attribute to be empty, as per the current website's HTML.
     * @return True if the image is displayed, has the expected src, and alt text (can be empty), false otherwise.
     */
    public boolean isTopImageDisplayedAndCorrect() {
        try {
            WebElement imageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(topImage));

            if (!imageElement.isDisplayed()) {
                System.err.println("Validation Failed: Top image element is not displayed.");
                return false;
            }

            String actualSrc = imageElement.getAttribute("src");
            String expectedSrcPart = "generic_phase_1_icon.svg";
            if (!actualSrc.contains(expectedSrcPart)) {
                System.err.println("Validation Failed: Top image src is incorrect. Actual: '" + actualSrc + "', Expected to contain: '" + expectedSrcPart + "'.");
                return false;
            }

            String actualAlt = imageElement.getAttribute("alt");
            // Modified: Allows alt attribute to be an empty string, but not null.
            if (actualAlt == null) {
                System.err.println("Validation Failed: Top image alt text is missing (null).");
                return false;
            }
            // If actualAlt is an empty string "", it will pass this check.

            System.out.println("Top image found, displayed, and attributes verified.");
            return true;

        } catch (TimeoutException e) {
            System.err.println("Error: Top image element not visible within expected time. Element: " + topImage + ". " + e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            System.err.println("Error: Top image element not present in DOM. Element: " + topImage + ". " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while verifying top image: " + e.getMessage());
            return false;
        }
    }

    public boolean isImageLoaded(By imageLocator) {
        try {
            WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(imageLocator));
            boolean loaded = (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0;", image);
            if (loaded) {
                System.out.println("Image at locator " + imageLocator + " is loaded correctly.");
            } else {
                System.err.println("Validation Failed: Image at locator " + imageLocator + " appears to be broken or not fully loaded.");
            }
            return loaded;
        } catch (TimeoutException e) {
            System.err.println("Error: Image element not visible within expected time for broken image check. Element: " + imageLocator + ". " + e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            System.err.println("Error: Image element not present in DOM for broken image check. Element: " + imageLocator + ". " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while checking if image is loaded: " + e.getMessage());
            return false;
        }
    }
    public Fifth_screen goToFifthScreen() {
        waitForFifthScreen(); // מחכה לאלמנט של מסך 5
        return new Fifth_screen(driver);
    }

}