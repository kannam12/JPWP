package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //pobieranie z poprzedniej aktywności i wyświetlanie nicku
        //Intent intent;
        String nick = getIntent().getStringExtra("nick");
        TextView textView = findViewById(R.id.nickInfoTxt);
        textView.setText(nick);

        //pobieranie z poprzedniej aktywności dostępnych języków
        boolean[] avaliableLanguages = getIntent().getBooleanArrayExtra("avaliableLanguages");
        
    }

    public void addSpinner() {

        //https://developer.android.com/guide/topics/ui/controls/spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_jezyki);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //-----//
    }
}