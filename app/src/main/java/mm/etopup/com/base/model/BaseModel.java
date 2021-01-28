package mm.etopup.com.base.model;


import android.content.Context;
import mm.etopup.com.database.AppDatabase;

public class BaseModel {

    protected AppDatabase mAppDatabase;

    public BaseModel(final Context context) {
        mAppDatabase = AppDatabase.getInMemoryDatabase(context);
    }

}