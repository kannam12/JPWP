package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogActivity extends AppCompatActivity {

    String nick;
    boolean[] avaliableLanguages= new boolean[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //resetowanie języków po cofnieciu z czatu do logowania
        for (int i = 0; i>6; i++){
            avaliableLanguages[i] = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //onCheckboxMarked();
        //onCheckboxClicked();
    }

    public void goNextLog(View view) {
        EditText editText = (EditText)findViewById(R.id.nickTxt);
        nick = editText.getText().toString();

        Intent intent;
        switch(view.getId()) {
            case R.id.goInBtn:
                intent = new Intent(LogActivity.this, ChatActivity.class);
                intent.putExtra("nick", nick);
                intent.putExtra("avaliableLanguages", avaliableLanguages);
                startActivity(intent);
                System.out.println(getText(R.id.nickTxt));
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        switch(view.getId()) {
            case R.id.commonCB:
                avaliableLanguages[0] = true;
                break;
            case R.id.lan2CB:
                avaliableLanguages[1] = true;
                break;
            case R.id.lan3CB:
                avaliableLanguages[2] = true;
                break;
            case R.id.lan4CB:
                avaliableLanguages[3] = true;
                break;
            case R.id.lan5CB:
                avaliableLanguages[4] = true;
                break;
            case R.id.lan6CB:
                avaliableLanguages[5] = true;
                break;
            case R.id.lan7CB:
                avaliableLanguages[6] = true;
                break;

        }
    }
}