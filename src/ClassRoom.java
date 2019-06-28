import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class ClassRoom {
    static private Integer classCodeMaker = 100;
    static private ArrayList<ClassRoom> classes=new ArrayList<>();

    private String className;
    private String description;
    private int roomNumber;
    private int classCode;

    private LinkedHashSet<ClientHandler> teachers = new LinkedHashSet<>();
    private HashSet<ClientHandler> students = new HashSet<>();

    public ClassRoom(String className, int roomNumber, ClientHandler teacher) {
        this(className, "", roomNumber, teacher);
    }

    public ClassRoom(String className, String description, int roomNumber, ClientHandler teacher) {
        this.className = className;
        this.description = description;
        this.roomNumber = roomNumber;
        this.teachers.add(teacher);
        this.classCode = classCodeMaker++;
    }

    public void setTeacher(ClientHandler teacher) {
        this.teachers.add(teacher);
    }

    public void setStudent(ClientHandler student) {
        this.students.add(student);
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

}
