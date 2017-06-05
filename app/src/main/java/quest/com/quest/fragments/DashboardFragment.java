package quest.com.quest.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quest.com.quest.R;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {


    private FragmentDashboardBinding dataBinding;
    private Toolbar toolBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_dashboard,container,false));
        dataBinding.setFragment(this);
        toolBar =(Toolbar) getActivity().findViewById(R.id.toolbar);
        toolBar.setTitle("DashBoard");
//        setToolBar();
        toolBar.setNavigationIcon(R.drawable.ic_back);
        setHasOptionsMenu(false);
        return dataBinding.getRoot();
    }


    private void setToolBar() {

        ((DashBoardActivity) getActivity())
                .setToolbarTitle("Sandeep","Dashboard");
    }

    public  void startExam(View v){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,new PremiumExamsFragment()).commit();
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new StartTestFragment()).commit();
    }

    public void openPremiumExams(View v){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,new PremiumExamsFragment()).commit();
    }
}
