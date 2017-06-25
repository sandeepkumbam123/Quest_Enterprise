package quest.com.quest.Teacher;

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
import quest.com.quest.models.ListofExams;
import quest.com.quest.models.StartExamModel;

/**
 * Created by kumbh on 11-03-2017.
 */

public class AnswersRecyclerAdapter extends RecyclerView.Adapter<AnswersRecyclerAdapter.HolderView>{
    private Context ctx;
    private StartExamModel examsBean;




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
    public void onBindViewHolder(final HolderView holder, int position) {
        if(getExamQuestion(position) != null){
            StartExamModel.QuestionModel examsBean = getExamQuestion(position);
            holder.questionName .setText(position+1 +" . "+ examsBean.getQuestion());
            if(!examsBean.isImage().equalsIgnoreCase("no")){
                Glide.with(holder.questionName.getContext())
                        .load((Constants.IMAGE_URL +examsBean.isImage()))
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>(100,100) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                holder.questionName.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(holder.answerForQuestion.getResources(),resource), null, null );
                            }
                        });
            }

            switch (examsBean.getCorrectAnswer()){
                case  "option1" :
                    holder.answerForQuestion.setText("Answer " + examsBean.getOptionA());
                    if(!examsBean.isHasOption1Image().equalsIgnoreCase("no")){
                        Glide.with(holder.answerForQuestion.getContext())
                                .load((Constants.IMAGE_URL +examsBean.isHasOption1Image()))
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>(100,100) {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                        holder.answerForQuestion.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(holder.answerForQuestion.getResources(),resource), null, null );
                                    }
                                });
                    }
                    break;
                case  "option2" :
                    holder.answerForQuestion.setText("Answer " + examsBean.getOptionB());
                    if(!examsBean.isHasOption1Image().equalsIgnoreCase("no")){
                        Glide.with(holder.answerForQuestion.getContext())
                                .load((Constants.IMAGE_URL +examsBean.isHasOption2Image()))
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>(100,100) {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                        holder.answerForQuestion.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(holder.answerForQuestion.getResources(),resource), null, null );
                                    }
                                });
                    }
                    break;
                case  "option3" :
                    holder.answerForQuestion.setText("Answer " + examsBean.getOptionC());
                    if(!examsBean.isHasOption1Image().equalsIgnoreCase("no")){
                        Glide.with(holder.answerForQuestion.getContext())
                                .load((Constants.IMAGE_URL +examsBean.isHasOption3Image()))
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>(100,100) {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                        holder.answerForQuestion.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(holder.answerForQuestion.getResources(),resource), null, null );
                                    }
                                });
                    }
                    break;
                case  "option4" :
                    holder.answerForQuestion.setText("Answer " + examsBean.getOptionD());
                    if(!examsBean.isHasOption1Image().equalsIgnoreCase("no")){
                        Glide.with(holder.answerForQuestion.getContext())
                                .load((Constants.IMAGE_URL +examsBean.isHasOption4Image()))
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>(100,100) {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                        holder.answerForQuestion.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(holder.answerForQuestion.getResources(),resource), null, null );
                                    }
                                });
                    }
                    break;
                default:
                    holder.answerForQuestion.setText("Answer " + examsBean.getOptionA());
                    if(!examsBean.isHasOption1Image().equalsIgnoreCase("no")){
                        Glide.with(holder.answerForQuestion.getContext())
                                .load((Constants.IMAGE_URL +examsBean.isHasOption1Image()))
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>(100,100) {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                        holder.answerForQuestion.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(holder.answerForQuestion.getResources(),resource), null, null );
                                    }
                                });
                    }
                    break;

            }

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
