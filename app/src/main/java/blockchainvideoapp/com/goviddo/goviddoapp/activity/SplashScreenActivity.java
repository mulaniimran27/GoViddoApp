package blockchainvideoapp.com.goviddo.goviddoapp.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import blockchainvideoapp.com.goviddo.goviddoapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 4000;
    ProgressBar mProgressBar;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mProgressBar = findViewById(R.id.splash_screen_progress_bar);

        mProgressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
