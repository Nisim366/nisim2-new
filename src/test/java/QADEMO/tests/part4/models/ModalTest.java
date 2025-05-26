package QADEMO.tests.part4.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import QADEMO.Base.BaseTest_QADEMO;

public class ModalTest extends BaseTest_QADEMO {

    @Test
    public void testModelDialog (){
        var obj = homePage.goToAlertsFrameWindowsCard().clickModalDialog();
        obj.clickSmallModalButtom();
        String actualText = obj.getSmallModalText();
        Assertions.assertTrue(actualText.contains("small modal."),"the massage not contains small modal");
        System.out.println("the massage contains  - small modal");
        obj.clickCloseButton();
    }
}
