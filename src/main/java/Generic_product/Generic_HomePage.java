package Generic_product;

import Generic_product.Base.Generic_BasePage;
import Generic_product.Pages.First_screen.First;
import org.openqa.selenium.WebDriver;
import utilities.JavaScriptUtility;


public class Generic_HomePage extends Generic_BasePage {

    private JavaScriptUtility jsUtil;
    private JavaScriptUtility js;

    public Generic_HomePage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        js = new JavaScriptUtility(driver);

    }

    public First goToPractice() {
        return new First(driver);
    }
}
