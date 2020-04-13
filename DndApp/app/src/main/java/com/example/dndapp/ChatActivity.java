package com.example.dndapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatActivity extends AppCompatActivity{

    String nick;
    int selectedLanguageID;



    //String[] messagesList;
    private EditText editMessage;
    private TextView chatMessages;
    private boolean loop = true;
    //final Handler handler1 = new Handler();
    public static int port = 44100;

    public Socket socket;
    public PrintWriter out;
    public BufferedReader in;
    public ServerSocket server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    ////////////////////////////////////////////
        infoNick();
        addSpinner(mkPersonalLanguageList());
    ///////////////////////////
        //ImageButton sendButton = findViewById(R.id.sendBtn);
        editMessage = (EditText) findViewById(R.id.chatETxt);
        chatMessages = (TextView) findViewById(R.id.replyFromServer);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isHost()) {
                    servListenSocket();
                } else {
                    cliListenSocket();
                }
            }

        });
        thread.start();
    }
/////////////////////////////////////////////////////////
    //Metody klienta
    public void cliListenSocket () {
        try {

            socket = new Socket ("10.0.2.16", port);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(loop){
                String line = in.readLine();
                chatMessages.append(line);
            }

        } catch (UnknownHostException e){
            //Log.d("tag", e.getMessage());
            Log.d("tag", "mesydz");
        } catch (IOException e){
            //Log.d("tag", e.getMessage());
            Log.d("tag", "mesydz");
        }
    }
    public void onClick (View view) {
        switch (view.getId()){
            case R.id.sendBtn:
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                    if (isHost()) {

                    } else {
                        String chatMessage = editMessage.getText().toString();
                        // WYSYŁANIE WIADOMOŚCI W FORMACIE: MSG 2 Krasnoludzki NickFury Siema kto PL?
                        chatMessage = "MSG " + selectedLanguageID + " " + nick + " " + chatMessage;

                        out.println(chatMessage);
                        editMessage.setText("");
                    }}
                });
                thread.start();
                break;
        }
    }
//////////////////////////////////////////////
    //Metody serwera
    public void servListenSocket () {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            Log.d("tag", e.getMessage());
        }
        while (true) {
            CliWork w;
            try {
                w = new CliWork(server.accept(), chatMessages);
                Thread t = new Thread(w);
                t.start();
            } catch (IOException e) {
                Log.d("tag", e.getMessage());
            }
        }
    }

//////////////////////////////////////////////////
/*
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

                    Socket s = sS.accept();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    PrintWriter output = new PrintWriter(s.getOutputStream());
                    while (loop) {


                        stringDataTemp = input.readLine();
                        output.println("Response: " + stringDataTemp);
                        output.flush();

                        Thread.sleep(1000);

                        updateUI(stringDataTemp);
                        if (stringDataTemp.equalsIgnoreCase("STOP")) {
                            loop = false;
                            output.close();
                            s.close();
                            break;
                        }

                    }
                    output.close();
                    s.close();
                    sS.close();
                } catch (Exception e) {
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
*/

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
        nick = getIntent().getStringExtra("nick");
        TextView textView = findViewById(R.id.nickInfoTxt2);
        textView.setText(nick);
    }
    public void addSpinner(final String[] finalLanguageList) {
        //wiadomo
        //TODO: pytanie: czemu dwie linijki niżej jest na żółto?
        //FIXME: naprawiodend, a temu: "That's because ArrayAdapter expects you to specify which type of object it will manipulate."

        Spinner spinner = findViewById(R.id.spinner_jezyki);

    ////////Dzielenie na ID języka i jego nazwę/////////
        final int[] finalLanguageID = new int[finalLanguageList.length];
        final String[] finalLanguageShow = new String[finalLanguageList.length];

        for (int i = 0; i < finalLanguageList.length; i++){
            String[] tmp = finalLanguageList[i].split("@", 2);
            finalLanguageID[i] = Integer.parseInt(tmp[0]);
            finalLanguageShow[i] = tmp[1];
        }
    //////////////////////////////////////////////////
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, finalLanguageShow);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int choiceID, long position) {
                selectedLanguageID = finalLanguageID[(int)position];
                Toast.makeText(ChatActivity.this, "Wybrałeś język: " + (finalLanguageShow[choiceID]) +", zapisano ID: " + selectedLanguageID, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                selectedLanguageID = finalLanguageID[0];
            }
        });
    }
}