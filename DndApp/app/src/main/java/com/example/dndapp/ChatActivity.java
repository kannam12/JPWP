package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {
    String[] languageList;
    String[] languagePersonalList;
    ///////
    String[] messagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    ///////////
        infoNick();
        mkPersonalLanguageList();

        //Debugger
        //Toast toast = Toast.makeText(this, Integer.toString(languagePersonalList.length),  Toast.LENGTH_SHORT);
        //toast.show();

        addSpinner();
    }
    public void mkPersonalLanguageList(){
        //wczytywanie tablicy z values/string.xml
        languageList = getResources().getStringArray(R.array.languages_array);
        //wczytywanie tablicy zadeklarowanych w log activity języków
        boolean[] avaliableLanguages = getIntent().getBooleanArrayExtra("avaliableLanguages");
        int counter = 0;
        for (int i = 0; i < avaliableLanguages.length; i++) {
            if (avaliableLanguages[i]) {
                counter++;
            }
        }
        //adaptowanie jednego do drugiego - KARTA FUZJI
        languagePersonalList = new String[counter];
        counter = 0;
        for (int i = 0; i < avaliableLanguages.length; i++) {
            if (avaliableLanguages[i]) {
                languagePersonalList[counter] = languageList[i];
                counter++;
            }
        }
    }
    public void infoNick(){
        //pobieranie info o nicku z log activity
        String nick = getIntent().getStringExtra("nick");
        TextView textView = findViewById(R.id.nickInfoTxt2);
        textView.setText(nick);
    }
    public void addSpinner() {
        //wiadomo
        Spinner spinner = findViewById(R.id.spinner_jezyki);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,languagePersonalList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}