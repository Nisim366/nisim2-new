package Morning.Tests.FirstScreen;

import Generic_product.Pages.First_screen.First;
import Generic_product.Pages.Second_screen.Second;
import Morning.Base.Morning_BaseTest;
import Morning.Processes.Sole.First_screen;
import Morning.Processes.Sole.Second_screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstScreenTest extends Morning_BaseTest {

    private First_screen firstPage;
    private  Second_screen secondPage;

    @BeforeEach
    public void setUp() {
        firstPage = new First_screen(driver);
        handleWelcomeBackPopupIfExists(false); // false כי עדיין לא בדקתי איפה אני

        secondPage = new Second_screen(driver);
    }

    @Test
    public void testGoToSecondScreen() {
        Second_screen seondPage = firstPage.goToSecondScreen();
        assertTrue(seondPage.isOnSecondPage(), "❌ לא במסך השני");
    }


}
