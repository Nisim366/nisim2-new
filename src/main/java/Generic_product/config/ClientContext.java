package Generic_product.config;

public class ClientContext {
    private static Client client = Client.BALKAR; // ברירת מחדל



    public enum Client {
        FIRST("first"),
        KABLANIM("kablanim"),
        LEADBOX("leadbox"),
        TWIST("twist"),
        BALKAR("balkar"),
        RESULT("result");

        private final String name;

        Client(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Client fromString(String value) {
            for (Client c : values()) {
                if (c.name.equalsIgnoreCase(value)) {
                    return c;
                }
            }
            throw new IllegalArgumentException("❌ Client not found: " + value);
        }
    }


    public static void setClient(String name) {
        client = Client.fromString(name);
    }

    public static String getClient() {
        return client.getName();
    }

    public static Client getClientEnum() {
        return client;
    }

    static {
        // מאפשר העברת לקוח דרך שורת פקודה -Dclient=kablanim
        String fromSystem = System.getProperty("client");
        if (fromSystem != null && !fromSystem.isEmpty()) {
            client = Client.fromString(fromSystem);
        }
    }
}
