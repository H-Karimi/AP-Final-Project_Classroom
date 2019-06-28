package com.classroom.zed.classroom;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Communicator extends AsyncTask<String,Void,String> {

    Socket socket;
    PrintWriter printWriter;
    Scanner scanner;
    @Override
    protected String doInBackground(String... strings) {

        String output = strings[0];
        String input = "";
        try {
            socket = new Socket();
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(output);
            printWriter.flush();

            input = scanner.next();
            printWriter.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return input;
    }
}
