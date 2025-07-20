package Generic_product.Pages.Twentieth_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Twentiethscreen extends Generic_BasePage {

    private final By welcomeBackButton = By.cssSelector("[data-testid='onboarding-close-welcome-back-dialog']");
    private final By closeIconButton = By.cssSelector("[data-testid='CloseIcon']");

    public Twentiethscreen(WebDriver driver) {
        super(driver);
    }





}
