package Generic_product.SF;

import Generic_product.Base.Generic_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.JavaScriptUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class sf extends Generic_BasePage {

    By buttonsLocator = By.cssSelector(".rule-container > button");


    private final JavaScriptUtility js;


    public sf(WebDriver driver) {
        super(driver);
        js = new JavaScriptUtility(driver);

    }
    public void waitForFailedRuleSection() {
        customWait(60).until(ExpectedConditions.presenceOfElementLocated(buttonsLocator));
    }

    public List<WebElement> getFailedRuleButtons() {
        try {
            By locator = By.cssSelector("div.rule-container > button.slds-button");
            js.scrollToElementJS(locator); // ניסיון לגלול
            Thread.sleep(1000); // אפשר גם להסיר אחרי בדיקה
            return customWait(10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            System.out.println("❌ לא נמצאו כפתורים של חוקים. ייתכן שהמסך לא נטען או שהסלקטור שגוי.");
            return new ArrayList<>();
        }
    }



    public void clickFirstFailedRuleButton() {
        clickFailedRuleButtonByIndex(0);
    }

    public void clickSecondFailedRuleButton() {
        clickFailedRuleButtonByIndex(1);
    }

    public void clickThirdFailedRuleButton() {
        clickFailedRuleButtonByIndex(2);
    }

    public void clickFourthFailedRuleButton() {
        clickFailedRuleButtonByIndex(3);
    }

    private void clickFailedRuleButtonByIndex(int index) {
        List<WebElement> buttons = getFailedRuleButtons();
        if (buttons.size() > index) {
            WebElement button = buttons.get(index);
            js.scrollToElementJS(button);
            js.clickElementWithJS(button);
            System.out.println("✅ בוצעה לחיצה על כפתור מספר " + (index + 1));
        } else {
            System.out.println("⚠️ לא קיים כפתור מספר " + (index + 1) + " – קיימים רק " + buttons.size());
        }
    }








    public void clickOnCustomerByName(String customerName) {
        // שלב 1: מצא את כל שורות הטבלה
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

        // שלב 2: עבור על כל שורה בטבלה
        for (WebElement row : rows) {
            try {
                // שלב 3: מצא את האלמנט עם טקסט של שם הלקוח
                By locator = By.xpath(".//span[normalize-space(text())='" + customerName + "']");
                WebElement customerLink = row.findElement(locator);

                // שלח את WebElement, לא את By
                js.scrollToElementJS(customerLink);
                js.clickElementWithJS(customerLink);

                System.out.println("✅ נמצא ולחצנו על הלקוח: " + customerName);
                return;

            } catch (NoSuchElementException e) {
                // ממשיכים לשורה הבאה
            }
        }


        // אם לא מצאנו את הלקוח בכלל – זורקים שגיאה
        throw new NoSuchElementException("❌ לא נמצא לקוח בשם: " + customerName);
    }

}
