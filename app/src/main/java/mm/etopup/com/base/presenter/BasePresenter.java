package mm.etopup.com.base.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;


public abstract class BasePresenter extends ViewModel {

    protected MutableLiveData<ErrorData> mErrorLD;

    public void initPresenter(Context context) {
        mErrorLD = new MutableLiveData<>();
    }

    public LiveData<ErrorData> getErrorLD() {
        return mErrorLD;
    }

}
