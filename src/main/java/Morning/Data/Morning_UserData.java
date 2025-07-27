package Morning.Data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Morning_UserData {

    public final IdCardInfo idCard;

    public Morning_UserData(String userName) {
        try {
            Properties props = new Properties();

            InputStream input = getClass().getClassLoader().getResourceAsStream("users/" + userName + ".properties");
            if (input == null) {
                throw new RuntimeException("❌ קובץ המשתמש לא נמצא: " + userName);
            }

            props.load(new InputStreamReader(input, StandardCharsets.UTF_8));

            this.idCard = new IdCardInfo(props);

        } catch (Exception e) {
            throw new RuntimeException("❌ נכשל בטעינת קובץ המשתמש: " + userName, e);
        }
    }

    public static class IdCardInfo {
        public final String issueDate;
        public final String birthDate;
        public final String gender;

        public IdCardInfo(Properties props) {
            this.issueDate = props.getProperty("issueDate", "");
            this.birthDate = props.getProperty("birthDate", "");
            this.gender = props.getProperty("gender", "");
        }
    }
}
