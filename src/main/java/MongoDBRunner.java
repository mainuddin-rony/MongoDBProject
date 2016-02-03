import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mainuddin Rony on 2/3/2016.
 */
public class MongoDBRunner {
    public static void main(String args[]) throws ParseException {

        MongoDbAccessLayer dbAccess = new MongoDbAccessLayer(); //for accessing Database

        // ***********************************Insert**********************************//

        // Inserting Multiple Students at a time

        List<Student> stList = DummyStudentGenerator.getStudents(); //Create a dummy student list

        dbAccess.saveStudentList(stList);
        System.out.println("All students have been inserted successfully.");
        System.out.println("After inserting number of students is: " + dbAccess.countDBEntryNumber());

        // Inserting Single Student

        List<Course> courseList = new ArrayList<>();
        Course newCourse = new Course();

        newCourse.setCourseName("Bangla");
        newCourse.setCourseId(101);

        courseList.add(newCourse);

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");

        Student newStd = new Student();
        newStd.setFirstName("Abdul");
        newStd.setLastName("Hamid");
        newStd.setDob(date);
        newStd.setPhone("+8801955901392");
        newStd.setAddress("Dhaka, Bangladesh");
        newStd.setStdId(2000);
        newStd.setCourses(courseList);

        dbAccess.saveStudent(newStd);
        System.out.println("New student has been inserted successfully.");
        System.out.println("After inserting number of students is: " + dbAccess.countDBEntryNumber());
    }
}
