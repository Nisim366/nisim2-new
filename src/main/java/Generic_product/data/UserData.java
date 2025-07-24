package Generic_product.data;

import utilities.EmailGenerator;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class UserData {

    public final PersonalInfo personal;
    public final IdCardInfo idCard;
    public final AddressInfo address;
    public final EmploymentInfo employment;
    public final LoanInfo loan;
    public final String securityAnswer;




    public UserData(String userName) {
        try {
            Properties props = new Properties();

            InputStream input = getClass().getClassLoader().getResourceAsStream("users/" + userName + ".properties");
            if (input == null) {
                throw new RuntimeException("❌ קובץ המשתמש לא נמצא: " + userName);
            }

            props.load(new InputStreamReader(input, StandardCharsets.UTF_8));

            this.personal = new PersonalInfo(props);
            this.idCard = new IdCardInfo(props);
            this.address = new AddressInfo(props);
            this.employment = new EmploymentInfo(props);
            this.loan = new LoanInfo(props);
            this.securityAnswer = props.getProperty("securityAnswer", "123456");




        } catch (Exception e) {
            throw new RuntimeException("❌ נכשל בטעינת קובץ המשתמש: " + userName, e);
        }
    }



    public static class PersonalInfo {
        public final String firstName;
        public final String lastName;
        public final String phone;
        public final String email;

        public PersonalInfo(Properties props) {
            this.firstName = props.getProperty("firstName", "ברירתמחדל");
            this.lastName = props.getProperty("lastName", "ברירתמחדל");
            this.phone = props.getProperty("phone", "0532407762");

            this.email = EmailGenerator.generateUniqueEmail();

        }
    }

    public static class IdCardInfo {
        public final String birthDate;
        public final String gender;
        public final String issueDate;

        public IdCardInfo(Properties props) {
            this.issueDate = props.getProperty("issueDate", "");
            this.birthDate = props.getProperty("birthDate", "");
            this.gender = props.getProperty("gender", "");
        }
    }

    public static class AddressInfo {
        public final String town;
        public final String street;
        public final String houseNumber;
        public final String apartment;
        public final String zipCode;

        public AddressInfo(Properties props) {
            this.town = props.getProperty("town", "");
            this.street = props.getProperty("street", "");
            this.houseNumber = props.getProperty("houseNumber", "");
            this.apartment = props.getProperty("apartment", "");
            this.zipCode = props.getProperty("zipCode", "");
        }
    }

    public static class EmploymentInfo {
        public final String status;
        public final String occupation;
        public final String profession;
        public final String income;

        public EmploymentInfo(Properties props) {
            this.status = props.getProperty("employmentStatus", "שכיר/ה");
            this.occupation = props.getProperty("occupation", "חשבונאות");
            this.profession = props.getProperty("profession", "ראיית חשבון");
            this.income = props.getProperty("employment.income", "12000"); // ⬅️ שינוי פה

        }
    }
    public static class LoanInfo {
        public final String amount;

        public LoanInfo(Properties props) {
            this.amount = props.getProperty("amount", "1000");
        }
    }



}
