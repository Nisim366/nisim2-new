package demo.qa.Widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class practiceWidgetsPage extends WidgetsPage {

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private By standartMultySelect = By.id("cars");

    public practiceWidgetsPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public By optionLocator(String value) {
        return By.xpath("//select[@id='cars']/option[@value='"+value+"']");
    }

    private Select getSelect() {
        return new Select(find(standartMultySelect));
    }

    public void selectStandartMultiByString(String text) {
        jsUtil.waitForElementVisibility(standartMultySelect);
        getSelect().selectByVisibleText(text);
        System.out.println("Selected option: " + text);
    }

    public void selectStandartMultiByInt(int index) {
        jsUtil.waitForElementVisibility(standartMultySelect);
        getSelect().selectByIndex(index);
        System.out.println("Selected option at index: " + index);
    }

    // מתודה זו משתמשת ב-By locator כדי לבחור אופציה לפי הטקסט הנראה
    public void selectOptionByVisibleText(By locator, String value) {
        WebElement selectElement = find(locator);
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
    }

    public List<String> getAllSelectedOptions() {
        List<WebElement> allSelectedOptions = getSelect().getAllSelectedOptions();
        List<String> selectedTexts = new ArrayList<>();

        for (WebElement option : allSelectedOptions) {
            selectedTexts.add(option.getText());
        }
        System.out.println("Currently selected options: " + selectedTexts);
        return selectedTexts;
    }
    public void deselectByIndex(int index) {
        jsUtil.waitForElementVisibility(standartMultySelect);
        getSelect().deselectByIndex(index);
        System.out.println("Deselected option at index: " + index);
    }


}
