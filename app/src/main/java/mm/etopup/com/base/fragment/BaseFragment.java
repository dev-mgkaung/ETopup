package mm.etopup.com.base.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {

    private boolean isShowOnScreen = false;
    private boolean isOnCreate = false;
    private boolean isFinishedShowing = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            readArguments(bundle);
        }
        if (savedInstanceState != null) {
            readArguments(savedInstanceState);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isOnCreate = true;
        if (isShowOnScreen && !isFinishedShowing) {
            isFinishedShowing = true;
            onShowingScreen();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isShowOnScreen = true;
            if (isOnCreate && !isFinishedShowing) {
                isFinishedShowing = true;
                onShowingScreen();
            }
        } else {
            isShowOnScreen = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    protected void readArguments(Bundle bundle) {

    }

    public abstract void onShowingScreen();
}