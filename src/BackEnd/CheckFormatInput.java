package BackEnd;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFormatInput {
    public static  boolean checkPhoneNumber(String phoneNumber )
        {
            String regex = "^0\\d{9,10}$";  // Dinh dang so dien thoai bat dau voi 0 va có 10 hoac 11 chu so
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phoneNumber);
            if(matcher.find()) {
                return true;
            }else {
                return false;
            }

        }

    public static boolean checkName (String name) {
        String regex = "^[a-zA-Z\\s]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if(matcher.find()){
            return true;
        }else{
            return false;
        }

    }
}