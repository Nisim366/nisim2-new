package Generic_product.Pages.Second_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.JavaScriptUtility;

public class Second extends Generic_BasePage {
    private JavaScriptUtility js;
    private final By headerSecondPage = By.xpath("//h1[@id='page-header' and contains(text(),'נתחיל בכמה פרטים אישיים')]");


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
}
