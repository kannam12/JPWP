package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.Output;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatActivity extends AppCompatActivity{

    String[] messagesList;
    private EditText editMessage;
    private TextView chatMessages;
    private boolean loop = true;
    final Handler handler1 = new Handler();
    public static int port = 44100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    ////////////////////////////////////////////
        infoNick();
        addSpinner(mkPersonalLanguageList());
        //ImageButton sendButton = findViewById(R.id.sendBtn);
        editMessage = (EditText) findViewById(R.id.chatETxt);
        chatMessages = (TextView) findViewById(R.id.replyFromServer);
    }


    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.sendBtn:
                if (isHost()) {
                    startServerSocket();

                } else {
                    sendMessage(editMessage.getText().toString());
                }
                break;
        }
    }

    private void sendMessage (final String message) {
        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket("10.0.2.16", port);
                    chatMessages.setText("Failed 1");
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(message);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String str = input.readLine();
                    handler.post(new Runnable() {
                        @Override
                        public void run () {
                            String s = chatMessages.getText().toString();
                            if(str.trim().length() != 0) {
                                chatMessages.setText(s + "\nSerwer: " + str);
                            }
                        }

                    });
                    s.close();
                }catch (IOException e) {
                    String s = chatMessages.getText().toString();
                    chatMessages.setText(s + "Client failed");
                }
            }
        });
        thread.start();
    }


    private void startServerSocket () {
        Thread thread = new Thread(new Runnable() {
            private String stringDataTemp = null;
            @Override
            public void run() {
                try {
                    ServerSocket sS = new ServerSocket(port);
                    chatMessages.setText("Fail 1");
                    while (loop) {
                        Socket s = sS.accept();
                        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        PrintWriter output = new PrintWriter(s.getOutputStream());

                        stringDataTemp = input.readLine();
                        output.println("Response: " + stringDataTemp);
                        output.flush();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        updateUI(stringDataTemp);
                        if (stringDataTemp.equalsIgnoreCase("STOP")) {
                            loop = false;
                            output.close();
                            s.close();
                            break;
                        }
                        output.close();
                        s.close();

                    }
                    sS.close();
                } catch (IOException e) {
                    chatMessages.setText(e.toString());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void updateUI (final String stringDataTemp) {
        handler1.post(new Runnable() {
            @Override
            public void run() {
                String s = chatMessages.getText().toString();
                if (stringDataTemp.trim().length() != 0) {
                    chatMessages.setText(s + "\nGlient:" + stringDataTemp);
                }
            }
        });
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

    public boolean isHost() {
        Boolean isHost = getIntent().getBooleanExtra("is_host", false);
        return isHost;

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