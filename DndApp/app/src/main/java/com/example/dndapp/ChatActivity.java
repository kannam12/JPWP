package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {

    String[] messagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    ////////////////////////////////////////////
        infoNick();
        addSpinner(mkPersonalLanguageList());

        //Debugger
        //Toast toast = Toast.makeText(this, Integer.toString(languagePersonalList.length),  Toast.LENGTH_SHORT);
        //toast.show();
    }

    public String[] mkPersonalLanguageList(){

        //wczytywanie tablicy z values/string.xml
        String[] allLanguageList = getResources().getStringArray(R.array.languages_array);

        //czy to host? jeśli tak to ma dostępne wszytskie języki, bo jest GM-em
        if (getIntent().getBooleanExtra("is_host", false)) {
           return allLanguageList;
        } else {
            //wczytywanie tablicy zadeklarowanych w log activity języków, jesli nie host
            boolean[] avaliableLanguages = getIntent().getBooleanArrayExtra("avaliableLanguages");
            int counter = 0;
            for (int i = 0; i < avaliableLanguages.length; i++) {
                if (avaliableLanguages[i]) {
                    counter++;
                }
            }
            //adaptowanie jednego do drugiego - KARTA FUZJI
            String[] languagePersonalList = new String[counter];
            counter = 0;
            for (int i = 0; i < avaliableLanguages.length; i++) {
                if (avaliableLanguages[i]) {
                    languagePersonalList[counter] = allLanguageList[i];
                    counter++;
                }
            }
            return languagePersonalList;
        }
    }

    public void infoNick(){
        //pobieranie info o nicku z log/serv activity
        String nick = getIntent().getStringExtra("nick");
        TextView textView = findViewById(R.id.nickInfoTxt2);
        textView.setText(nick);
    }
    public void addSpinner(String[] finalLanguageList) {
        //wiadomo
        //TODO: pytanie: czemu dwie linijki niżej jest na żółto?
        Spinner spinner = findViewById(R.id.spinner_jezyki);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,finalLanguageList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}