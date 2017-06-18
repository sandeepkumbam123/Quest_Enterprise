package quest.com.quest.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import quest.com.quest.R;

/**
 * Created by skumbam on 30-03-2017.
 */

public class DrawerMenuAdapter extends BaseAdapter {
    private static String[] menuItemsText = {"Home", "Previous Exams", "Student Data", "Logout"};
    private static int[] selectedMenuItemsDrawable = {R.drawable.teacher_home_ic, R.drawable.teacher_previousexam_ic, R.drawable.teacher_studentdata_ic,
            R.drawable.teacher_logout_ic};
    @Override
    public int getCount() {
        return menuItemsText.length;
    }

    @Override
    public Object getItem(int position) {
        return menuItemsText[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView mMenuText;
        ImageView mMenuImage;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_side_menu_items, parent, false);
            mMenuText = (TextView) convertView.findViewById(R.id.tv_menuItem);
            mMenuImage = (ImageView) convertView.findViewById(R.id.iv_menuImage);
            mMenuText.setText(menuItemsText[position]);
            mMenuImage.setImageResource(selectedMenuItemsDrawable[position]);
        }
        return convertView;
    }
}
