package demo.qa;

import demo.qa.alerts_frames_windows.Alert_Frames_WindowssPage;
import demo.qa.base.BasePage;
import demo.qa.elements.ElementsPage;
import demo.qa.Widgets.WidgetsPage;
import demo.qa.forms.FormsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.JavaScriptUtility;

public class HomePage extends BasePage {

    private JavaScriptUtility jsUtil;

    public HomePage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver);
    }

    private final By formsCard = By.xpath("//div[@id='app']//h5[text()='Forms']");
    private final By elementsCard = By.xpath("//div[@id='app']//h5[text()='Elements']");
    private final  By WidgetsManueItemCard = By.xpath("//div[@id='app']//h5[text()='Widgets']");
    private  final By alertsFrameWindowsCard = By.xpath("//div[@id='app']//h5[contains(text(),'Alerts')]");


    public FormsPage goToFormsPage() {
        jsUtil.scrollToElementJS(formsCard);
        click(formsCard);
        return new FormsPage(driver);
    }

    public ElementsPage goToElements() {
        jsUtil.scrollToElementJS(elementsCard);
        click(elementsCard);
        return new ElementsPage(driver);
    }
    public WidgetsPage goToWidgets(){
        jsUtil.scrollToElementJS(WidgetsManueItemCard);
        click(WidgetsManueItemCard);
        return  new WidgetsPage(driver);
    }
    public Alert_Frames_WindowssPage goToAlertsFrameWindowsCard(){
        jsUtil.scrollToElementJS(alertsFrameWindowsCard);
        click(alertsFrameWindowsCard);
        return  new Alert_Frames_WindowssPage(driver);
    }


}
