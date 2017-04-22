package quest.com.quest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import quest.com.quest.R;

/**
 * Created by skumbam on 30-03-2017.
 */

public class DrawerMenuAdapter  extends ArrayAdapter<DrawerModel> {
    private DrawerModel[] data;
    private Context context;
    private  int layoutID;

    public DrawerMenuAdapter(Context context, int layoutID, DrawerModel[] data) {
        super(context, layoutID, data);

        this.data = data;
        this.context = context;
        this.layoutID = layoutID;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        listItem = inflater.inflate(layoutID, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.iv_item_view);
        TextView textViewName = (TextView) listItem.findViewById(R.id.tv_home_view);

        DrawerModel folder = data[position];


        imageViewIcon.setImageResource(folder.getIcon());
        textViewName.setText(folder.getName());

        return listItem;

    }
}
