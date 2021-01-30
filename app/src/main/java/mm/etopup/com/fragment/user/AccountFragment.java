package mm.etopup.com.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.activities.SplashActivity;
import mm.etopup.com.database.entity.UserEntity;
import mm.etopup.com.presenter.UserPresenter;
import mm.etopup.com.session.SessionManager;

public class AccountFragment extends Fragment {


    UserPresenter userPresenter;

    @BindView(R.id.logoutButton)
    AppCompatButton logoutButton;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.balance)
    TextView balance;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, root);
        initPresenter();
        setUpListener();
        listenObserver();
        return root;
    }

    private void initPresenter()
    {
        userPresenter= ViewModelProviders.of(this).get(UserPresenter.class);
        userPresenter.initPresenter(getActivity());
    }

    private void setUpListener()
    {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.getObjectInstance(getActivity()).setLoggedIn(false);
                SessionManager.getObjectInstance(getActivity()).setEmail("");
                getActivity().finish();
                SplashActivity.open(getActivity());
            }
        });
    }


    private void listenObserver()
    {
       userPresenter.getUser(SessionManager.getObjectInstance(getActivity()).getEmail().toString()).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(@Nullable final UserEntity user) {
                if (user != null) {
                    balance.setText(user.balance + "  Ks");
                    username.setText(user.user_name);
                    phone.setText(user.phone_number);
                    email.setText(user.email);
                }else{
                    Toast.makeText(getActivity(), "User", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}