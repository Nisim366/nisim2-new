package demo.qa.Widgets;

import demo.qa.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.JavaScriptUtility;

import java.time.Duration;

public class WidgetsPage extends HomePage {

    private WebDriverWait wait;
    private JavaScriptUtility jsUtil;
    private final By SelectMenuItem = By.xpath("//li[@id='item-8']//span[text()='Select Menu']");
    private final By datePickerMenuItem = By.xpath("//li[@id='item-2']//span[text()='Date Picker']");
    private  final  By progressBarMenueItem = By.xpath("//li[@id='item-4']/span[text()='Progress Bar']");
    private final By sliderMenueItem = By.xpath("//li[@id='item-3']/span[text()='Slider']");

    public WidgetsPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public practiceWidgetsPage clickSelectMenu() {
        jsUtil.scrollToElementJS(SelectMenuItem);
        click(SelectMenuItem);
        return new practiceWidgetsPage(driver); // פועל לאחר המעבר לדף selectMenuItemPage
    }
    public DatePickerMneuPage clickDatePicker(){
        jsUtil.scrollToElementJS(datePickerMenuItem);
        click(datePickerMenuItem);
        return new DatePickerMneuPage(driver);
    }
    public ProgressBarPage clickBarPage(){
        jsUtil.scrollToElementJS(progressBarMenueItem);
        click(progressBarMenueItem);
        return new ProgressBarPage (driver);
    }
//public SliderMenuePage clickSlider(){
//    jsUtil.scrollToElementJS(sliderMenueItem);
//    click(sliderMenueItem);
//    return new SliderMenuePage (driver);
//}



}
