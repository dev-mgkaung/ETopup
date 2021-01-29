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
        Pattern pattern = Pattern.compile("^((09|\\+?959)7(9|8|7)\\d{7})$", Pattern.CASE_INSENSITIVE);
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
        Pattern pattern = Pattern.compile("^((09|\\+?959)6\\d{8})$", Pattern.CASE_INSENSITIVE);
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
        Pattern pattern = Pattern.compile("^((09|\\+?959)9(7|6)\\d{7})$", Pattern.CASE_INSENSITIVE);
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
        Pattern pattern = Pattern.compile("^((09|\\+?959)(5\\d{6}|4\\d{7,8}|2\\d{6,8}|3\\d{7,8}|6\\d{6}|8\\d{6}|7\\d{7}|9(0|1|9)\\d{5,6}))$", Pattern.CASE_INSENSITIVE);
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

    public  static boolean isValidMyanmarPhoneNumber(String pno)
    {
       boolean status = false;
       if(isMEC(pno))
       {
           status =true;
       }
       if(isMPT(pno)){
           status =true;
       }
       if(isTelenor(pno))
       {
           status =true;
       }
       if(isOoredoo(pno))
       {
           status =true;
       }
       if(isMyTel(pno))
       {
           status =true;
       }
       return status;
    }

}
