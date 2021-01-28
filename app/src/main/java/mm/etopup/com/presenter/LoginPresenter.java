package mm.etopup.com.presenter;

import android.content.Context;
import androidx.lifecycle.LiveData;

import mm.etopup.com.base.presenter.BasePresenter;
import mm.etopup.com.database.AppDatabase;
import mm.etopup.com.database.entity.UserEntity;

public class LoginPresenter extends BasePresenter {

    private AppDatabase mAppDatabase;

    @Override
    public void initPresenter(Context context) {
        super.initPresenter(context);
        mAppDatabase = AppDatabase.getInMemoryDatabase(context);
    }

    public LiveData<UserEntity> getLoginUser(String email , String password){
        return mAppDatabase.userDao().checkLoginUser(email,password);
    }



}
