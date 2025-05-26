package QADEMO.tests.part3.elements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import QADEMO.Base.BaseTest_QADEMO;

public class LinksTest extends BaseTest_QADEMO {

    @Test
    public void testErrorLink() {
        try {
            var linksPage = homePage.goToElements().clickLinks();
            linksPage.clickBadRequestLink();
            String actualResponse = linksPage.getErrorResponse();

            assertTrue(actualResponse.contains("Bad Request") &&
                            (actualResponse.contains("400")),
                    "Test failed: 'Bad Request' not found in the response");
        } catch (Exception e) {
            fail("Exception occurred in testErrorLink: " + e.getMessage());
        }
    }

    @Test
    public void testCreateLink() {
        try {
            var linksPage = homePage.goToElements().clickLinks();
            linksPage.clickCreatedLink();
            String actualResponse = linksPage.getCreatedResponse();

            assertTrue(actualResponse.contains("Created") &&
                            (actualResponse.contains("201")),
                    "Test failed: 'Created' not found in the response");
        } catch (Exception e) {
            fail("Exception occurred in testCreateLink: " + e.getMessage());
        }
    }
}
