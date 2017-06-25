package quest.com.quest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import quest.com.quest.R;
import quest.com.quest.Utils.Utilities;
import quest.com.quest.models.CreatedExamsModel;
import quest.com.quest.models.ListofExams;

/**
 * Created by kumbh on 02-04-2017.
 */

public class StudentStatusReportAdapter extends RecyclerView.Adapter<StudentStatusReportAdapter.HolderView> {

    private Context ctx;
    private ListofExams examsList;
    private int layoutId;
    private StudentStatusReportAdapter.PreviousExamReportData listener;
    public StudentStatusReportAdapter(Context ctx, ListofExams examsList, int layoutId ,PreviousExamReportData listener){
        this.ctx= ctx;
        this.examsList = examsList;
        this.layoutId = layoutId;
        this.listener = listener;
    }






    public ListofExams.ListOfScheduledExamsBean getItem(int position) {
        return examsList.getListOfScheduledExams().get(position);
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(ctx).inflate(layoutId, parent, false);
        return new HolderView(view);

    }

    @Override
    public void onBindViewHolder(HolderView holder, final int position) {

        ListofExams.ListOfScheduledExamsBean examData = getItem(position);

        holder.examBadge.setVisibility(View.GONE);
        holder.examTitle.setText(examData.getTitle());
        holder.examDate.setText(Utilities.formattedDatefromString(examData.getExam_date()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.reportOnClick(position);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return examsList.getListOfScheduledExams().size();
    }

  /*  @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        TextView examTitle,examDate;
        LinearLayout examBadge;
        ListofExams.ListOfScheduledExamsBean examData = getItem(position);
        if(convertView==null){
            convertView = inflater.inflate(layoutId,null,false);
        }
        examBadge = (LinearLayout) convertView.findViewById(R.id.badge_layout);
        examTitle = (TextView) convertView.findViewById(R.id.tv_badge_title);
        examDate =(TextView)convertView.findViewById(R.id.tv_date_of_exam);

        examBadge.setVisibility(View.GONE);
        examTitle.setText(examData.getTitle());
        examDate.setText(Utilities.formattedDatefromString(examData.getExam_date()));

        return convertView;
    }*/


    public static class HolderView extends RecyclerView.ViewHolder{

        private TextView examTitle,examDate;
        private LinearLayout examBadge;
        public HolderView(View itemView) {
            super(itemView);
            examBadge = (LinearLayout) itemView.findViewById(R.id.badge_layout);
            examTitle = (TextView) itemView.findViewById(R.id.tv_badge_title);
            examDate =(TextView)itemView.findViewById(R.id.tv_date_of_exam);

        }
    }


    public interface PreviousExamReportData{
        void reportOnClick(int position);
    }
}
