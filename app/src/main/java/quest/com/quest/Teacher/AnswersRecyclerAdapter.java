package quest.com.quest.Teacher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quest.com.quest.R;

/**
 * Created by kumbh on 11-03-2017.
 */

public class AnswersRecyclerAdapter extends RecyclerView.Adapter<AnswersRecyclerAdapter.HolderView>{
    private Context ctx;


    public AnswersRecyclerAdapter(Context context){

        this.ctx =context;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_answer_key_layout,parent,false);
        return new HolderView(itemView);
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }



    public  class HolderView extends RecyclerView.ViewHolder{
        private TextView questionName;
        private  TextView answerForQuestion;
        public HolderView(View itemView) {

            super(itemView);
            questionName = (TextView) itemView.findViewById(R.id.tv_exam_question);
            answerForQuestion =(TextView) itemView.findViewById(R.id.tv_answer_attempted);
        }
    }
}
