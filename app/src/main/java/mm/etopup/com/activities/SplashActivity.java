package mm.etopup.com.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import mm.etopup.com.R;
import mm.etopup.com.base.activity.BaseActivity;
import mm.etopup.com.session.SessionManager;


public class SplashActivity extends BaseActivity {

    public static void open(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if (SessionManager.getObjectInstance(SplashActivity.this).getLoggedIn()) {
                        if (SessionManager.getObjectInstance(SplashActivity.this).getAlreadyLoggedInUserType().equalsIgnoreCase("admin")) {
                            AdminActivity.open(SplashActivity.this);
                        } else {
                            UserActivity.open(SplashActivity.this);
                        }
                    } else {
                        LoginActivity.open(SplashActivity.this);
                    }
                    // Close this page
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}

