package com.example.mobilesupervisor_supervisorapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    Button checkBox, checkBox2, checkBox3, checkBox4, checkBox5;
    public static int period;


    public HistoryFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history,
                container, false);
        checkBox = (Button)view.findViewById(R.id.checkBox);
        checkBox2 = (Button)view.findViewById(R.id.checkBox2);
        checkBox3 = (Button)view.findViewById(R.id.checkBox3);
        checkBox4 = (Button)view.findViewById(R.id.checkBox4);
        checkBox5 = (Button)view.findViewById(R.id.checkBox5);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                period = 1;
                Intent a = new Intent(getActivity(),HeartRateActivity.class);
                startActivity(a);
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                period = 2;
                Intent a = new Intent(getActivity(),HeartRateActivity.class);
                startActivity(a);
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                period = 3;
                Intent a = new Intent(getActivity(),HeartRateActivity.class);
                startActivity(a);
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                period = 4;
                Intent a = new Intent(getActivity(),HeartRateActivity.class);
                startActivity(a);
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                period = 5;
                Intent a = new Intent(getActivity(),HeartRateActivity.class);
                startActivity(a);
            }
        });
        return view;
    }

}
