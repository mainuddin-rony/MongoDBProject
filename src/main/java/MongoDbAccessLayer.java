import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by Mainuddin Rony on 2/3/2016.
 */
public class MongoDbAccessLayer {
    private final MongoDatabase db;
    private final String uri = "mongodb://localhost:27017/School";

    private final String COLLECTION = "students";

    public MongoDbAccessLayer() {
        final MongoClientURI mcUri = new MongoClientURI(uri);
        final MongoClient client = new MongoClient(mcUri);

        this.db = client.getDatabase(mcUri.getDatabase());
    }

    public void saveStudentList(List<Student> studentList) {
        if (db.getCollection(COLLECTION).count() != 0) db.getCollection(COLLECTION).drop();
        db.getCollection(COLLECTION).insertMany(studentList.stream()
        .map(student -> getStudentInfo(student))
        .collect(Collectors.toList()));
    }

    private Document getStudentInfo(Student student) {
        return new Document()
                .append("stdID", student.getStdId())
                .append("firstName", student.getFirstName())
                .append("lastName", student.getLastName())
                .append("address", student.getAddress())
                .append("phone", student.getPhone())
                .append("dob", student.getDob())
                .append("courses", student.getCourses().stream()
                .map(course -> getCourseInfo(course))
                .collect(Collectors.toList()))
                ;
    }

    private Document getCourseInfo(Course course) {
        return new Document()
                .append("courseName", course.getCourseName())
                .append("courseId", course.getCourseId());
    }
}
