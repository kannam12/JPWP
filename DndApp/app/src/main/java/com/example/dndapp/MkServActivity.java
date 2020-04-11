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
    //TODO: ja to mam szczęście absolutne do tych intencji xD jak się domyślasz gunwno działa xD

    public void goNextServ(View view) {
        EditText editText = (EditText)findViewById(R.id.nickTxt);
        String nick = editText.getText().toString();

        Intent intent;
        switch(view.getId()) {
            case R.id.goInServBtn:
                intent = new Intent(MkServActivity.this, ChatActivity.class);
                intent.putExtra("nick", nick);
                intent.putExtra("is_host", true);

                startActivity(intent);
        }
    }
}
