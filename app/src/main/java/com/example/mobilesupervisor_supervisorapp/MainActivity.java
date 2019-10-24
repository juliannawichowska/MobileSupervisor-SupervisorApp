package com.example.mobilesupervisor_supervisorapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionBar and its title
        actionBar = getSupportActionBar();


        //bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        actionBar.setTitle("Results");
        ResultsFragment fragment3 = new ResultsFragment();
        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
        ft3.replace(R.id.content,fragment3,"");
        ft3.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_message:
                    //start message activity
                    actionBar.setTitle("Message");
                    Intent a = new Intent(MainActivity.this,MessageActivity.class);
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

}
