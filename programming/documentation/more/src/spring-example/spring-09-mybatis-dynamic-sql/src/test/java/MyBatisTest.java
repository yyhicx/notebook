import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import java.io.IOException;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyBatisTest {
  private SqlSession session;

  @BeforeEach
  public void init() throws IOException {
    session = new SqlSessionFactoryBuilder()
      .build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
  }

  @AfterEach
  public void destroy() {
    session.commit();
    session.close();
  }

  @Test
  public void testSelectStudentByGenderOrAge() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    List<Student> students = mapper.selectStudentList("男", 17);

    for (Student student : students) {
      System.out.println(student);
    }
  }

  @Test
  public void testUpdateStudentById() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    int result = mapper.updateStudentById(1, "唐一", 18);
    System.out.println("result = " + result);
  }

  @Test
  public void testSelectStudentListTrim() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    List<Student> students = mapper.selectStudentListTrim("男", 17);

    for (Student student : students) {
      System.out.println(student);
    }
  }

  @Test
  public void testUpdateStudentByIdTrim() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    int result = mapper.updateStudentByIdTrim(1, "张三", 15);
    System.out.println("result = " + result);
  }

  @Test
  public void testSelectStudentListChoose() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    List<Student> students = mapper.selectStudentListChoose("男", 17);

    for (Student student : students) {
      System.out.println(student);
    }
  }

  @Test
  public void testSelectBatch() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    List<Integer> ids = List.of(1, 2, 3);
    List<Student> students = mapper.selectBatch(ids);

    for (Student student : students) {
      System.out.println(student);
    }
  }

  @Test
  public void testInsertBatch() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    List<Student> students = List.of(
      new Student(100, "十二", "男", 18, "高中一班"),
      new Student(101, "十三", "女", 16, "高中二班"),
      new Student(102, "十四", "男", 17, "高中三班")
    );
    int result = mapper.insertBatch(students);
    System.out.println("result = " + result);
  }

  @Test
  public void testUpdateBatch() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    List<Student> students = List.of(
      new Student(100, "十五", "男", 17, "高中二班"),
      new Student(101, "十六", "男", 18, "高中一班"),
      new Student(102, "十七", "女", 16, "高中三班")
    );
    int result = mapper.updateBatch(students);
    System.out.println("result = " + result);
  }

  @Test
  public void testDeleteBatch() {
    StudentMapper mapper = session.getMapper(StudentMapper.class);
    List<Integer> ids = List.of(100, 101, 102);
    int result = mapper.deleteBatch(ids);
    System.out.println("result = " + result);
  }
}
