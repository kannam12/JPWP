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

        Intent intent;
        switch(view.getId()) {
            case R.id.goInServBtn:
                intent = new Intent(MkServActivity.this, ChatActivity.class);
                //intent.putExtra("nick", nick);
                //avaliableLanguages[0] = ((CheckBox)view).isChecked();
                intent.putExtra("is_host", true);

                //intent.putExtra("avaliableLanguages", avaliableLanguages);
                startActivity(intent);
                // break;
        }
    }
}
