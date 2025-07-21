package Generic_product.Pages.Nineteenth_screen;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Nineteenthscreen  extends Generic_BasePage {

    private final By pageHeader = By.id("page-header");


    public Nineteenthscreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnNineteenthScreen() {
        try {
            Thread.sleep(10000);

            String actualText = customWait(90)
                    .until(ExpectedConditions.visibilityOfElementLocated(pageHeader))
                    .getText();
            return actualText.contains("נצטרך לבצע בדיקה נוספת");
        } catch (Exception e) {
            return false;
        }
    }



}
