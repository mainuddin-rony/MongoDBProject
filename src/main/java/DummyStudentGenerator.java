import org.fluttercode.datafactory.impl.DataFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mainuddin Rony on 2/3/2016.
 */
public class DummyStudentGenerator {
    public  static DataFactory df = new DataFactory();

    public static Date minDate = df.getDate(2000, 1, 1);
    public static Date maxDate = df.getDate(2005, 1, 1);
    public static List<Student> studentList = new ArrayList<Student>();
    public static int id = 1000;
    public static String[] operatorID = {"+88015", "+88016", "+88017", "+88018", "+88019"};
    public static HashMap<String, Integer> courseSub = new HashMap();

    public static List<Student> getStudents() {

        courseSub.put("Bangla", 101);
        courseSub.put("English", 102);
        courseSub.put("Math", 103);
        courseSub.put("Higher Math", 104);
        courseSub.put("Physics", 105);
        courseSub.put("Religion", 106);
        courseSub.put("Chemistry", 107);
        courseSub.put("Biology", 108);

        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            String name = df.getFirstName() + " "+ df.getLastName();

            student.setFirstName(df.getFirstName());
            student.setLastName(df.getLastName());
            student.setAddress(df.getAddress()+","+df.getCity()+","+df.getNumberText(5));
            student.setStdId(id++);
            student.setPhone(df.getItem(operatorID) + df.getNumberText(8));
            student.setDob( df.getDateBetween(minDate, maxDate));
            student.setCourses(generateCourse());

            studentList.add(student);
        }

        return studentList;
    }

    private static List<Course> generateCourse() {

        List<Course> courseList = new ArrayList<Course>();
        String [] course = {"Bangla", "English", "Math", "Religion", "Higher Math", "Physics", "Chemistry", "Biology"};
        String [] optional = {"true", "false"};

        int m = df.getNumberBetween(1,5);

        while (m>0) {
            Course cour = new Course();
            String sub = df.getItem(course);

            if(containsSub(sub, courseList)) continue;
            cour.setCourseId(courseSub.get(sub));
            cour.setCourseName(sub);

            courseList.add(cour);
            m--;
        }

        return courseList;
    }

    private static boolean containsSub(String sub, List<Course> courseList) {
        for(Course c: courseList) {
            if (sub.equals(c.getCourseName())) return true;
        }

        return false;
    }
}
