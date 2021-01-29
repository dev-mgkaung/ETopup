package mm.etopup.com.session;


import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static SessionManager objectInstance;
    public boolean show_popup = false;
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    private String PREF_NAME = "ELoad";
    private String IS_LOGIN = "IsLoggedIn";
    private String KEY_USERTYPE = "usertype";
    private String KEY_USEREMAIL = "user_email";

    public SessionManager() {
    }

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
    }

    public void initSession() {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static SessionManager getObjectInstance(Context context) {
        if (objectInstance == null) {
            objectInstance = new SessionManager(context);
            objectInstance.initSession();
        }
        return objectInstance;
    }

    public boolean getLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setLoggedIn(boolean loggedIn) {
        editor.putBoolean(IS_LOGIN, loggedIn);
        editor.commit();
    }

    public String getEmail() {
        return pref.getString(KEY_USEREMAIL, "");
    }

    public void setEmail(String phoneNumber) {
        editor.putString(KEY_USEREMAIL, phoneNumber);
        editor.commit();
    }


    public void setAlreadyLoggedInUserType(String type) {
        editor.putString(KEY_USERTYPE, type);
        editor.commit();
    }

    public String getAlreadyLoggedInUserType() {
        return pref.getString(KEY_USERTYPE, "");
    }
}