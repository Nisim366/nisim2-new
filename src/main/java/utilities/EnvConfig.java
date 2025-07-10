package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class EnvConfig  {
    private static final Properties props = new Properties();

    public static class UserData {
        public final String firstName;
        public final String lastName;
        public final String phone;
        public final String email;
        public final String birthDate;
        public final String gender;
        public final String issueDate;

        public UserData(String userPrefix) {
            this.firstName = get(userPrefix + ".firstName", "");
            this.lastName = get(userPrefix + ".lastName", "");
            this.phone = get(userPrefix + ".phone", "");
            this.email = get(userPrefix + ".email", "");
            this.birthDate = get(userPrefix + ".birthDate", "");
            this.gender = get(userPrefix + ".gender", "");
            this.issueDate = get(userPrefix + ".issueDate", "");
        }
    }

    static {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/env.properties");
            InputStreamReader reader = new InputStreamReader(input, java.nio.charset.StandardCharsets.UTF_8);
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("❌ לא ניתן לטעון את קובץ הסביבה (env.properties)", e);
        }
    }


    // שימוש כללי עם fallback – כולל לוג לזיהוי
    public static String get(String key, String fallback) {
        String value = props.getProperty(key);
        if (value == null || value.isEmpty()) {
            System.out.println("⚠️ [EnvConfig] מפתח חסר: " + key + " → הוחזר fallback: " + fallback);
            return fallback;
        }
        return value;
    }



    public static String getFirstName() {
        return get("firstName", "ברירתמחדל");
    }

    public static String getLastName() {
        return get("lastName", "ברירתמחדל");
    }

    public static String getPhone() {
        return get("phone", "0500000000");
    }

    public static String getEmail() {
        return get("email", "test@example.com");
    }

    public static String getLoanAmount() {
        return get("loanAmount", "50000");
    }

    public static String getEmploymentStatus() {
        return get("employmentStatus", "לא הוזן_סטטוס_תעסוקתי");
    }
}
