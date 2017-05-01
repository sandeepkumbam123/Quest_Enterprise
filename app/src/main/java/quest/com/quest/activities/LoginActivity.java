package quest.com.quest.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Teacher.SearchStudentFragment;
import quest.com.quest.Teacher.TeacherDashBoardActivity;
import quest.com.quest.databinding.ActivitityLoginBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.LoginResponseModel;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by skumbam on 23-02-2017.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ActivitityLoginBinding dataBinding;
    private boolean isTeacher = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activitity_login);
        initViews(dataBinding);
    }

    private void initViews(ActivitityLoginBinding dataBinding) {
        dataBinding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_login:
                if(isNetworkConnected(LoginActivity.this)){
                     if(validateFields()) {
                         loginHit(inputParams());
                         dataBinding.pbProgresbar.setVisibility(View.VISIBLE);
                     }// write the action to be performed whenever network is connected

                }
                else {
                    //show a dialog to connect to a network
                    QuestDialog.showOkDialog(LoginActivity.this,getString(R.string.network_error),getString(R.string.network_error_message));
                }
                break;
        }
    }

    private Map<String,String> inputParams() {
        HashMap<String,String> params = new HashMap<>();
        String email = dataBinding.inputUsername.getText().toString().trim();
        String password = dataBinding.inputPassword.getText().toString().trim();
        params.put(ApiConstants.EMAIL,email);
        params.put(ApiConstants.PASSWORD,password);

        dataBinding.inputUsername.setText("");
        dataBinding.inputPassword.setText("");
        return params;

    }

    private boolean validateFields() {
        if(dataBinding.inputUsername.getText().toString().isEmpty()||dataBinding.inputPassword.getText().toString().isEmpty()){
            QuestDialog.showOkDialog(this,"Error","Please Enter username/password");
            return false;
        }
         else return true;
    }

    private void loginHit(Map<String,String> params) {

        new RetrofitRequestHandler(this).loginUser(RequestConstants.REQ_LOGIN_USER, params, new RetrofitAPIRequests.ResponseListener<LoginResponseModel>() {
            @Override
            public void onSuccess(int requestId, Headers headers, LoginResponseModel response) {
                dataBinding.pbProgresbar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this,response.getStatus()+" status , success : "+response.is_$Issuccess127(), Toast.LENGTH_SHORT).show();
               if(response.is_$Issuccess127()) {
                   if (!isTeacher)
                       navigatetoNextActivity(LoginActivity.this, new DashBoardActivity());
                   else
                       navigatetoNextActivity(LoginActivity.this, new TeacherDashBoardActivity());
               }
                else{
                   QuestDialog.showOkDialog(LoginActivity.this,"Error",response.getStatus());
               }
            }

            @Override
            public void onFailure(int requestId, Throwable error) {
//                dataBinding.pbProgresbar.setVisibility(View.INVISIBLE);
//                QuestDialog.showOkDialog(LoginActivity.this,"Connectivity Error",error.getMessage());
                navigatetoNextActivity(LoginActivity.this, new DashBoardActivity());

                Log.d(TAG,error+"");
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
