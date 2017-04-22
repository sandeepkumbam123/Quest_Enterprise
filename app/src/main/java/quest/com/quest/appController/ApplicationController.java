package quest.com.quest.appController;

import android.app.Application;

import quest.com.quest.R;
import quest.com.quest.SqliteDb.Database;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by skumbam on 24-02-2017.
 */

public class ApplicationController extends Application {
private Database db;
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Ubuntu-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        db = new Database(this);
    }
}
