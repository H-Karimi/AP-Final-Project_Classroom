import java.io.*;
import java.net.*;
import java.util.LinkedHashSet;

public class ClientHandler implements Runnable {

    private String id;
    private String passWord;
    private String name;
    private String lastName;
    private LinkedHashSet<ClassRoom> teacherClass = new LinkedHashSet<>();
    private LinkedHashSet<ClassRoom> studentClass = new LinkedHashSet<>();
    private Socket clientSocket;
    private DataInputStream dsInp;
    private DataOutputStream dsOut;
    private int key;

    public ClientHandler(int key, Socket socket) {
        this.key = key;
        this.clientSocket = socket;
        try {
            dsOut = new DataOutputStream(socket.getOutputStream());
            dsInp = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        while (true) {
            dsInp.readUTF()
            String ask=;
            String id=;
            String passWord=;
            if (ask.equals("L")){
                if (logIn(id, passWord) == 0) {
                    //next page
                    break;
                } else {
                    //send error
                }
            }
            //ask to sign up
            //send name and pass
            if(ask.equals("S")){
                if (signUp(id, passWord) == 0) {
                    this.name = name;
                    this.lastName = lastName;
                    //next page
                    break;
                } else {
                    //send error
                }
            }
        }
        //send menu message
        {
            //change class options
            {

            }
            //enter class
            {

            }
            //exitfromclass
            {

            }
            //make class
            {

            }
            //join class
            {

            }
            //refresh
            {

            }
            //update
            {

            }
            //notification
            {

            }

        }

    }

    private byte logIn(String id, String passWord) {
        if (true/*check in file*/) {
            //importing detailes
        } else if (true/*wrong password*/) {

        } else if (true/*no id*/) {

        }

        //unknown error
        return 0;
    }
    private byte signUp(String id, String passWord) {
        if (true/*check in file*/) {

        } else if (true/*unavailable id*/) {

        }

        //unknown error
        return 0;
    }
}


/*
public class ClientHandlerchange implements Runnable, Comparable {
    static private int loggedInUsersCounter = 0;
    static private int ClientCounter = 0;
    static private IDHandler idHandler;


    private String firstName;
    private String lastName;


    private boolean isLoggedin = false;


    private Socket clientSocket;
    private DataInputStream dsInp;
    private DataOutputStream dsOut;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        dsInp = new DataInputStream(clientSocket.getInputStream());
        dsOut = new DataOutputStream(clientSocket.getOutputStream());

    }

    public static void setIdHandler(IDHandler idHandler) {
        ClientHandler.idHandler = idHandler;
    }

    public static int getLoggedInUsersCounter() {
        return loggedInUsersCounter;
    }

    @Override
    public void run() {
        clientnumber = ++ClientCounter;
        Log.Out("Client" + this.clientnumber + " regisred.");
        try {
            char command;
            while (!isLoggedin) {
                command = dsInp.readChar();
                if (command == 's') {
                    this.id = idHandler.getNewID();
                    dsOut.writeUTF(this.id);
                    isLoggedin = true;
                    loggedInUsersCounter++;
                    Log.Out("Client" + this.clientnumber + " Signed Up.");
                } else if (command == 'l') {
                    String userId = dsInp.readUTF();
                    int temp = idHandler.logIn(userId);
                    if (temp >= 0) {
                        this.id = userId;
                        this.score = temp;
                        isLoggedin = true;
                        loggedInUsersCounter++;
                    }
                    dsOut.writeUTF(String.format("%d", temp));
                }
            }
            while (isLoggedin) {
                Log.Out("Client" + this.clientnumber + " Logged in.");
                while (true) {
                    int c = dsInp.readInt();
                    if (c >= 0) {
                        short s = Question.checkAnswer(c, this.id);
                        dsOut.writeUTF(String.format("A>%d", s));
                        if (s == 2) {
                            Log.Out("Client" + this.clientnumber + " Wins.");
                            score++;
                        } else if (s == 1) {
                            Log.Out("Client" + this.clientnumber + " Gussed right.");
                        }
                    } else if (c == -1) {//time
                        dsOut.writeUTF(String.format("T>%03d", (gameIsAlive ? 120 : 60) - Question.getTimeSecond()));
                    } else if (c == -2) {//score
                        dsOut.writeUTF(String.format("S>%04d", this.score));
                    } else if (c == -3) {//list
                        dsOut.writeUTF("L>" + Server.getScoreBoard());
                    }
                }

            }

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void gameStatus(boolean isAlive) {
        gameIsAlive = isAlive;

        try {
            if (!gameIsAlive)
                dsOut.writeUTF(String.format("S>%04d", this.score));
            dsOut.writeUTF(gameIsAlive ? "I>" : "O>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getClientNumber() {
        return clientnumber;
    }


    public int getScore() {
        return score;
    }

    public String getId() {
        return id;
    }

    public boolean isLoggedin() {
        return isLoggedin;
    }

    public void shutDown() {
        try {
            dsOut.writeUTF("@");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Object o) {
        ClientHandler ch = (ClientHandler) o;
        return this.score > ch.score ? -1 : 0;
    }
}
*/