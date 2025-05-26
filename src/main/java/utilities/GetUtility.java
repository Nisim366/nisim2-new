package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class GetUtility extends Utility {

    private WebDriver driver;

    public GetUtility(WebDriver driver) {
        super(driver);
    }

    public static String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public static Set<String> getWindowHandles(WebDriver driver) {
        return driver.getWindowHandles();
    }
    public static String getURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }
}
