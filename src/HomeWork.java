import java.io.File;
import java.util.Hashtable;

public class HomeWork {


    private ClassRoom classRoom;
    private String name;
    private String description;
    private Topic topic;
    private double barom;
    private File attachedFile;
    private Hashtable<String,ClientHandler>students=new Hashtable<>();
    //time


    public HomeWork(ClassRoom classRoom, String name, String description, Topic topic, double barom, File attachedFile, Hashtable<String, ClientHandler> students) {
        this.classRoom = classRoom;
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.barom = barom;
        this.attachedFile = attachedFile;
        this.students = students;
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
