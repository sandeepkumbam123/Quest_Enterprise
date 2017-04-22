package quest.com.quest.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import quest.com.quest.R;
import quest.com.quest.models.CreatedExamsModel;

/**
 * Created by kumbh on 02-04-2017.
 */

public class StudentStatusReportAdapter extends BaseAdapter {

    private Context ctx;
    private List<CreatedExamsModel> examsList;
    private int layoutId;
    public StudentStatusReportAdapter(Context ctx, List<CreatedExamsModel> examsList, int layoutId){
        this.ctx= ctx;
        this.examsList = examsList;
        this.layoutId = layoutId;
    }



    @Override
    public int getCount() {
        return examsList.size();
    }

    @Override
    public CreatedExamsModel getItem(int position) {
        return examsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        TextView examTitle,examClass,examDate;
        CreatedExamsModel examData = getItem(position);
        if(convertView==null){
            convertView = inflater.inflate(layoutId,null,false);
        }
        examTitle = (TextView) convertView.findViewById(R.id.tv_badge_title);
//        examClass   = (TextView)convertView.findViewById(R.id.class_name);
        examDate =(TextView)convertView.findViewById(R.id.tv_date_of_exam);

        examTitle.setText(examData.getExamTitle());
//        examClass.setText(examData.getClassOfExam());
        examDate.setText(examData.getDateofExam());

        return convertView;
    }
}
