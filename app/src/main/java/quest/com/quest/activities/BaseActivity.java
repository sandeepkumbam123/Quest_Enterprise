package quest.com.quest.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by skumbam on 23-02-2017.
 */

public class BaseActivity extends AppCompatActivity {


    public static void showToast(Activity activity,String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }


    public  boolean isTablet(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        } else {
            getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        return screenInches >= 6.7;
    }
    public static boolean isNetworkConnected(Context activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void navigatetoNextActivity(Activity activity, Activity nextActivity, Bundle data){
        Intent nextScreen = new Intent(activity,nextActivity.getClass());
        nextScreen.putExtras(data);
        activity.startActivity(nextScreen);
    }
    public static void navigatetoNextActivity(Activity activity, Activity nextActivity){
        Intent nextScreen = new Intent(activity,nextActivity.getClass());
        activity.startActivity(nextScreen);
    }


    public  static void replaceFragment(Context ctx, int id, Fragment fragment){
    }
}
