package utilities;

import org.openqa.selenium.WebDriver;

public class Utility {

    protected static WebDriver driver;

    // אתחול ה-Driver
    public Utility(WebDriver driver) {
        this.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
