package util;

public class InputValidator 
{
    public static boolean isEmpty(String value)
    {
        return value==null || value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email)
    {
        return email.contains("@") && email.contains(".");
    }
}
