package quest.com.quest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quest.com.quest.R;
import quest.com.quest.Teacher.AnswersRecyclerAdapter;
import quest.com.quest.Utils.Utilities;
import quest.com.quest.models.ListofExams;

/**
 * Created by kumbh on 11-03-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HolderView>{
    private Context ctx;
    private ListofExams listofExams;
    private onExamDetailsClicked listener;


    public RecyclerAdapter(Context context,ListofExams listofExams ,onExamDetailsClicked listener){
        this.ctx =context;
        this.listofExams =listofExams;
        this.listener = listener;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upcoming_exam,parent,false);
        HolderView holderView = new HolderView(itemView);
        holderView.dateOfExam = (TextView) itemView.findViewById(R.id.exam_date);
        holderView.dayOfExam   = (TextView) itemView.findViewById(R.id.exam_day);
        holderView.examTitle= (TextView) itemView.findViewById(R.id.exam_title);
        holderView.examCOde= (TextView)  itemView.findViewById(R.id.exam_code);
        holderView.durationOfExam=(TextView) itemView.findViewById(R.id.duration_of_exam);

        return holderView;
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {
        final ListofExams.ListOfScheduledExamsBean exambean = listofExams.getListOfScheduledExams().get(position);
        holder.dateOfExam.setText(Utilities.returnDatefromString(exambean.getExam_date()).getDate()+"");
        holder.dayOfExam.setText(Utilities.returnDatefromString(exambean.getExam_date()).getDay()+"");
        holder.durationOfExam.setText(Utilities.returnDuration(exambean.getDuration())+"");
        holder.examCOde.setText(exambean.getExam_manualID()+"");
        holder.examTitle.setText(exambean.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(exambean);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listofExams.getListOfScheduledExams().size();
    }



    public  static  class HolderView extends RecyclerView.ViewHolder {

        public TextView dateOfExam;
        public TextView dayOfExam;
        public TextView examTitle;
        public TextView examCOde;
        public TextView durationOfExam;

        public HolderView(View itemView) {
            super(itemView);
        }
    }


    public interface onExamDetailsClicked{
        void onClick(ListofExams.ListOfScheduledExamsBean examsBean);
    }
}
