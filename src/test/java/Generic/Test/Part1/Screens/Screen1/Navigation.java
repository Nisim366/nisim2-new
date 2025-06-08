package Generic.Test.Part1.Screens.Screen1;

import Generic.Base.BaseTest_Generic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.DevToolsHelper;

public class Navigation extends BaseTest_Generic {

    public static String targetScreen = "contactDetailsGeneric";
    private DevToolsHelper devTools;

    @BeforeEach
    public void setUp() {
        super.setUp();
        devTools = new DevToolsHelper(driver);
    }

    @Test
    public void openDevToolsAndJumpToTargetScreen() {
        // פותחים את DevTools ועוברים ל־Console
        devTools.openDevToolsAndGoToConsole();


        // מדלגים למסך הרצוי
        System.out.println("Jumping to screen: " + targetScreen);
        devTools.jumpToScreen(targetScreen);
    }
}
