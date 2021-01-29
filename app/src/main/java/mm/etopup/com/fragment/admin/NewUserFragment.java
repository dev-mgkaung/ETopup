package mm.etopup.com.fragment.admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.sql.Timestamp;

import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.activities.AdminActivity;
import mm.etopup.com.activities.SplashActivity;
import mm.etopup.com.database.entity.UserEntity;
import mm.etopup.com.presenter.AdminPresenter;
import mm.etopup.com.session.SessionManager;

public class NewUserFragment extends Fragment {

    @BindView(R.id.ed_email)
    AppCompatEditText ed_email;

    @BindView(R.id.ed_username)
    AppCompatEditText ed_username;

    @BindView(R.id.save_btn)
    AppCompatButton save_btn;

    @BindView(R.id.ed_user_phone)
    AppCompatEditText ed_user_phone;

    @BindView(R.id.ed_user_balance)
    AppCompatEditText ed_user_balance;

    @BindView(R.id.ed_new_password)
    AppCompatEditText ed_new_password;

    @BindView(R.id.ed_confirm_password)
    AppCompatEditText ed_confirm_password;

    @BindView(R.id.logout)
    ImageView logout;

    AdminPresenter adminPresenter;
    private boolean isExistingPhoneNo =false;

    public static NewUserFragment newInstance() {
        NewUserFragment fragment = new NewUserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_user, container, false);
        ButterKnife.bind(this, root);
        initPresenter();
        setUpListener();
        return root;
    }

    private void initPresenter()
    {
        adminPresenter= ViewModelProviders.of(this).get(AdminPresenter.class);
        adminPresenter.initPresenter(getActivity());
    }

    public void setUpListener()
    {

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.getObjectInstance(getActivity()).setLoggedIn(false);
                SessionManager.getObjectInstance(getActivity()).setEmail("");
                getActivity().finish();
                SplashActivity.open(getActivity());
            }
        });


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExistingPhoneNo =false;
                if(ed_username.getText().toString().equalsIgnoreCase("") || ed_email.getText().toString().equalsIgnoreCase("") ||ed_user_phone.getText().toString().equalsIgnoreCase("") ||
                ed_user_balance.getText().toString().equalsIgnoreCase("") || ed_new_password.getText().toString().equalsIgnoreCase("") || ed_confirm_password.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(),"Form incomplete",Toast.LENGTH_SHORT).show();
                }else {
                    if (ed_new_password.getText().toString().equalsIgnoreCase(ed_confirm_password.getText().toString())) {
                        UserEntity userEntity = new UserEntity();
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        System.out.println(timestamp.getTime());
                        userEntity.user_id = timestamp.getTime();
                        userEntity.user_type = "user";
                        userEntity.email = ed_email.getText().toString();
                        userEntity.user_name = ed_username.getText().toString();
                        userEntity.balance = Integer.parseInt(ed_user_balance.getText().toString());
                        userEntity.phone_number = ed_user_phone.getText().toString();
                        userEntity.password = ed_new_password.getText().toString();

                        adminPresenter.checkPhoneNumber(ed_user_phone.getText().toString(), ed_email.getText().toString()).observe(getActivity(), new Observer<UserEntity>() {
                            @Override
                            public void onChanged(@Nullable final UserEntity user) {
                                if (user != null) {
                                    if(isExistingPhoneNo == false ) {
                                        Toast.makeText(getActivity(), "This phone number or email is already exist. Please enter other phone number.", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    adminPresenter.saveNewUser(userEntity);
                                    isExistingPhoneNo =true;
                                    callSuccessDialog();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(getActivity(), "Password incorrect", Toast.LENGTH_SHORT).show();
                      }
                  }
            }
        });
    }

    public void callSuccessDialog()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Successful");
        alertDialog.setMessage("Your account is created!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}