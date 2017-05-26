package quest.com.quest.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by skumbam on 5/26/17.
 */

public class PrefUtils {
    SharedPreferences sharedPreferences;

    public static SharedPreferences getInstance(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("QUEST_APP",Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static void writeExamIdDetaisinSP(Context context, SharedPreferences sp, String key , int value){
        getInstance(context).edit().putInt(key, value).apply();
    }

    public static int getExamIdDetailsfromSP(Context context , String key){
        return getInstance(context).getInt(key,0);
    }

}
