package mm.etopup.com.presenter;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import mm.etopup.com.base.presenter.BasePresenter;
import mm.etopup.com.database.AppDatabase;
import mm.etopup.com.database.entity.TransitionHistory;
import mm.etopup.com.database.entity.UserEntity;

public class UserPresenter extends BasePresenter {

    private AppDatabase mAppDatabase;

    @Override
    public void initPresenter(Context context) {
        super.initPresenter(context);
        mAppDatabase = AppDatabase.getInMemoryDatabase(context);
    }

    public void  updateUserBalance(String email , String balance){
       mAppDatabase.userDao().updateUserByEmail(email , balance);
    }

    public LiveData<List<TransitionHistory>> getHistoryByEmail(String email){
        return mAppDatabase.transitionDao().getAllHistoryByEmail(email);
    }

    public LiveData<UserEntity> getUser(String email){
        return mAppDatabase.userDao().getOneUser(email);
    }

    public void  insertTransitionRecord(TransitionHistory transitionHistory){
         mAppDatabase.transitionDao().insertTransition(transitionHistory);
    }

}
