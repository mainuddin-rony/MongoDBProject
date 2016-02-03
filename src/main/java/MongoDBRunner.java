import java.util.List;

/**
 * Created by Mainuddin Rony on 2/3/2016.
 */
public class MongoDBRunner {
    public static void main(String args[]) {

        List<Student> stList = DummyStudentGenerator.getStudents();

        MongoDbAccessLayer dbAccess = new MongoDbAccessLayer();

        dbAccess.saveStudentList(stList);
        System.out.println();
    }
}
