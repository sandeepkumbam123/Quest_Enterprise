package quest.com.quest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.Constants;
import quest.com.quest.R;
import quest.com.quest.Teacher.CreatedExamsFragment;

/**
 * Created by kumbh on 07-06-2017.
 */

public class CreateExamFragment extends Fragment {


    public static CreateExamFragment getInstance(){
        return  new CreateExamFragment();
    }

    private WebView createExamView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_create_exam,container,false);
        createExamView = (WebView) v.findViewById(R.id.web_view);


        // Enable Javascript
        WebSettings webSettings = createExamView.getSettings();
        createExamView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        webSettings.setJavaScriptEnabled(true);

        createExamView.loadUrl(Constants.QUEST_URL +Constants.LOGIN_URL);

        return v;



    }
}
