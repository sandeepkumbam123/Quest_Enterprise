package quest.com.quest.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import quest.com.quest.R;
import quest.com.quest.models.FastestAnswersModel;

/**
 * Created by skumbam on 6/20/17.
 */

public class FastestAnswerModelAdapter extends BaseAdapter {

    private Context ctx;
    private List<FastestAnswersModel> models;

    public FastestAnswerModelAdapter(Context ctx , List<FastestAnswersModel> models) {
        this.ctx = ctx;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public FastestAnswersModel getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView mQuestion,mAnswer;
        FastestAnswersModel model = getItem(position);
        if(convertView ==  null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.fastest_answer_view ,null,false);
        }
        mAnswer = (TextView) convertView.findViewById(R.id.tv_answer);
        mQuestion = (TextView) convertView.findViewById(R.id.tv_question);

        mQuestion.setText(position+1 +" . "+model.getQuestion());
        mAnswer.setText("Answer : "+ model.getAnswer());


        return convertView;
    }
}
