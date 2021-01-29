package mm.etopup.com;

import android.app.Application;

import mm.etopup.com.database.AppDatabase;
import mm.etopup.com.database.entity.UserEntity;
import mm.etopup.com.session.SessionManager;

public class ETopUpApplication extends Application {
    protected AppDatabase mAppDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppDatabase = AppDatabase.getInMemoryDatabase(this);
        mAppDatabase.userDao().insertUser(new UserEntity(
                0, "admin", "admin@gmail.com" , "12345","091234567",1000,"admin"));
        SessionManager.getObjectInstance(this).initSession();
    }
}