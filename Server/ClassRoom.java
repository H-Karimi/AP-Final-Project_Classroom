import java.io.File;
import java.util.*;

public class ClassRoom {
    static private Integer classCodeMaker = 100;
    static private Hashtable<Integer, ClassRoom> classes = new Hashtable<>();

    private String className;
    private String description;
    private int roomNumber;
    private int classCode;

    private LinkedList<HomeWork> homeWorks = new LinkedList<>();
    private LinkedHashSet<Topic> topics = new LinkedHashSet<>();
    private LinkedHashSet<ClientHandler> teachers = new LinkedHashSet<>();
    // private HashSet<ClientHandler> students = new HashSet<>();
    private Hashtable<String, ClientHandler> students = new Hashtable<>();

    public ClassRoom(String className, int roomNumber, ClientHandler teacher) {
        this(className, "", roomNumber, teacher);
    }

    public ClassRoom(String className, String description, int roomNumber, ClientHandler teacher) {
        this.className = className;
        this.description = description;
        this.roomNumber = roomNumber;
        this.teachers.add(teacher);
        this.classCode = classCodeMaker++;
        classes.put(classCode - 1, this);
    }

    public static ClassRoom getClass(int classCode) {
        return classes.get(classCode);
    }

    public LinkedList<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public byte setTeacher(String id) {
        if(SavedPerson.personList.containsKey(id)){
            ClientHandler teacher=SavedPerson.personList.get(id);
            if(students.containsValue(teacher)) {
                students.remove(teacher.id, teacher);
                teacher.removeStudentClass(this);
            }
                teachers.add(teacher);
                teacher.setTeacherClass(this);
                return 0;

        }else{
            return 1;
        }

    }

    public byte setStudent(String id) {

        if(SavedPerson.personList.containsKey(id)){
            ClientHandler student=SavedPerson.personList.get(id);
            if(teachers.contains(student)) {
                return 2;
            }
            this.students.put(student.getId(), student);
            student.setStudentClass(this);
            return 0;
        }else{
            return 1;
        }
    }

    public boolean removeStudent(ClientHandler student) {
        return this.students.remove(student.getId(), student);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getClassCode() {
        return classCode;
    }

    public int getStudentsSize() {
        return students.size();
    }

    public int getTeachersSize() {
        return teachers.size();
    }

    public String getTeacherName() {
        String respond = "";
        Iterator itr = teachers.iterator();
        while (itr.hasNext()) {
            respond += ((ClientHandler) (itr.next())).getId();
        }
        return respond;
    }

    public void setTopic(String topic) {
        this.topics.add(new Topic(topic));
    }

    public boolean removeTopic(String topic) {
        return this.topics.remove(new Topic(topic));
    }

    public void setHomeWorks(String name, String topic, String description, double barom, File attach, Hashtable<String, ClientHandler> students) {
        Topic t = new Topic(topic);
        topics.add(t);
        homeWorks.addLast(new HomeWork(this, name, description, t, barom, attach, students));
    }

    String classMenuDetailsToString() {
        //people
        String respond = "#T";
        synchronized (teachers) {
            Iterator itr = teachers.iterator();
            while (itr.hasNext()) {
                ClientHandler c = (ClientHandler) itr.next();
                respond += "#" + c.getId();
            }
        }
        respond += "#S#" + students.size();
        synchronized (students) {
            Iterator itr = students.keySet().iterator();
            while (itr.hasNext()) {
                String c = (String) itr.next();
                respond += "#" + c;
            }

        }
        //pictures?
        //classswork


        return respond;
    }

    String classInfPageToString(){
        return this.getClassName()+"#"+this.getClassCode()+"#"+(this.getDescription()==null?"":this.getDescription())+"#";
    }

    @Override
    public int hashCode() {
        return classCode;
    }


}
