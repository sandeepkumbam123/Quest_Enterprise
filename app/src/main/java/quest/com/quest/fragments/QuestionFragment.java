package quest.com.quest.fragments;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import quest.com.quest.R;
import quest.com.quest.activities.BaseActivity;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.FragmentQuestionBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.QuestionModel;

/**
 * Created by skumbam on 03-03-2017.
 */

public class QuestionFragment extends Fragment {
    private static String TAG = QuestionFragment.class.getSimpleName();
    private FragmentQuestionBinding dataBinding;
    QuestionModel qm;
    public final ObservableField<String> time =new ObservableField<>();
    CountDownTimer countDownTimer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBar();
        setHasOptionsMenu(true);

    }

    private void setToolBar() {

        ((DashBoardActivity) getActivity()).setToolbarTitle("Sandeep","Exam");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_question,null,false));
        dataBinding.setFragment(this);


        createQuestionFragment();

        qm= new QuestionModel("Physics",5,12,120000);
        dataBinding.setData(qm);

        countDownTimer(qm.getQuestionDuration(),dataBinding.timerCountdown);
        return dataBinding.getRoot();
    }


    public void getNextQuestion(View v){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,  R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.fl_question_container,QuestionTagFragment.getInstance());
        transaction.commit();
//        countDownTimer.onFinish();
//       countDownTimer.cancel();
//        countDownTimer(12000,dataBinding.timerCountdown);
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new QuestionFragment()).commit();
    }

    public void getPreviousQuestion(View v){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.fl_question_container, QuestionTagFragment.getInstance());
        transaction.commit();
//        countDownTimer.onFinish();
//        countDownTimer.cancel();
//        countDownTimer(12000,dataBinding.timerCountdown);
//        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fade_in,R.animator.fade_out).replace(R.id.fl_container,new QuestionFragment()).commit();
    }

    public  void createQuestionFragment(){
        if(countDownTimer!=null){
            countDownTimer.cancel();
//            countDownTimer.onFinish();
        }
//        countDownTimer(12000,dataBinding.timerCountdown);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_question_container, QuestionTagFragment.getInstance()).commit();
    }



    public void submitResult(View v){
        countDownTimer.cancel();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ResultsFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }


    public void submitResult(){
        countDownTimer.cancel();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ResultsFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }
    public  void countDownTimer(int timer, final TextView textView){
        countDownTimer = new CountDownTimer(timer, 1000) {

            public void onTick(long millis) {
                long second = (millis / 1000) % 60;
                long minute = (millis / (1000 * 60)) % 60;
                long hour = (millis / (1000 * 60 * 60)) % 24;
                String time ="";
                if(hour==0){
                    time = String.format("%02d:%02d", minute, second);
                }else if(hour==0 && minute==0 && second ==0){
                    submitResult();

                }
                else {
                    time = String.format("%02d:%02d:%02d", hour, minute, second);
                }

                textView.setText(time);
            }

            public void onFinish() {
//                getNextQuestion(dataBinding.btnPreviousquestion);
                submitResult();
            }
        };

        countDownTimer.start();
    }


}
