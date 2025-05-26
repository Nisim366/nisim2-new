package demo.qa.elements;

import demo.qa.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.JavaScriptUtility;

public class ElementsPage extends HomePage {

    private JavaScriptUtility jsUtil; // משתנה לאובייקט JavaScriptUtility

    private final By webTabelsMenuItem = By.xpath("//li[@id='item-3'] //span[text()='Web Tables']");
    private final By linksManueItem = By.xpath("//li[@id='item-5']/span[text()='Links']");
    private  final  By dynamicPropertiesMenuItem = By.xpath("//li[@id='item-8']/span[text()='Dynamic Properties']");

    public ElementsPage(WebDriver driver) {
        super(driver);
        jsUtil = new JavaScriptUtility(driver); // אתחול אובייקט של JavaScriptUtility עם ה-driver

    }

    public webTabelsPage clickWebTables(){
        click(webTabelsMenuItem);
        return new webTabelsPage(driver);
    }
    public LinksPage clickLinks(){
        click(linksManueItem);
        return  new LinksPage(driver);
    }
    public DynamicPropertiesPage clickDynamicProperties() {
        jsUtil.waitForElementVisibility(dynamicPropertiesMenuItem);
        click(dynamicPropertiesMenuItem);
        return new DynamicPropertiesPage(driver);
    }



}
