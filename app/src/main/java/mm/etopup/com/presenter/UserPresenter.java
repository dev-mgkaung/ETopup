package mm.etopup.com.presenter;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import mm.etopup.com.base.presenter.BasePresenter;
import mm.etopup.com.database.AppDatabase;
import mm.etopup.com.database.entity.UserEntity;

public class UserPresenter extends BasePresenter {

    private AppDatabase mAppDatabase;

    @Override
    public void initPresenter(Context context) {
        super.initPresenter(context);
        mAppDatabase = AppDatabase.getInMemoryDatabase(context);
    }

    public void  saveRechage(UserEntity userEntity){
        //mAppDatabase.userDao().insertUser(userEntity);
    }

    public LiveData<List<UserEntity>> getHistoryByEmail(){
        return mAppDatabase.userDao().getAllUser();
    }

    public LiveData<UserEntity> getUser(String phoneNumber){
        return mAppDatabase.userDao().getOneUser(phoneNumber);
    }

}
