import com.example.MainApplication;
import com.example.dao.StudentDao;
import com.example.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MainApplication.class)
public class StudentDaoTest {
  @Autowired
  private StudentDao studentDao;

  @Test
  public void testSelectStudentById() {
    Student student = studentDao.selectStudentById(1);
    System.out.println(student);
  }
}
