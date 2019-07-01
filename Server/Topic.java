import java.util.HashSet;

public class Topic {

    private String topicName;

    public Topic(String topicName) {
        this.topicName = topicName;

    }

    @Override
    public int hashCode(){
        return topicName.hashCode();
    }
    @Override
    public String toString() {
        return topicName;
    }
    @Override
    public boolean equals(Object obj){
        if(!obj.getClass().equals(this.getClass()))
            return false;
        return ((Topic)obj).topicName.equals(this.topicName);
    }
}
