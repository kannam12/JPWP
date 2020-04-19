public class CliWork implements Runnable {



CliWork () {
???
    }
    
    @Override
    public void run() {
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            Log.d("tag", e.getMessage());
        }
        while (true) {
            try {
                assert in != null;
                String receivedFromClientLine = in.readLine();
???
???
???
???
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

public class ChatActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
       //^ android stuff
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isHost()) {
                    serverListeningSocket();
                } else {
                    clientListeningSocket();
                }
            }
        });
        thread.start();
    }
    //Socket dla servera
    private void serverListeningSocket() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            Log.d("tag", Objects.requireNonNull(e.getMessage()));
        }
        while (true) {
            CliWork work;
            try {
                work = new CliWork(                                          );
???
???
                Thread t = new Thread(work);
                t.start();
            } catch (IOException e) {
                Log.d("tag", Objects.requireNonNull(e.getMessage()));
            }
        }
    }
    //Socket dla klienta
    private void clientListeningSocket() {
        try {
            socket = new Socket (serverIP, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            boolean loop = true;
            while(loop){
                final String incomingLine = in.readLine();
                final String displayLine = organizingProtocol(incomingLine);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatMessages.append("\n" + displayLine);
                    }
                });
            }
        } catch (IOException e){
            Log.d("tag", Objects.requireNonNull(e.getMessage()));
        }
    }

    //onClick - co się dzieje po kliknięciu przycisków\\
    //sendBtn: wysyłania wiadomości; d20/4Btn: rzutu kością   
    public void onClick (final View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                switch (view.getId()) {
                    case R.id.sendBtn: 
                        String outgoingChatMessage = editMessage.getText().toString();
???
                        break;
                    case R.id.d20Btn:
???
                        break;
                    case R.id.d4Btn:
???
                        break;
                }
            }
        });
        thread.start();
    }

    private String organizingProtocol (String incomingLine) {
???        //Zadanie 1
    }
}