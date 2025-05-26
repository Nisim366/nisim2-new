package demo.qa.forms;

import demo.qa.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.JavaScriptUtility;

public class FormsPage extends HomePage {

    private By practiceFormMenuItems = By.xpath("//li[@id='item-0']/span[text()='Practice Form']");
    private JavaScriptUtility jsUtil;

    public FormsPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
    }
    public PracticeFormPage clickPracticeFormPage() {
        jsUtil.clickElementWithJS(practiceFormMenuItems);
        return new PracticeFormPage(getDriver());
    }
}
