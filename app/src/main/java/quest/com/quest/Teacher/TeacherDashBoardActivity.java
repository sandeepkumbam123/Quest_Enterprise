package quest.com.quest.Teacher;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import quest.com.quest.Adapters.DrawerMenuAdapter;
import quest.com.quest.Adapters.DrawerModel;
import quest.com.quest.R;
import quest.com.quest.activities.BaseActivity;
import quest.com.quest.activities.LoginActivity;
import quest.com.quest.databinding.ActivityTeacherDashBoardBinding;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TeacherDashBoardActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ActivityTeacherDashBoardBinding dataBinding;
    private static final String TAG = TeacherDashBoardActivity.class.getSimpleName();
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_teacher_dash_board);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teacher_frame, CreatedExamsFragment.getInstance(TeacherDashBoardActivity.this, new Bundle()))
                .commit();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                drawerView.requestLayout();
                drawerView.bringToFront();
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    private void initView() {




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_home:
                selectItem(0);
                break;
            case R.id.nav_previous_exams:
                selectItem(1);
                break;
            case R.id.nav_student_Data:
                selectItem(2);
                break;
            case R.id.nav_logout:
                selectItem(3);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = CreatedExamsFragment.getInstance(TeacherDashBoardActivity.this, new Bundle());
                break;
            case 1:
                fragment = StudentPreviousExamsFragment.getInstance(TeacherDashBoardActivity.this, new Bundle());
                break;
            case 2:
                fragment = SearchStudentFragment.getInstance(TeacherDashBoardActivity.this, new Bundle());
                break;
            case 3:
navigatetoNextActivity(this,new LoginActivity());

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.teacher_frame, fragment).commit();


        } else {
            Log.e(TAG, "Error in creating fragment");
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



}
