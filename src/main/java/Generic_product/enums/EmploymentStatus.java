package Generic_product.enums;

public enum EmploymentStatus {
    EMPLOYEE("employee"),
    INDEPENDENT("independent"),
    SOLDIER("soldier"),
    STUDENT("student"),
    NOT_WORKING("notWorking");

    private final String value;

    EmploymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EmploymentStatus fromEnv(String envValue) {
        if (envValue == null || envValue.isBlank()) {
            throw new IllegalArgumentException("❌ לא הוגדר employmentStatus בסביבה או בקובץ");
        }

        for (EmploymentStatus status : values()) {
            if (status.value.equalsIgnoreCase(envValue)) {
                return status;
            }
        }
        throw new IllegalArgumentException("❌ ערך לא חוקי לסטטוס תעסוקתי: " + envValue);
    }

    // 📌 Enum נוסף: OccupationType
    public enum OccupationType {
        INTERNET_AND_COMPUTERS("INTERNET_AND_COMPUTERS"),
        ACCOUNTING("ACCOUNTING"),
        ENGINEERING("ENGINEERING"),
        ADVERTISING_AND_MARKETING("ADVERTISING_AND_MARKETING"),
        CLASSES_LEISURE_AND_SPORTS("CLASSES_LEISURE_AND_SPORTS"),
        HEALTH_AND_SOUL("HEALTH_AND_SOUL"),
        AGRICULTURE("AGRICULTURE"),
        ART_AND_CREATION("ART_AND_CREATION"),
        EDUCATION_AND_TEACHING("EDUCATION_AND_TEACHING"),
        COMMUNICATION_AND_JOURNALISM("COMMUNICATION_AND_JOURNALISM"),
        RELIGIOUS_SERVICES("RELIGIOUS_SERVICES"),
        LAWS("LAWS"),
        ARCHITECTURE_AND_DESIGN("ARCHITECTURE_AND_DESIGN"),
        FINANCE_INVESTMENTS_AND_INSURANCE("FINANCE_INVESTMENTS_AND_INSURANCE"),
        TELEVISION_AND_STAGE_PROFESSIONS("TELEVISION_AND_STAGE_PROFESSIONS"),
        CONSULTING_AND_COACHING("CONSULTING_AND_COACHING"),
        ACCOMMODATION_AND_CATERING("ACCOMMODATION_AND_CATERING"),
        SHIPPING("SHIPPING"),
        REAL_ESTATE("REAL_ESTATE"),
        BUILDING_RENOVATIONS_AND_MAINTENANCE("BUILDING_RENOVATIONS_AND_MAINTENANCE"),
        ADMINISTRATION_AND_LOGISTICS("ADMINISTRATION_AND_LOGISTICS"),
        SECURITY_AND_INVESTIGATIONS("SECURITY_AND_INVESTIGATIONS"),
        FASHION_AND_BEAUTY("FASHION_AND_BEAUTY"),
        ANIMALS("ANIMALS"),
        HOUSEHOLD("HOUSEHOLD"),
        TOURISM_AND_RECREATION("TOURISM_AND_RECREATION"),
        TRANSPORTATION_TRANSPORTATION_AND_DELIVERIES("TRANSPORTATION_TRANSPORTATION_AND_DELIVERIES"),
        WHOLESALE("WHOLESALE");

        private final String value;

        OccupationType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static OccupationType fromEnv(String envValue) {
            if (envValue == null || envValue.isBlank()) {
                throw new IllegalArgumentException("❌ לא הוגדר occupation בסביבה או בקובץ");
            }

            for (OccupationType type : values()) {
                if (type.value.equalsIgnoreCase(envValue)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("❌ ערך לא חוקי ל־Occupation: " + envValue);
        }
    }

    public enum LoanPurpose {
        MATERNITY_LEAVE("MATERNITY_LEAVE"),         // מימון חופשת לידה
        EVENT("EVENT"),                             // אירוע
        MATERNITY_PACKAGE("MATERNITY_PACKAGE"),     // חבילה ללידה
        DEBT_COVERAGE("DEBT_COVERAGE"),             // כיסוי יתרת חוב
        VEHICLE("VEHICLE"),                         // רכב
        VACATION("VACATION"),                       // חופשה
        ANY("ANY");                                 // לכל מטרה


        private final String value;

        LoanPurpose(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static LoanPurpose fromEnv(String envValue) {
            if (envValue == null || envValue.isBlank()) {
                throw new IllegalArgumentException("❌ לא הוגדר loanPurpose בסביבה או בקובץ");
            }

            for (LoanPurpose purpose : values()) {
                if (purpose.value.equalsIgnoreCase(envValue)) {
                    return purpose;
                }
            }
            throw new IllegalArgumentException("❌ ערך לא חוקי ל־LoanPurpose: " + envValue);
        }
    }

}
