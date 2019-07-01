import java.io.*;
import java.util.*;

public class HomeWork {


    private ClassRoom classRoom;
    private String name;
    private String description;
    private Topic topic;
    private double barom;
    private File attachedFile=new File("homework");
    //private Hashtable<ClientHandler,File>homeWorkFiles=new Hashtable<>();
    private Hashtable<String,ClientHandler>students=new Hashtable<>();
    private Hashtable<ClientHandler,Integer>grades=new Hashtable<>();
    protected FileOutputStream fos;
    protected FileInputStream fis;
    {
        try {
            fos = new FileOutputStream(attachedFile);
            fis =new FileInputStream(attachedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //time


    public Integer getGrades(ClientHandler clientHandler) {
        return grades.get(clientHandler);
    }
    public Integer getGrades(String id) {
        return grades.get(students.get(id));
    }

    public HomeWork(ClassRoom classRoom, String name, String description, Topic topic, double barom, File attachedFile, Hashtable<String, ClientHandler> students) {
        this.classRoom = classRoom;
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.barom = barom;
        this.attachedFile = attachedFile;
        this.students = students;
        Iterator itr=students.values().iterator();
        while (itr.hasNext()){
            grades.put((ClientHandler)itr.next(),0);
        }
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return this.topic.toString();
    }

    public void setTopic(String topic) {
        this.topic = new Topic(topic);
    }

}
