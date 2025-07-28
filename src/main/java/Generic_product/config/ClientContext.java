package Generic_product.config;

public class ClientContext {

    private static String clientName = "user2"; // ברירת מחדל

    public static void setClient(String name) {
        clientName = name;
    }

    public static String getClient() {
        return clientName;
    }

    static {
        // מאפשר העברת לקוח דרך שורת פקודה -Dclient=user2
        String fromSystem = System.getProperty("client");
        if (fromSystem != null && !fromSystem.isEmpty()) {
            clientName = fromSystem;
        }
    }
}
