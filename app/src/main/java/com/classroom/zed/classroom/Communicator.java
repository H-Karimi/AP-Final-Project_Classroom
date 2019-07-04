package com.classroom.zed.classroom;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Communicator extends AsyncTask<String, Void, String> {

    static final String IP_ADDRESS = "";
    static final int PORT_NUMBER = 0;

    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    String input = "";

    public static void connect(){
        try {
            socket = new Socket(IP_ADDRESS, PORT_NUMBER);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(String... strings) {

        String output = strings[0];

        try {
            dataOutputStream.writeUTF(output);
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

        return input;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Server response", "Server: " + input);
        super.onPostExecute(s);
    }
}
