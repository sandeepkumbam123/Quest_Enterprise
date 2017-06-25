package quest.com.quest.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import quest.com.quest.R;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.fragments.QuestionFragment;
import quest.com.quest.models.AttemptedQuestionModel;

/**
 * Created by skumbam on 6/25/17.
 */

public class QuestionsListPopupMenu extends DialogFragment {

    private RecyclerView recyclerView;
    private QuestionListAdapter adapter;
    private int questionsCount;
    private   questionNumberClick listener;


    public  QuestionsListPopupMenu(questionNumberClick listener ,int questionsCount){
        super();
        this.listener = listener;
        this.questionsCount = questionsCount;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_list_dialog,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.question_list_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));

        adapter = new QuestionListAdapter(getActivity(),questionsCount);
        recyclerView.setAdapter(adapter);

        getDialog().setTitle("Questions");

        return view;
    }




    private class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.HolderView>{

        private Context ctx;
        private int questionCount;


        public QuestionListAdapter(Context ctx ,
                                   int questionCount ){
            this.ctx = ctx;
            this.questionCount = questionCount;

        }

        @Override
        public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(ctx).inflate(R.layout.question_number_view, parent, false);

            return new HolderView(view);
        }

        @Override
        public void onBindViewHolder(HolderView holder, final int position) {
            holder.mQuestionNumber.setText(position+1 +"");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return questionCount;
        }

        public class HolderView extends RecyclerView.ViewHolder{
            private TextView mQuestionNumber;

            public HolderView(View itemView) {
                super(itemView);
                mQuestionNumber = (TextView) itemView.findViewById(R.id.question_number_dialog);

            }
        }
    }

    public interface questionNumberClick{
        void onClick(int position);
    }

}
