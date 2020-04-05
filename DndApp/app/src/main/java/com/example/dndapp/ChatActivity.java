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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Pobieranie listy języków
        //to wszystko poniżej miało być w onResume, ale jak chcędodać onResume to jest w klasie fragment i nie wiem na razie
        languageList = getResources().getStringArray(R.array.languages_array);

        //pobieranie z poprzedniej aktywności i wyświetlanie nicku
        //Intent intent;
        String nick = getIntent().getStringExtra("nick");
        TextView textView = findViewById(R.id.nickInfoTxt);
        textView.setText(nick);
        //pobieranie z poprzedniej aktywności dostępnych języków
        boolean[] avaliableLanguages = getIntent().getBooleanArrayExtra("avaliableLanguages");
        int counter = 0;
        for (int i = 0; i < avaliableLanguages.length; i++) {
            if (avaliableLanguages[i]) {
                counter++;
            }
        }
        languagePersonalList = new String[counter];
        counter = 0;
        for (int i = 0; i < avaliableLanguages.length; i++) {
            if (avaliableLanguages[i]) {
                languagePersonalList[counter] = languageList[i];
                counter++;
            }
        }
        //Debugger
        Toast toast = Toast.makeText(this, Integer.toString(languagePersonalList.length),  Toast.LENGTH_SHORT);
        toast.show();
    }

    public void addSpinner() {

        //https://developer.android.com/guide/topics/ui/controls/spinner
       /*Spinner spinner = (Spinner) findViewById(R.id.spinner_jezyki);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        Spinner spinner = (Spinner) findViewById(R.id.spinner_jezyki);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,languagePersonalList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //-----//
    }
}