package eventmanagement.util;

public class StringValidator {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    public static boolean isValidEmail(String email) {
        // Simple email validation
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public static boolean isValidPhoneNumber(String phone) {
        // Simple phone validation (10 digits)
        return phone != null && phone.matches("\\d{10}");
    }
    
    public static String capitalize(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
