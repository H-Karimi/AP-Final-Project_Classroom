package com.classroom.zed.classroom;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommunicatorFile extends AsyncTask<String, Void, Void> {
    static final String IP_ADDRESS = "";
    static final int PORT_NUMBER = 0;

    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    String input = "";

    public static void connect(){
        try {
            socket = Communicator.socket;
            dataInputStream = Communicator.dataInputStream;
            dataOutputStream = Communicator.dataOutputStream;
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected Void doInBackground(String... strings) {

        String output = strings[0];

        try {
            dataOutputStream.write(output.getBytes());
            dataOutputStream.flush();

            input = dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        Log.d("Server response", "Server: " + input);
        super.onPostExecute(v);
    }
}
