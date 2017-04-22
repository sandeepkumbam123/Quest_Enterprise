package quest.com.quest.activities;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;

import quest.com.quest.R;

public class SplashScreenActivity extends BaseActivity  {

    private static int  TIME_TO_SWITCH=3000;
    private static String TAG =SplashScreenActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(showscreen(this),TIME_TO_SWITCH);


    }

    private Runnable showscreen(final Activity splashScreenActivity) {
        return new Runnable() {
            @Override
            public void run() {
                Bundle data = new Bundle();
                data.putString(TAG,TAG);
                navigatetoNextActivity(splashScreenActivity,new LoginActivity(),data);
                finish();
            }


        };
    }


}
