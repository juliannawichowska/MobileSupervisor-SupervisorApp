package com.example.mobilesupervisor_supervisorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultsActivity extends AppCompatActivity {

    ActionBar actionBar;
    final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = System.identityHashCode(this) & 0xFFFF;

    //firebase auth
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .build();
        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this,
                    GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                    account,
                    fitnessOptions
            );

        }
        else {
            accessGoogleFit();
        }

        //ActionBar and its title
        actionBar = getSupportActionBar();


        //Bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        //Start from results fragment
        actionBar.setTitle("Results");
        ResultsFragment fragment3 = new ResultsFragment();
        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
        ft3.replace(R.id.content,fragment3,"");
        ft3.commit();



    }

    private void accessGoogleFit() {
    }



    //The bottom navigation method
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_message:
                            //start message activity
                            actionBar.setTitle("Message");
                            Intent a = new Intent(ResultsActivity.this,MessageActivity.class);
                            startActivity(a);
                            return true;
                        case R.id.navigation_video:
                            //video fragment transaction
                            actionBar.setTitle("Video");
                            VideoFragment fragment2 = new VideoFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content,fragment2,"");
                            ft2.commit();
                            return true;
                        case R.id.navigation_results:
                            //results fragment transaction
                            actionBar.setTitle("Results");
                            ResultsFragment fragment3 = new ResultsFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content,fragment3,"");
                            ft3.commit();
                            return true;
                        case R.id.navigation_camera:
                            //camera fragment transaction
                            actionBar.setTitle("Camera");
                            CameraFragment fragment4 = new CameraFragment();
                            FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                            ft4.replace(R.id.content,fragment4,"");
                            ft4.commit();
                            return true;
                        case R.id.navigation_history:
                            //history fragment transaction
                            actionBar.setTitle("History");
                            HistoryFragment fragment5 = new HistoryFragment();
                            FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                            ft5.replace(R.id.content,fragment5,"");
                            ft5.commit();
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu
        getMenuInflater().inflate(R.menu.menu_up, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //handle lohout click

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_logout){
            firebaseAuth.signOut();
                 }



        return super.onOptionsItemSelected(item);
    }
}



