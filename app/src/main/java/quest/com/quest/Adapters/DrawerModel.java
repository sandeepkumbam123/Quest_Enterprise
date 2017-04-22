package quest.com.quest.Adapters;

import android.content.Context;

/**
 * Created by skumbam on 30-03-2017.
 */

public class DrawerModel {

    private String  name;
    private int icon;
    public DrawerModel( String name, int icon){
        this.name = name;
        this.icon = icon;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
