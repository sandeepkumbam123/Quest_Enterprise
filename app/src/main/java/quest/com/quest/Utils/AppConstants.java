package quest.com.quest.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class AppConstants {

    private static final String APP_NAME = "ENTERPRISE_APP";
    public static final String SSN_ID ="ssnid";

   public static void SaveInSP(Context ctx,String key,String Value){
       SharedPreferences sp = ctx.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
       SharedPreferences.Editor ed = sp.edit();
       ed.putString(key,Value);
       ed.apply();
   }


    public static String getFromSP(Context ctx,String key){
        String value = ctx.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE).getString(key,"");

        return value;
    }



}
