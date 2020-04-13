package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LogActivity extends AppCompatActivity {

    boolean[] avaliableLanguages = new boolean[7]; //Wartość domyślna false


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ///////////////////////////////////
        mkCommonAsDefault(avaliableLanguages);
    }

    public void goNextLog(View view) {
        EditText editText = (EditText)findViewById(R.id.nickETxt);
        String nick = editText.getText().toString();
        editText = (EditText)findViewById(R.id.hostIPETxt);
        String servIP = editText.getText().toString();
        editText = (EditText)findViewById(R.id.portETxt);
        String port = editText.getText().toString();

        Intent intent;
        switch(view.getId()) {
            case R.id.goInBtn:
                intent = new Intent(LogActivity.this, ChatActivity.class);
                intent.putExtra("is_host", false);
                intent.putExtra("nick", nick);
                intent.putExtra("servIP", servIP);
                intent.putExtra("port", port);

                intent.putExtra("avaliableLanguages", avaliableLanguages);
                startActivity(intent);
        }
    }

    public boolean[] mkCommonAsDefault(boolean[] avaliableLanguages){
        avaliableLanguages[0] = true;
        return avaliableLanguages;
    }

    public void onCheckboxClicked(View view) {
        switch(view.getId()) {
            case R.id.commonCB:
                avaliableLanguages[0] = ((CheckBox)view).isChecked();
                break;
            case R.id.lan2CB:
                avaliableLanguages[1] = ((CheckBox)view).isChecked();
                break;
            case R.id.lan3CB:
                avaliableLanguages[2] = ((CheckBox)view).isChecked();
                break;
            case R.id.lan4CB:
                avaliableLanguages[3] = ((CheckBox)view).isChecked();
                break;
            case R.id.lan5CB:
                avaliableLanguages[4] = ((CheckBox)view).isChecked();
                break;
            case R.id.lan6CB:
                avaliableLanguages[5] = ((CheckBox)view).isChecked();
                break;
            case R.id.lan7CB:
                avaliableLanguages[6] = ((CheckBox)view).isChecked();
                break;
        }
    }
}