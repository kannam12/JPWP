package com.example.dndapptest4;

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
    public ChatActivity chatActivity;
    public static List<PrintWriter> klienci = new ArrayList<>();


    CliWork (Socket client, TextView textArea) {
        this.client = client;
        this.textArea = textArea;
    }
    public PrintWriter getOut () throws IOException {
        return new PrintWriter(client.getOutputStream(), true);
    }
    public void setKlienci (List<PrintWriter> clients) {
        klienci = clients;
    }

    @Override
    public void run() {
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            Log.d("tag", e.getMessage());
        }
        while (true) {
            try {
                line = in.readLine();
                klienci = ChatActivity.getListOfCliOuts();
                for (int i = 0; i < klienci.size(); i++) {
                    klienci.get(i).println(line);
                }
                textArea.append(line);
            } catch (IOException e) {
                Log.d("tag", e.getMessage());
            }

        }

    }
}