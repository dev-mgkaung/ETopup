package mm.etopup.com.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import mm.etopup.com.base.presenter.BasePresenter;
import mm.etopup.com.database.AppDatabase;
import mm.etopup.com.database.entity.UserEntity;


public class AdminPresenter extends BasePresenter {

    private AppDatabase mAppDatabase;

    @Override
    public void initPresenter(Context context) {
        super.initPresenter(context);
        mAppDatabase = AppDatabase.getInMemoryDatabase(context);
    }

    public void  saveNewUser(UserEntity userEntity){
         mAppDatabase.userDao().insertUser(userEntity);
    }

    public LiveData<UserEntity> checkPhoneNumber(String phone){
        return mAppDatabase.userDao().checkPhoneNumber(phone);
    }

}
