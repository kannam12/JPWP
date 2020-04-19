package com.example.dndappklon2;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CliWork implements Runnable {
    private Socket client;
    private TextView textArea;
    private PrintWriter out = null;
    private ChatActivity chatActivity;
    private static List<PrintWriter> klienci = new ArrayList<>();


    CliWork (Socket client, TextView textArea, ChatActivity chatActivity) {
        this.client = client;
        this.textArea = textArea;
        this.chatActivity = chatActivity;
    }

    public PrintWriter getOut () throws IOException {
        return new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        String receivedFromClientLine;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            Log.d("tag", e.getMessage());
        }
        while (true) {
            try {
                assert in != null;
                receivedFromClientLine = in.readLine();
                klienci = ChatActivity.getListOfCliOuts();
                for (int i = 0; i < klienci.size(); i++) {
                    klienci.get(i).println(receivedFromClientLine);
                }
                final String finalReceivedFromClientLine = receivedFromClientLine;
                chatActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textArea.append("\n" + finalReceivedFromClientLine);
                    }
                });
            } catch (IOException e) {
                Log.d("tag", e.getMessage());
            }
        }
    }
}
