package mm.etopup.com.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.activities.SplashActivity;
import mm.etopup.com.session.SessionManager;

public class AccountFragment extends Fragment {


    @BindView(R.id.logoutButton)
    AppCompatButton logoutButton;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, root);
        setUpListener();
        return root;
    }

    private void setUpListener()
    {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.getObjectInstance(getActivity()).setLoggedIn(false);
                getActivity().finish();
                SplashActivity.open(getActivity());
            }
        });
    }
}