package com.example.dndapp;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CliWork implements Runnable{
    private Socket client;
    private TextView textArea;
    CliWork (Socket client, TextView textArea) {
        this.client = client;
        this.textArea = textArea;
    }
    @Override
    public void run() {
        String line;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            Log.d("tag", e.getMessage());
        }
        while (true) {
            try {
                line = in.readLine();
                out.println(line);
                textArea.append(line);
            } catch (IOException e) {
                Log.d("tag", e.getMessage());
            }

        }

    }
}
