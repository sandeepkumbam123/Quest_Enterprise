package quest.com.quest.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import quest.com.quest.NetworkUtils.Constants;
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
            holder.questionName.setText(position+1+ " . "+examsBean.getQuestion());
            holder.answerForQuestion.setText("Student Answer : "+getAnswer(examsBean));
            String imageURL ="";
            if(examsBean.getUser_anser().equalsIgnoreCase(examsBean.getAnswer())){

                switch (examsBean.getUser_anser()){
                    case "option1" :
                        imageURL = examsBean.getIs_option1_image();
                        break;
                    case "option2" :
                        imageURL = examsBean.getIs_option1_image();
                        break;
                    case "option3" :
                        imageURL = examsBean.getIs_option1_image();
                        break;
                    case "option4" :
                        imageURL = examsBean.getIs_option1_image();
                        break;
                    default:
                        imageURL ="";
                        break;
                }

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


    private String getAnswer(PreviousExamsListModel.QuestionListBean examData){
        switch (examData.getUser_anser()){
            case "option1" :
                return examData.getOption1();
            case "option2" :
                return examData.getOption2();
            case "option3" :
                return examData.getOption3();
            case "option4" :
                return examData.getOption4();
            default:
                return "Not Attempted";
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
