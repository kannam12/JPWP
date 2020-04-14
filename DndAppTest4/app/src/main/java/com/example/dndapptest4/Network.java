package com.example.dndapptest4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {
    //Dla serwera np. po kliknięciu w radiobutton, że chcę być serwerem
    public void serverMaker(){

    }
    //Dla klienta
    public void serverSearching(){
        //w sumie nie
    }
    //Dla obu
    public void messageReceiver(){

    }
    public void messageSender(){

    }
    public static void main() {

        String host = null;
        int port = 0;

        try {
            host = "88.88.88.88";       // host - adres ip
            port = 2222;

            // Utworzenie gniazda
            Socket socket = new Socket(host, port);


            // Pobranie strumienia wejściowego gniazda
            // Nakładamy dekodowanie i buforowanie
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            // Odczyt odpowiedzi serwera
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            // Zamknięcie strumienia i gniazda
            br.close();
            socket.close();
        } catch (UnknownHostException exc) {
            System.out.println("Nieznany host: " + host);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
