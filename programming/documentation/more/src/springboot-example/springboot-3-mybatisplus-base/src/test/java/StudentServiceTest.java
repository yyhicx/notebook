import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.MainApplication;
import com.example.entity.Student;
import com.example.service.StudentService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MainApplication.class)
public class StudentServiceTest {
  @Autowired
  private StudentService studentService;

  @Test
  public void testSave() {
    Student student = new Student(null, "十一", "男", 18, "高中三班");
    boolean isSuccess = studentService.save(student);
    System.out.println("Is success save: " + isSuccess);
  }

  @Test
  public void testSaveBatch() {
    List<Student> students = new ArrayList<>();
    students.add(new Student(null, "十一", "男", 18, "高中三班"));
    students.add(new Student(null, "十一", "男", 18, "高中三班"));
    students.add(new Student(null, "十一", "男", 18, "高中三班"));

    boolean isSuccess = studentService.saveBatch(students);
    System.out.println("Is success save batch: " + isSuccess);
  }

  @Test
  public void testSaveOrUpdate() {
    Student student = new Student(9, "十一", "男", 19, "高中三班");

    UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("name", "十一");

    boolean isSuccess = studentService.saveOrUpdate(student, updateWrapper);
    System.out.println("Is success save or update: " + isSuccess);
  }

  @Test
  public void testSaveOrUpdateBatch() {
    List<Student> students = new ArrayList<>();
    students.add(new Student(null, "十一", "男", 18, "高中三班"));
    students.add(new Student(null, "十一", "男", 18, "高中三班"));
    students.add(new Student(null, "十一", "男", 18, "高中三班"));

    boolean isSuccess = studentService.saveOrUpdateBatch(students, 2);
    System.out.println("Is success save or update batch: " + isSuccess);
  }

  @Test
  public void testRemove() {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("name", "十一");

    boolean isSuccess = studentService.remove(queryWrapper);
    System.out.println("Is success remove: " + isSuccess);
  }

  @Test
  public void testUpdate() {
    Student student = new Student(9, "十一", "男", 19, "高中三班");

    UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("name", "十一");

    boolean isSuccess = studentService.update(student, updateWrapper);
    System.out.println("Is success update: " + isSuccess);
  }

  @Test
  public void testGetById() {
    Student student = studentService.getById(1);
    System.out.println("Get student by id: " + student);
  }
}
