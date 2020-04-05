package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }
    //boolean[] knownLanguages = new boolean[7];

    public void goNextLog(View view) {

        Intent intent;
        switch(view.getId()) {
            case R.id.goInBtn:
                intent = new Intent(LogActivity.this, ChatActivity.class);
                //intent.putExtra('knownLanguages', knownLanguages [ ]);
                startActivity(intent);
                break;
        }

    }
}