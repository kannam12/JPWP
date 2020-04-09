package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LogActivity extends AppCompatActivity {

    String nick;
    boolean[] avaliableLanguages = new boolean[7]; //Wartość domyślna false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ///////////////////////////////////
        mkCommonAsDefault(avaliableLanguages);
    }

    public void goNextLog(View view) {
        EditText editText = (EditText)findViewById(R.id.nickTxt);
        nick = editText.getText().toString();

        Intent intent;
        switch(view.getId()) {
            case R.id.goInBtn:
                intent = new Intent(LogActivity.this, ChatActivity.class);
                //intent.putExtra("is_host", false);
                intent.putExtra("nick", nick);
                //avaliableLanguages[0] = ((CheckBox)view).isChecked();

                intent.putExtra("avaliableLanguages", avaliableLanguages);
                startActivity(intent);
                // break;
        }
    }

    public boolean[] mkCommonAsDefault(boolean[] avaliableLanguages){
        //kanoniczny jako defaultowy język
        avaliableLanguages[0] = true;
        return avaliableLanguages;
    }

    public void onCheckboxClicked(View view) {
        switch(view.getId()) {
            case R.id.commonCB:
                avaliableLanguages[0] = ((CheckBox)view).isChecked();
                //Toast toast = Toast.makeText(this, Boolean.toString(avaliableLanguages[0]),  Toast.LENGTH_SHORT);
                //toast.show();
                break;
            case R.id.lan2CB:
                avaliableLanguages[1] = ((CheckBox)view).isChecked();
                //Toast toast1 = Toast.makeText(this, Boolean.toString(avaliableLanguages[1]),  Toast.LENGTH_SHORT);
                //toast1.show();
                break;
            case R.id.lan3CB:
                avaliableLanguages[2] = ((CheckBox)view).isChecked();
                //Toast toast2 = Toast.makeText(this, Boolean.toString(avaliableLanguages[2]),  Toast.LENGTH_LONG);
                //toast2.show();
                break;
            case R.id.lan4CB:
                avaliableLanguages[3] = ((CheckBox)view).isChecked();
                //Toast toast3 = Toast.makeText(this, Boolean.toString(avaliableLanguages[3]),  Toast.LENGTH_SHORT);
                //toast3.show();
                break;
            case R.id.lan5CB:
                avaliableLanguages[4] = ((CheckBox)view).isChecked();
                //Toast toast4 = Toast.makeText(this, Boolean.toString(avaliableLanguages[4]),  Toast.LENGTH_SHORT);
                //toast4.show();
                break;
            case R.id.lan6CB:
                avaliableLanguages[5] = ((CheckBox)view).isChecked();
                //Toast toast5 = Toast.makeText(this, Boolean.toString(avaliableLanguages[5]),  Toast.LENGTH_SHORT);
                //toast5.show();
                break;
            case R.id.lan7CB:
                avaliableLanguages[6] = ((CheckBox)view).isChecked();
                //Toast toast6 = Toast.makeText(this, Boolean.toString(avaliableLanguages[6]),  Toast.LENGTH_SHORT);
                //toast6.show();
                break;
        }
    }
}