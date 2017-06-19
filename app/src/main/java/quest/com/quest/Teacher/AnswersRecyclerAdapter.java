package quest.com.quest.Teacher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quest.com.quest.R;
import quest.com.quest.models.ListofExams;
import quest.com.quest.models.StartExamModel;

/**
 * Created by kumbh on 11-03-2017.
 */

public class AnswersRecyclerAdapter extends RecyclerView.Adapter<AnswersRecyclerAdapter.HolderView>{
    private Context ctx;
    private StartExamModel examsBean;


    public AnswersRecyclerAdapter(Context context){

        this.ctx =context;
    }

    public AnswersRecyclerAdapter(Context context , StartExamModel examsBean){
        this.ctx = ctx;
        this.examsBean = examsBean;

    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_answer_key_layout,parent,false);
        return new HolderView(itemView);
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {
        if(getExamQuestion(position) != null){
            StartExamModel.QuestionModel examsBean = getExamQuestion(position);
            holder.questionName .setText(position+1 +" . "+ examsBean.getQuestion());
            holder.answerForQuestion.setText("Answer " + examsBean.getCorrectAnswer());
        }

    }

    @Override
    public int getItemCount() {
        return examsBean != null ? examsBean.getQuestionsList().size() : 0 ;
    }

    private StartExamModel.QuestionModel getExamQuestion(int position){
        return examsBean != null ? examsBean.getQuestionsList().get(position) : null ;
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
