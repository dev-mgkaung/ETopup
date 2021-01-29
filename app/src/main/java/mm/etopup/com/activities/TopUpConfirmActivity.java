package mm.etopup.com.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.button.MaterialButton;
import java.text.SimpleDateFormat;
import java.util.Date;
import butterknife.BindView;
import mm.etopup.com.R;
import mm.etopup.com.base.activity.BaseActivity;
import mm.etopup.com.database.entity.TransitionHistory;
import mm.etopup.com.presenter.UserPresenter;
import mm.etopup.com.session.SessionManager;

public class TopUpConfirmActivity extends BaseActivity {

    UserPresenter userPresenter;
    int balance = -1;
    String operatorname;
    String pno;

    @BindView(R.id.cancel_btn)
    MaterialButton cancel_btn;

    @BindView(R.id.confirm_btn)
    MaterialButton confirm_btn;

    public static void open(Context context, int balance, String operatorname, String pno) {
        Intent intent = new Intent(context, TopUpConfirmActivity.class);
        intent.putExtra("balance" , balance);
        intent.putExtra("operatorname" ,operatorname);
        intent.putExtra("pno",pno);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_confirm);
        initPresenter();

        balance = Integer.parseInt(getIntent().getStringExtra("balance"));


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUpConfirmActivity.this.finish();
                UserActivity.open(TopUpConfirmActivity.this);
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.updateUserBalance(SessionManager.getObjectInstance(TopUpConfirmActivity.this).getEmail().toString(), balance + "");
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                userPresenter.insertTransitionRecord(new TransitionHistory(
                        operatorname, pno,
                        SessionManager.getObjectInstance(TopUpConfirmActivity.this).getEmail().toString(),
                        formatter.format(date).toString() ,
                        balance + ""
                ));
                callSuccessDialog();
            }
        });



    }

    private void initPresenter()
    {
        userPresenter= ViewModelProviders.of(this).get(UserPresenter.class);
        userPresenter.initPresenter(TopUpConfirmActivity.this);
    }

    public void callSuccessDialog()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(TopUpConfirmActivity.this).create();
        alertDialog.setTitle("Success");
        alertDialog.setMessage("Mobile Top Up Transication is successful");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        TopUpConfirmActivity.this.finish();
                        UserActivity.open(TopUpConfirmActivity.this);
                    }
                });
        alertDialog.show();
    }
}
