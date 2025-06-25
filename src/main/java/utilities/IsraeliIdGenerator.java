package utilities;

public class IsraeliIdGenerator {

    public static String generateRandomValidIsraeliId() {
        String id;
        do {
            int base = (int) (Math.random() * 1_000_000); // רק 6 ספרות ראשונות
            id = String.format("%09d", base);
        } while (!isValidIsraeliId(id));
        return id;
    }

    public static boolean isValidIsraeliId(String id) {
        if (id == null || !id.matches("\\d{5,9}")) {
            return false;
        }

        id = String.format("%09d", Integer.parseInt(id));
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(id.charAt(i));
            int mult = digit * ((i % 2) + 1);
            sum += (mult > 9) ? mult - 9 : mult;
        }

        return sum % 10 == 0;
    }
}

