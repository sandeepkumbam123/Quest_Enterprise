package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import quest.com.quest.R;
import quest.com.quest.SqliteDb.Database;
import quest.com.quest.databinding.FragmentQuestiongroupBinding;
import quest.com.quest.models.AttemptedQuestionModel;

/**
 * Created by skumbam on 08-03-2017.
 */

public class QuestionTagFragment extends Fragment implements QuestionFragment.AnswerSubmitted{

private FragmentQuestiongroupBinding mBinding;
    private AttemptedQuestionModel model;
    public static final String QUESTIONS_MODEL  = "questions_model";
    private Database mDB;

    private TextView questionText;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC;
    private RadioButton optionD;
    private long startTime;


    public static QuestionTagFragment getInstance(AttemptedQuestionModel model){
        QuestionTagFragment fragment = new QuestionTagFragment();
        Bundle b = new Bundle();
        b.putParcelable(QUESTIONS_MODEL ,model);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDB = new Database(getActivity());
        if(getArguments().getParcelable(QUESTIONS_MODEL)!=null){
            model = getArguments().getParcelable(QUESTIONS_MODEL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_questiongroup,container,false));

       startTime = System.currentTimeMillis();
        initViews();

        return mBinding.getRoot();
    }

    private void initViews() {
        questionText = mBinding.questionNumber;
        optionA = mBinding.optionA;
        optionB = mBinding.optionB;
        optionC = mBinding.optionC;
        optionD = mBinding.optionD;


        if(model!= null){
            questionText.setText(model.getQuestionNumber());
            optionA.setText(model.getOptionA());
            optionB.setText(model.getOptionB());
            optionC.setText(model.getOptionC());
            optionD.setText(model.getOptionD());
        }

    }

    @Override
    public void onanswerSubmitted() {
        int answer =0;
       long time = countTime(startTime,System.currentTimeMillis());
        if(optionA.isSelected()){
            answer =1;
        }
        else  if(optionB.isSelected()){
            answer =2;
        }
        else  if(optionC.isSelected()){
            answer =3;
        }
        else  if(optionD.isSelected()){
            answer =4;
        }
        else {
            answer =0;
            time =0;
        }
        if(model.getTimeTakentoAttempt()!=0){
            model.setTimeTakentoAttempt(model.getTimeTakentoAttempt()+(int)time);
        }
        else
        model.setTimeTakentoAttempt((int)time);
        model.setAttemptedAnswer(answer);
        mDB.updateAttemptedAnswer(mDB,model,model.getCriticality(),model.getExamDuration()
        ,model.getExamTitle(),model.getTotalMarks(),model.getNegativeMarks());

    }


    private long countTime(long start ,long end){
       return   (end - start);
    }
}
