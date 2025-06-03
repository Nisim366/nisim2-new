package Generic_product.Pages.Second;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.WebDriver;
import utilities.JavaScriptUtility;

public class First_lastName extends Generic_BasePage {
    private JavaScriptUtility js;

    public First_lastName(WebDriver driver) {
        super(driver);
        js = new JavaScriptUtility(driver);
    }

}
