package utilities;

import java.util.Random;

public class EmailGenerator {
    private static final String BASE_DATE = "02/02/2020";

    public static String generateUniqueEmail() {
        String formattedDate = BASE_DATE.replace("/", ""); // ← להסרה מהמייל בלבד
        int randomSuffix = generateSixDigitRandomNumber();
        return "nisimb+" + formattedDate + "-" + randomSuffix + "@greenlend.co.il";
    }

    private static int generateSixDigitRandomNumber() {
        return 100000 + new Random().nextInt(900000); // מספר בין 100000 ל־999999
    }

    public static String getOriginalBaseDate() {
        return BASE_DATE;
    }
}
