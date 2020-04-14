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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity{

    String nick;
    int port;
    String serverIP;
    int selectedLanguageID;
    boolean[] avaliableLanguages;

    private EditText editMessage;
    private TextView chatMessages;
    private boolean loop = true;

    public Socket socket;
    public PrintWriter out;
    public BufferedReader in;
    public ServerSocket server;

    public List<CliWork> listOfClients = new ArrayList<>();
    private static List<PrintWriter> listOfCliOuts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    ////////////////////////////////////////////
        extraInfo();
        Toast toast = Toast.makeText(ChatActivity.this, serverIP, Toast.LENGTH_SHORT);
        toast.show();
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
            socket = new Socket (serverIP, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(loop){
                String line = in.readLine();
                /////tu obróbka przychodzącej wiadomości w formacie: MSG 1 Ja EloKoledzy! - w przyszłości funckaj

                String[] tmp = line.split(" ",4);
                String statement = tmp[0];      //MSG
                String receivedLanguageID = tmp[1];
                String senderNick = tmp[2];
                String message = tmp[3];

                int counter = 0;
                avaliableLanguages = getAvaliableLanguages();
                for (int i = 0; i < avaliableLanguages.length; i++){
                     if (Integer.parseInt(receivedLanguageID) == i && avaliableLanguages[i]) {
                         counter = 1;
                    }
                 }
                if (counter == 0){
                    message = "!@#$%^&*()_+{}|:";
                }
                //////////////////////
                chatMessages.append(senderNick + ": " + message);
            }

        } catch (IOException e){
            Log.d("tag", Objects.requireNonNull(e.getMessage()));
        }
    }
    public void onClick (View view) {
        switch (view.getId()){
            case R.id.sendBtn:
                if (isHost()) {
                    //for (int i = 0; i < listOfClients.size(); i++) {
                        //listOfClients.get(i).setKlienci(listOfCliOuts);
                    //    Thread thread1 = new Thread(listOfClients.get(i));
                    //   thread1.start();
                    //}
                } else {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String chatMessage = editMessage.getText().toString();
                                // WYSYŁANIE WIADOMOŚCI W FORMACIE: MSG 2 Krasnoludzki NickFury Siema kto PL?
                            chatMessage = "MSG " + selectedLanguageID + " " + nick + " " + chatMessage;
                            out.println(chatMessage);
                            editMessage.setText("");
                            }
                        });
                        thread.start();
                 }
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
                listOfClients.add(w);
                listOfCliOuts.add(w.getOut());
                Thread t = new Thread(w);
                t.start();
            } catch (IOException e) {
                Log.d("tag", e.getMessage());
            }
        }
    }

//////////////////////////////////////////////////

    public static List<PrintWriter> getListOfCliOuts() {
        return  listOfCliOuts;
    }

    public boolean[] getAvaliableLanguages(){
        return getIntent().getBooleanArrayExtra("avaliableLanguages");
    }

    public String[] mkPersonalLanguageList(){

        //wczytywanie tablicy z values/string.xml
        String[] allLanguageList = getResources().getStringArray(R.array.languages_array);

        //czy to host? jeśli tak to ma dostępne wszytskie języki, bo jest GM-em
        if (getIntent().getBooleanExtra("is_host", false)) {
           return allLanguageList;
        } else {
            //wczytywanie tablicy zadeklarowanych w log activity języków, jesli nie host
            avaliableLanguages = getIntent().getBooleanArrayExtra("avaliableLanguages");
            int counter = 0;
            assert avaliableLanguages != null;
            for (boolean avaliableLanguage : avaliableLanguages) {
                if (avaliableLanguage) {
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
        return getIntent().getBooleanExtra("is_host", false);
    }

    public void extraInfo(){
        //pobieranie info o nicku z log/serv activity
        nick = getIntent().getStringExtra("nick");
        TextView textView = findViewById(R.id.nickInfoTxt2);
        textView.setText(nick);

        String port_tmp = getIntent().getStringExtra("port");
        textView = findViewById(R.id.portInfoTxt2);
        textView.setText(port_tmp);
        assert port_tmp != null;
        port = Integer.parseInt(port_tmp);

        serverIP = getIntent().getStringExtra("servIP");
        textView = findViewById(R.id.IPinfoTxt2);
        textView.setText(serverIP);
    }
    public void addSpinner(final String[] finalLanguageList) {

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