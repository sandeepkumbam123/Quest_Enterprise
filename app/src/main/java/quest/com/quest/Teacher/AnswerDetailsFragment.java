package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quest.com.quest.R;
import quest.com.quest.databinding.AnswersListLayoutBinding;

/**
 * Created by kumbh on 26-03-2017.
 */

public class AnswerDetailsFragment extends Fragment {
    private AnswersListLayoutBinding dataBinding;
    private RecyclerView recyclerAnswersList;
    private AnswersRecyclerAdapter adapter;

    public static AnswerDetailsFragment getInstance(Activity activity, Bundle bundle){
        AnswerDetailsFragment fragment = new AnswerDetailsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.answers_list_layout,container,false));
        recyclerAnswersList = dataBinding.answersList;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerAnswersList.setLayoutManager(layoutManager);
        adapter = new AnswersRecyclerAdapter(getActivity());
        recyclerAnswersList.setAdapter(adapter);
        return dataBinding.getRoot();
    }
}
