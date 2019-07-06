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

    static final String IP_ADDRESS = "172.20.168.184";
    static final int PORT_NUMBER = 2003;


    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    static boolean isSocketCreated = false;
    String input = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        if (!isSocketCreated) {
            try {
                socket = new Socket(IP_ADDRESS, PORT_NUMBER);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                isSocketCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        String output = strings[0];

        try {
            dataOutputStream.writeUTF(output);
            dataOutputStream.flush();
            Log.d("Client request", "Me to Server: " + output);

            input = dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Server response", "Server: " + s);
        super.onPostExecute(s);
    }
}
