package quest.com.quest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import quest.com.quest.R;
import quest.com.quest.models.ListofExams;
import quest.com.quest.models.PreviousExamsListModel;

/**
 * Created by kumbh on 10-06-2017.
 */

public class GridPreviousExamsAdapter extends RecyclerView.Adapter<GridAdapter.SimpleViewHolder>  {
    private Context ctx;
    private List<PreviousExamsListModel> examsList;
    private GridPreviousExamsAdapter.PreviousExamclick clickListner;
    public GridPreviousExamsAdapter(Context ctx, List<PreviousExamsListModel> examsList, PreviousExamclick clickListner){
        this.ctx= ctx;
        this.examsList = examsList;
        this.clickListner = clickListner;
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView examTitle,examClass,examDate;
        public LinearLayout examLayout;

        public SimpleViewHolder(View convertView) {
            super(convertView);
            examTitle = (TextView) convertView.findViewById(R.id.exam_title);
            examClass   = (TextView)convertView.findViewById(R.id.class_name);
            examDate =(TextView)convertView.findViewById(R.id.exam_date);
            examLayout=(LinearLayout)convertView.findViewById(R.id.ll_exam_layout);
        }
    }

    @Override
    public GridAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(ctx).inflate(R.layout.previous_exam_card, parent, false);
        return new GridAdapter.SimpleViewHolder(view);
    }

    public PreviousExamsListModel getExamData(int position){
        return examsList !=null ? examsList.get(position) : null ;
    }

    @Override
    public void onBindViewHolder(GridAdapter.SimpleViewHolder holder, int position) {
        PreviousExamsListModel examBean = getExamData(position);
        holder. examTitle.setText(examBean.getTitle());
        holder. examClass.setText(examBean.getExam_manualID());
        holder.examDate.setText(examBean.getExam_date());

        holder.examDate.setOnClickListener(clickListner(position));
        holder.examClass.setOnClickListener(clickListner(position));
        holder.examTitle.setOnClickListener(clickListner(position));
        holder.examLayout.setOnClickListener(clickListner(position));

    }

    private View.OnClickListener clickListner(final int position){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                clickListner.examData(getExamData(position));

            }
        };
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return examsList != null ? examsList.size() : 0;
    }


    public interface  PreviousExamclick {
        void examData(PreviousExamsListModel bean);
    }
}
