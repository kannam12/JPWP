package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MkServActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mk_serv);
    }

    public void goNextServ(View view) {
        EditText editText = (EditText)findViewById(R.id.nickETxt);
        String nick = editText.getText().toString();
        editText = (EditText)findViewById(R.id.portETxt);
        String port = editText.getText().toString();
        //TODO: pobieranie IP servera (opcjonalnie jako osoobna funckja String, być może w klasie Network)
        String servIP = "10.0.0.16";

        Intent intent;
        switch(view.getId()) {
            case R.id.goInServBtn:
                intent = new Intent(MkServActivity.this, ChatActivity.class);
                intent.putExtra("nick", nick);
                intent.putExtra("port", port);
                intent.putExtra("servIP", servIP);
                intent.putExtra("is_host", true);

                startActivity(intent);
        }
    }
}
