package utils;

public class NameValidation {
    public static boolean isValidString(String string)
    {
        for(int i = 0;i<string.length();i++)
        {
            char ch = string.charAt(i);
            //if there is a foreign character
            if((!(ch >= 'A' && ch <= 'Z')) && (!(ch >= 'a' && ch <= 'z')))
            {
                System.out.println("Invalid character in first or last name.");
                return false;
            }
        }

        return true;
    }
}
