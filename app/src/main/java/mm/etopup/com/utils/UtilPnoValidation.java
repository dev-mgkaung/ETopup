package mm.etopup.com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilPnoValidation {

    public UtilPnoValidation(){}

    private static UtilPnoValidation objInstance;
    String imageFilePath;

    public static UtilPnoValidation getInstance() {
        if (objInstance == null) {
            objInstance = new UtilPnoValidation();
        }
        return objInstance;
    }


    public static boolean isTelenor(String pno)
    {
        Pattern pattern = Pattern.compile("/^(09|\\+?959)7\\d{8}$/", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pno);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMyTel(String pno)
    {
        Pattern pattern = Pattern.compile("/^(09|\\+?959)6\\d{8}$/", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pno);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOoredoo(String pno)
    {
        Pattern pattern = Pattern.compile("/^(09|\\+?959)9\\d{8}$/", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pno);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        } else {
            return false;
        }
    }

    public   static boolean isMPT(String pno)
    {
        Pattern pattern = Pattern.compile("/^(09|\\+?959)(2[0-4]\\d{5}|5[0-6]\\d{5}|8[13-7]\\d{5}|4[1379]\\d{6}|73\\d{6}|91\\d{6}|25\\d{7}|26[0-5]\\d{6}|40[0-4]\\d{6}|42\\d{7}|44[0-589]\\d{6}|45\\d{7}|87\\d{7}|89[6789]\\d{6})$/", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pno);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMEC(String pno)
    {
        Pattern pattern = Pattern.compile("/^(09|\\+?959)3\\d{7}$/", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pno);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        } else {
            return false;
        }
    }

    public  static boolean isCorrectPno(String pno)
    {
        if(pno == "" || pno == null){
            return false;
        }else if(pno.length()<3){
            return false;
        } else{
            String phone = pno.substring(0, 2) == ("09")? pno.substring(2) : pno.substring(0, 3) == "+95" ? pno.substring(3) : pno;

            if (phone.length() > 9 && phone.substring(0 ,1) == ("9")) {
                phone = pno.substring(1);
            }

            if(phone.trim().length() < 1) {
                return false;
            } else if (phone.trim().length() < 7) {
                return false;
            } else if (phone.length() == 7) {
                if (!(phone.substring(0, 1) == ("5")||phone.substring(0, 1) == ("2") || phone.substring(0, 1) == ("8")))
                    return false;
            } else if (phone.length() == 8) {
                if (!(phone.substring(0, 1) == ("3") || phone.substring(0, 1) == ("4") || phone.substring(0, 1) == ("8")))
                    return false;
            } else if (phone.length() == 9) {
                if (!(phone.substring(0, 1) == ("6") || phone.substring(0, 1) == ("7") || phone.substring(0, 1) == ("9") || phone.substring(0, 1) == ("2") || phone.substring(0,1) == ("4") || phone.substring(0,1) == ("8")))
                    return false;
            } else if(phone.length() > 10) {
                return false;
            }
        }
        {
            return false;
        }

    }

}
