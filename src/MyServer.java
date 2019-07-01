import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MyServer {



    public static void main(String[] args) throws Exception {


        ServerSocket serverSocket = new ServerSocket(2000);
//accepting clients
        new Thread(() -> {
            int i=0;
            while (true) {
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                    ClientHandler.connectedList.put(i,new ClientHandler(i,clientSocket));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Thread thread = new Thread(ClientHandler.connectedList.get(i));
                i++;
                thread.start();
            }

        }).start();

//shut down server
        new Thread(() -> {
            System.out.println("Shut down:Type something...");
            Scanner inp = new Scanner(System.in);
            inp.next();
            idHandler.close();
            try {
                serverSocket.close();
                for (ClientHandler ch : clientHandlersList) {
                    ch.shutDown();
                }
            } catch (IOException e) {
                //e.printStackTrace();
            } finally {
                Log.Out("Server Closed.");
                System.exit(0);
            }
        }).start();


}

}
