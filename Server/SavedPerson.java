import java.io.*;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class SavedPerson {//for connecting to file

    static Hashtable<String, ClientHandler> personList = new Hashtable<>();

    protected String id;
    protected String passWord;
    protected LinkedHashSet<ClassRoom> teacherClass = new LinkedHashSet<>();
    protected LinkedHashSet<ClassRoom> studentClass = new LinkedHashSet<>();
    protected File picture = new File("" + id + ".jpg");//namgozari va handel taghyir nam
    protected FileOutputStream fos;
    protected FileInputStream fis;
    {
        try {
            fos = new FileOutputStream(picture);
            fis =new FileInputStream(picture);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void collectOfEnd() {

    }

    public String getId() {
        return id;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setTeacherClass(ClassRoom c) {
        this.teacherClass.add(c);
    }

    public void setStudentClass(ClassRoom c) {
        this.studentClass.add(c);
    }

    public void removeTeacherClass(ClassRoom c) {
        this.teacherClass.remove(c);
    }

    public void removeStudentClass(ClassRoom c) {
        this.studentClass.remove(c);
    }

    public String personDetailsTostring(String personId, ClassRoom classRoom) {
        ClientHandler person = personList.get(personId);

        String respond = "#P#";
        LinkedList<HomeWork> homeWorks = classRoom.getHomeWorks();
        for (HomeWork h : homeWorks) {
            if (h.getGrades(this.id) != null) {
                respond += h.getName() + "#" + h.getGrades(this.id) + "#";
            }
        }
        return respond;
    }


}
