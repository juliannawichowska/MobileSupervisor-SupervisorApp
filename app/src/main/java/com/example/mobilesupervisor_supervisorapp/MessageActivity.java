package com.example.mobilesupervisor_supervisorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MessageActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_message:
                    Intent a = new Intent(MessageActivity.this,MessageActivity.class);
                    startActivity(a);
                    break;
                case R.id.navigation_video:
                    mTextMessage.setText(R.string.title_video);
                    return true;
                case R.id.navigation_camera:
                    mTextMessage.setText(R.string.title_camera);
                    return true;
                case R.id.navigation_history:
                    mTextMessage.setText(R.string.title_history);
                    return true;
                case R.id.navigation_results:
                    mTextMessage.setText(R.string.title_results);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
