package mm.etopup.com.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginButton)
    AppCompatButton loginButton;

    @BindView(R.id.editTextPassword)
    AppCompatEditText editTextPassword;

    @BindView(R.id.editTextEmail)
    AppCompatEditText editTextEmail;

    public static void open(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpClickAction();

    }

    private void setUpClickAction()
    {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminActivity.open(LoginActivity.this);
            }
        });
    }
}