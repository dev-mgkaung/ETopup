package mm.etopup.com.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import mm.etopup.com.R;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    // Wait delay Time 3 sec
                    sleep(3000);
                    // Go To Login Screen
                    LoginActivity.open(SplashActivity.this);
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

