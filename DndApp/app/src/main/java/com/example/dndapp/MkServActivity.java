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
    //FIXME: Załatwione. Zwracaj uwagę na id elementów. Inne w kodzie, inne w xml i nie działą.
    //TODO: Tak sobie jeszcze myślę, że skoro klient podaje port, to serwer też powinien mieć pole na to. Dzisiaj lub jutro dopiszę

    public void goNextServ(View view) {
        EditText editText = (EditText)findViewById(R.id.nickText);
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
