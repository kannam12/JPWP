package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    // to jest ten magiczny moment kiedy dzbanię, bo nie chce się przejsc do ekranu czatu .... i wywala apke
    public void goNextLog(View view) {

        Intent intent;
        switch(view.getId()) {
            case R.id.goInBtn:
                intent = new Intent(LogActivity.this, ChatActivity.class);
                startActivity(intent);
                break;
        }

    }
}