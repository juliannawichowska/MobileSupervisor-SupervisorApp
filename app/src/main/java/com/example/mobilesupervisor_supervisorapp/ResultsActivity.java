package com.example.mobilesupervisor_supervisorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "SMARTBAND";
    ActionBar actionBar;
    public long stepsToday;
    final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = System.identityHashCode(this) & 0xFFFF;

    //firebase auth
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //ActionBar and its title
        actionBar = getSupportActionBar();

        //firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();


        //Bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        //Start from results fragment
        actionBar.setTitle("Results");
        //ResultsFragment fragment3 = new ResultsFragment();
        //FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
        //ft3.replace(R.id.content,fragment3,"");
        //ft3.commit();

        FirebaseMessaging.getInstance().subscribeToTopic("supervisorMessages")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.v("","success");
                        }
                        Log.v("", "failure");
                    }
                });
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Results");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot dsnap : ds.getChildren()) {
                        StepsModel sm = dsnap.getValue(StepsModel.class);
                       // HeartRateModel hr = dsnap.getValue(HeartRateModel.class);
                       // Log.e(TAG, "value"+hr.Pulse);
                        if(sm.Date.equals(strDate)) {
                            stepsToday = sm.Steps;
                            show_steps();
                        }
                }
            }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error occured");
            }
        });
    }
    public void show_steps() {
        TextView stepsCount = (TextView) findViewById(R.id.textView);
        if (stepsToday != 0) {
            Log.e(TAG, "CO SIE DZIEJE" + stepsToday);
            stepsCount.setText("Ilość kroków: " + String.valueOf(stepsToday));
        } else {
            stepsCount.setText("Nie odczytano kroków");
        }
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
                        case R.id.navigation_Messenger:
                            Uri uri = Uri.parse("https://www.messenger.com/t"); // missing 'http://' will cause crashed
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        case R.id.navigation_results:
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

    //handle logout click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_logout){
            FirebaseAuth.getInstance().signOut();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user == null) {
                startActivity(new Intent(ResultsActivity.this, MainActivity.class));
            }


        }
        return super.onOptionsItemSelected(item);
    }
}



