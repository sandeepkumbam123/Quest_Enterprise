package quest.com.quest.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import quest.com.quest.R;
import quest.com.quest.SqliteDb.Database;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.fragments.DashboardFragment;
import quest.com.quest.fragments.QuestionTagFragment;
import quest.com.quest.models.AttemptedQuestionModel;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DashBoardActivity extends BaseActivity
      /*  implements NavigationView.OnNavigationItemSelectedListener */ implements QuestionTagFragment.DataChangedListener{
    private FrameLayout containerFrame ;
    private Toolbar toolbar;
    private TextView userName;
    private TextView fragmentName;
    public  AttemptedQuestionModel attemptedQuestionModel;
    public Database mDB;


    @Override
    protected void onStart() {
        super.onStart();
        mDB = new Database(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_dash_board);


        //init toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userName = (TextView) findViewById(R.id.tv_user_name);
        fragmentName = (TextView) findViewById(R.id.tv_header);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //load the dashboard fragment to show the details of the user
        containerFrame =(FrameLayout)findViewById(R.id.fl_container);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,new DashboardFragment()).commit();

    }

    @Override
    public void onBackPressed() {

        String title = fragmentName.getText().toString();
        if (title.equalsIgnoreCase("Exam")) {

            QuestDialog.showDialogwithPostiveNegativeButtons(DashBoardActivity.this,
                    "Alert", "Do you want to exit this Exam?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fl_container,new DashboardFragment())
                                    .commit();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }else if(title.equalsIgnoreCase("Dashboard")){
            finish();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container,new DashboardFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    public  AttemptedQuestionModel getAttemptedModel(){
        return attemptedQuestionModel;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public  void setToolbarTitle(String userName,String fragmentName){
        this.userName.setText(userName);
        this.fragmentName.setText(fragmentName);
    }

    @Override
    public void onDataChanged(AttemptedQuestionModel model ,String examId) {
        mDB.updateAttemptedAnswer(mDB,model,model.getCriticality(),model.getExamDuration()
                ,model.getExamTitle(),model.getTotalMarks(),model.getNegativeMarks() , examId);
        attemptedQuestionModel = model;
    }
}
