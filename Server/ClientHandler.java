import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler extends SavedPerson implements Runnable {

    static Hashtable<Integer, ClientHandler> connectedList = new Hashtable<>();


    /*
        private String id;
        private String passWord;
        private LinkedHashSet<ClassRoom> teacherClass = new LinkedHashSet<>();
        private LinkedHashSet<ClassRoom> studentClass = new LinkedHashSet<>();*/
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


    /*public String getId() {
        return id;
    }

    public String getPassWord() {
        return passWord;
    }*/

    @Override
    public void run() {
        while (true) {
            String ask = null;
            try {
                ask = dsInp.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] arrOfStr = ask.split("#");
            String command1 = arrOfStr[0];
            String id = arrOfStr[1];
            String passWord = arrOfStr[2];
            if (command1.equals("L")) {
                byte logInCheck = logIn(id, passWord);
                if (logInCheck == 0) {
                    importFromSavedPerson(personList.get(id));
                    personList.put(id,this);
                    try {
                        dsOut.writeUTF("#E0");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else if (logInCheck == 1) {
                    try {
                        dsOut.writeUTF("#E1");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (logInCheck == 2) {
                    try {
                        dsOut.writeUTF("#E2");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        dsOut.writeUTF("#E999");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (command1.equals("S")) {
                if (checkUserNameExist(id)) {
                    this.id = arrOfStr[1];
                    this.passWord = arrOfStr[2];
                    try {
                        dsOut.writeUTF("#E0");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    personList.put(this.id, this);
                    break;
                } else {
                    try {
                        dsOut.writeUTF("#E3");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (command1.equals("C")) {
                try {
                    if (checkUserNameExist(id)) {
                        dsOut.writeUTF("#E4");
                    } else {
                        dsOut.writeUTF("#E3");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //send menu details
        String respond0 = mainMenuDetailsToString();
        try {
            dsOut.writeUTF(respond0);
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {
            String ask = null;
            try {
                ask = dsInp.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] arrOfStr = ask.split("#");
            String command = arrOfStr[0];
            //change class options
            if (command.equals("O")) {

            }
            //enter class
            if (command.equals("E")) {
                int code = Integer.parseInt(arrOfStr[1]);//class code
                ClassRoom myclass = ClassRoom.getClass(code);
                //send my class details
            }
            //exit from class
            if (command.equals("X")) {
                int code = Integer.parseInt(arrOfStr[1]);//class code
                ClassRoom myclass = ClassRoom.getClass(code);
                myclass.removeStudent(this);
                this.studentClass.remove(myclass);
                String respond1 = mainMenuDetailsToString();
                try {
                    dsOut.writeUTF(respond1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //make class
            if (command.equals("M")) {
                String name = arrOfStr[1], descript = arrOfStr[2];
                int room = Integer.parseInt(arrOfStr[3]);
                ClassRoom myclass = new ClassRoom(name, descript, room, this);
                this.teacherClass.add(myclass);
                //send class details
            }
            //join class
            if (command.equals("J")) {
                int code = Integer.parseInt(arrOfStr[1]);//class code
                ClassRoom myclass = ClassRoom.getClass(code);
                myclass.setStudent(this.id);
                this.studentClass.add(myclass);
                //send class details
            }

            //update
            if (command.equals("U")) {
                String respond1 = mainMenuDetailsToString();
                try {
                    dsOut.writeUTF(respond1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //notification
            {
            }

        }

    }

    private byte logIn(String id, String passWord) {
        if (personList.containsKey(id) && personList.get(id).getPassWord().equals(passWord)) {
            return 0;
        //wrong pass
        } else if (personList.containsKey(id)) {
            return 2;
        //no id
        } else {
            return 1;
        }



    }
    private void importFromSavedPerson(ClientHandler ch){
        this.id=ch.id;
        this.passWord=ch.passWord;
        this.teacherClass=ch.teacherClass;
        this.studentClass=ch.studentClass;
    }


    private boolean checkUserNameExist(String id) {
        if (personList.containsKey(id)) {
            return false;
        } else {
            return true;
        }

    }

    String mainMenuDetailsToString() {
        String respond = "#T#" + teacherClass.size();
        synchronized (teacherClass) {
            Iterator itr = teacherClass.iterator();
            while (itr.hasNext()) {
                ClassRoom c = (ClassRoom) itr.next();
                respond += c.getClassName() + "#" + c.getStudentsSize();
            }
        }
        respond += "#S#" + studentClass.size();
        synchronized (studentClass) {
            Iterator itr = studentClass.iterator();
            while (itr.hasNext()) {
                ClassRoom c = (ClassRoom) itr.next();
                respond += c.getClassName() + "#" + c.getTeacherName();
            }
        }
        return respond;
    }

    void getImage(){
        int i;
        try {
            while ((i=dsInp.read())!=-1){
                fos.write(i);
            }
            fos.flush();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void sendImage(){
        int i;
        try {
            while ((i=fis.read())!=-1){
                dsOut.write(i);
            }
            dsOut.flush();
            dsOut.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendAttachedFile(HomeWork homeWork){
        int i;
        try {
            while ((i=homeWork.fis.read())!=-1){
                dsOut.write(i);
            }
            dsOut.flush();
            dsOut.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getAttachedFile(HomeWork homeWork) {
        int i;
        try {
            while ((i=dsInp.read())!=-1){
                homeWork.fos.write(i);
            }
            homeWork.fos.flush();
            homeWork.fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
