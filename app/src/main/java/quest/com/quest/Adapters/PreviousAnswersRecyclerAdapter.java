package quest.com.quest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quest.com.quest.R;
import quest.com.quest.Teacher.AnswersRecyclerAdapter;
import quest.com.quest.models.PreviousExamsListModel;
import quest.com.quest.models.StartExamModel;

/**
 * Created by kumbh on 10-06-2017.
 */

public class PreviousAnswersRecyclerAdapter extends RecyclerView.Adapter<PreviousAnswersRecyclerAdapter.HolderView> {
    private Context ctx;
    private PreviousExamsListModel examsBean;



    public PreviousAnswersRecyclerAdapter(Context context, PreviousExamsListModel examsBean) {
        this.ctx = ctx;
        this.examsBean = examsBean;

    }

    @Override
    public PreviousAnswersRecyclerAdapter.HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_answer_key_layout, parent, false);
        return new PreviousAnswersRecyclerAdapter.HolderView(itemView);
    }

    @Override
    public void onBindViewHolder(PreviousAnswersRecyclerAdapter.HolderView holder, int position) {
        if (getExamQuestion(position) != null) {
            PreviousExamsListModel.QuestionListBean examsBean = getExamQuestion(position);
            holder.questionName.setText(examsBean.getQuestion());
            holder.answerForQuestion.setText(examsBean.getUser_anser());

            if(examsBean.getUser_anser().equalsIgnoreCase(examsBean.getAnswer())){
                holder.answerForQuestion.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_tickcircle,0);
            }else {
                if(examsBean.getUser_anser().isEmpty() || examsBean.getUser_anser() == null){
                    holder.answerForQuestion.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
                else{
                    holder.answerForQuestion.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.teacher_wrong_ic,0);
                }
            }

        }

    }

    @Override
    public int getItemCount() {
        return examsBean != null ? examsBean.getQuestion_list().size() : 0;
    }

    private PreviousExamsListModel.QuestionListBean getExamQuestion(int position) {
        return examsBean != null ? examsBean.getQuestion_list().get(position) : null;
    }


    public class HolderView extends RecyclerView.ViewHolder {
        private TextView questionName;
        private TextView answerForQuestion;

        public HolderView(View itemView) {

            super(itemView);
            questionName = (TextView) itemView.findViewById(R.id.tv_exam_question);
            answerForQuestion = (TextView) itemView.findViewById(R.id.tv_answer_attempted);
        }
    }

}
