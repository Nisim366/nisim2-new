package Morning.Tests.FirstScreen;

import Morning.Base.Morning_BaseTest;
import Morning.Processes.Sole.First_screen;
import Morning.Processes.Sole.Second_screen;
import Morning.Processes.Sole.Third_screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecondScreenTest extends Morning_BaseTest {

    private First_screen firstPage;
    private Second_screen secondPage;
    private Third_screen thirdpage;

    @BeforeEach
    public void setUp() {
        super.setUp(); // 🟢 חייב! כדי לאתחל את driver כמו שצריך

        firstPage = new First_screen(driver);
        secondPage = new Second_screen(driver);

        boolean alreadyOnSecond = secondPage.isOnSecondPage(); // ⬅️ בודק אם חזרתי לתהליך

        handleWelcomeBackPopupIfExists(alreadyOnSecond); // ⬅️ לוחץ אם צריך

        if (!alreadyOnSecond) {
            secondPage.goToThirdScreen(); // ⬅️ נווט רגיל רק אם לא חזרתי
        }
    }


    @Test
    public void testGoToSecondScreen() {


    }
}
