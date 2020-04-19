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
public void onClick (final View view) {
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            switch (view.getId()) {
                case R.id.sendBtn:
                    String outgoingChatMessage = editMessage.getText().toString();
???
???
???
                    out.println(outgoingChatMessage);
                    editMessage.setText("");
                    break;
                case R.id.d20Btn:
                    String outgoingDiceRoll = Integer.toString(mkDiceRoll(20));
???
???
                    out.println(outgoingDiceRoll);
                    break;
                case R.id.d4Btn:
                    outgoingDiceRoll = Integer.toString(mkDiceRoll(4));
???
???
                    out.println(outgoingDiceRoll);
                    break;
            }
        }
    });
    thread.start();
}
private String organizingProtocol (String incomingLine) {

???
???
???
???
???
???
???
???
???
???
???
???
???
???
???
???
???
???
???
}