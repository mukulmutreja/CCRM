package ccrm.util;

public class ValidateUtils {

    public static void checkNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }
}
