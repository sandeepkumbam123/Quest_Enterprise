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
import quest.com.quest.models.ListofExams;

/**
 * Created by kumbh on 02-04-2017.
 */

public class StudentStatusReportAdapter extends BaseAdapter {

    private Context ctx;
    private ListofExams examsList;
    private int layoutId;
    public StudentStatusReportAdapter(Context ctx, ListofExams examsList, int layoutId){
        this.ctx= ctx;
        this.examsList = examsList;
        this.layoutId = layoutId;
    }



    @Override
    public int getCount() {
        return examsList.getListOfScheduledExams().size();
    }

    @Override
    public ListofExams.ListOfScheduledExamsBean getItem(int position) {
        return examsList.getListOfScheduledExams().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        TextView examTitle,examClass,examDate;
        ListofExams.ListOfScheduledExamsBean examData = getItem(position);
        if(convertView==null){
            convertView = inflater.inflate(layoutId,null,false);
        }
        examTitle = (TextView) convertView.findViewById(R.id.tv_badge_title);
//        examClass   = (TextView)convertView.findViewById(R.id.class_name);
        examDate =(TextView)convertView.findViewById(R.id.tv_date_of_exam);

        examTitle.setText(examData.getTitle());
//        examClass.setText(examData.getClassOfExam());
        examDate.setText(examData.getExam_date());

        return convertView;
    }
}
