package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goNextMain(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.goAheadBtn:
                intent = new Intent(MainActivity.this, LogActivity.class);
                startActivity(intent);
                break;
            case R.id.serverBtn:
                intent = new Intent(MainActivity.this, MkServActivity.class);
                startActivity(intent);
                break;
            case R.id.appInfoBtn:
                intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
                break;

        }
    }
}
