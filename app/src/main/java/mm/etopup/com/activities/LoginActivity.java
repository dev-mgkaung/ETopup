package mm.etopup.com.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.base.activity.BaseActivity;
import mm.etopup.com.database.entity.UserEntity;
import mm.etopup.com.presenter.LoginPresenter;
import mm.etopup.com.session.SessionManager;

public class LoginActivity extends BaseActivity  {

    @BindView(R.id.loginButton)
    AppCompatButton loginButton;

    @BindView(R.id.editTextPassword)
    AppCompatEditText editTextPassword;

    @BindView(R.id.editTextEmail)
    AppCompatEditText editTextEmail;

    @BindView(R.id.forgetpassWord)
    TextView forgetpassWord;

    @BindView(R.id.register)
    TextView register;

    LoginPresenter loginPresenter;

    public static void open(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        initPresenter();
        setUpListener();

    }
    private void initPresenter()
    {
        loginPresenter= ViewModelProviders.of(this).get(LoginPresenter.class);
        loginPresenter.initPresenter(this);
    }


    private void setUpListener()
    {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                loginPresenter.getLoginUser(email, password).observe(LoginActivity.this, new Observer<UserEntity>() {
                    @Override
                    public void onChanged(@Nullable final UserEntity user) {
                        if (user != null) {
                            if(user.user_type.equalsIgnoreCase("admin")){
                                AdminActivity.open(LoginActivity.this);
                                SessionManager.getObjectInstance(LoginActivity.this).setAlreadyLoggedInUserType("admin");
                            }else {
                                UserActivity.open(LoginActivity.this);
                                SessionManager.getObjectInstance(LoginActivity.this).setAlreadyLoggedInUserType("user");
                            }
                            SessionManager.getObjectInstance(LoginActivity.this).setLoggedIn(true);
                            Toast.makeText(LoginActivity.this,editTextEmail.getText().toString(),Toast.LENGTH_SHORT).show();
                            SessionManager.getObjectInstance(LoginActivity.this).setEmail(editTextEmail.getText().toString());

                            LoginActivity.this.finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"Something Wrong Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "This feature current not available",Toast.LENGTH_LONG).show();

            }
        });

        forgetpassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "This feature current not available",Toast.LENGTH_LONG).show();

            }
        });
    }

}