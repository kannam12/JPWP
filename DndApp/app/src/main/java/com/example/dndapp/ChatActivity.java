package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
//import android.widget.*;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    //tymczasowo wstawiam tu liste(array) do spinnera
    //String[] jezyki_array = {"Kanoniczny", "Elficki", "Krasnoludzki", "Opcja 4", "Opcja 5"};

    //robię obiekt mojego spinnera do wyboru języków
    //Spinner spinner = (Spinner) findViewById(R.id.spinner_jezyki);
    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            //android.R.layout.simple_spinner_item, jezyki_array);
    //final Spinner spinner = (Spinner)findViewById(R.id.spinner_jezyki);

    //spinner.setAdapter(adapter);
    //i czemu to jest na czerwono ta metoda setAdapter aaaaaaaaaaaaaaaaaaaaaaaaaaa ...>:C

    //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    //spinner.setAdapter(adapter);
}
