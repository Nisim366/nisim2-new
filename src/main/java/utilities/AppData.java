package utilities;

public class AppData {
    public String firstName;
    public String lastName;
    public String phone;
    public String email;

    public AppData(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public class FlowHandler {
        public void runFlow(AppData data) {
            System.out.println("שם: " + data.firstName);
            System.out.println("טלפון: " + data.phone);
            // כאן תבצע את כל התהליך עם הערכים הספציפיים
        }
    }

}

