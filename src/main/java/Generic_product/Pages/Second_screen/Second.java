package Generic_product.Pages.Second_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.JavaScriptUtility;

public class Second extends Generic_BasePage {
    private JavaScriptUtility js;
    private final By headerSecondPage = By.xpath("//h1[@id='page-header' and contains(text(),'נתחיל בכמה פרטים אישיים')]");
    private final By continueButton = By.cssSelector("button[data-testid='continue-button']");

    public Second(WebDriver driver) {
        super(driver);
        js = new JavaScriptUtility(driver);
    }

    public boolean isOnSecondPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(headerSecondPage));
            String text = getText(headerSecondPage);
            return text.contains("נתחיל בכמה פרטים אישיים");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        } catch (Exception e) {
            System.out.println("Regular click failed. Trying with JavaScript...");
            js.clickElementWithJS(continueButton); // ✅ קיים ומכיל חיכוי + scroll
        }
    }



    // כאן קוראים למתודה הגנרית מהבסיס ללחיצה על כפתור Back
    public void clickBackButton() {
        super.clickBackButton();
    }
}
