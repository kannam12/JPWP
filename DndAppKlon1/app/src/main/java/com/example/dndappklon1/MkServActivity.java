package com.example.dndappklon1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class MkServActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mk_serv);
    }

    public void goNextServ(View view) throws IOException {
        EditText editText = (EditText)findViewById(R.id.nickETxt);
        String nick = editText.getText().toString();
        editText = (EditText)findViewById(R.id.portETxt);
        String port = editText.getText().toString();
        String servIP = checkIP();
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
    private String checkIP () {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wm != null;
        return Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
    }
}
