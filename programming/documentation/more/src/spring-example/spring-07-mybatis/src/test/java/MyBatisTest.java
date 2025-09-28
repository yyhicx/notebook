import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyBatisTest {

  private SqlSession session;

  // junit 会在每个 @Test 方法之前执行 @BeforeEach
  @BeforeEach
  public void init() throws IOException {
    // 1. 声明 Mybatis 全局配置文件的路径
    String mybatisConfigFilePath = "mybatis-config.xml";

    // 2. 以输入流的形式加载 Mybatis 配置文件
    InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

    // 3. 基于读取 Mybatis 配置文件的输入流创建 SqlSessionFactory 对象
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    // 4. 使用 SqlSessionFactory 对象开启一个会话
    // SqlSessionFactory 是 ”生产“ SqlSession 的工厂
    // SqlSession 代表 Java 程序和数据库之间的会话
    // HttpSession 代表 Java 程序和浏览器之间的会话
    session = sessionFactory.openSession();
  }

  // junit 会在每个 @Test 方法之后执行 @AfterEach
  @AfterEach
  public void destroy() {
    // 7. 关闭 SqlSession
    session.commit();  // 提交事务 [DQL不需要,其他需要]
    session.close();  //关闭会话
  }

  @Test
  public void testSelectStudentById() {
    // 5. 根据 StudentMapper 接口的 Class 对象获取 Mapper 接口类型的对象（动态代理技术）
    StudentMapper studentMapper = session.getMapper(StudentMapper.class);

    // 6. 调用代理类方法触发对应的 SQL 语句
    Student student = studentMapper.selectStudentById(1);
    System.out.println(student);
  }

  @Test
  public void testInsertStudent() {
    StudentMapper studentMapper = session.getMapper(StudentMapper.class);
    Student student = new Student();
    student.setName("十一");
    student.setGender("男");
    student.setAge(18);
    student.setClasses("高中三班");
    int result = studentMapper.insertStudent(student);
    System.out.println("result = " + result);
    System.out.println("id = " + student.getId());
  }

  @Test
  public void testUpdateStudent() {
    StudentMapper studentMapper = session.getMapper(StudentMapper.class);
    int result = studentMapper.updateStudent(1, "唐一", "男", 18, "高中一班");
    System.out.println("result = " + result);
  }

  @Test
  public void testUpdateStudentByMap() {
    StudentMapper studentMapper = session.getMapper(StudentMapper.class);
    Map<String, Object> map = new HashMap<>();
    map.put("id", 1);
    map.put("name", "张三");
    map.put("gender", "男");
    map.put("age", 15);
    map.put("classes", "高中一班");
    int result = studentMapper.updateStudentByMap(map);
    System.out.println("result = " + result);
  }

  @Test
  public void testSelectStudentCount() {
    StudentMapper studentMapper = session.getMapper(StudentMapper.class);
    int count = studentMapper.selectStudentCount();
    System.out.println("count = " + count);
  }

  @Test
  public void testSelectStudentNameAndMaxAge() {
    StudentMapper studentMapper = session.getMapper(StudentMapper.class);
    Map<Integer, Object> map = studentMapper.selectStudentNameAndMaxAge();
    Set<Map.Entry<Integer, Object>> set = map.entrySet();

    for (Map.Entry<Integer, Object> entry : set) {
      Integer key = entry.getKey();
      String value = entry.getValue().toString();
      System.out.println(key + " = " + value);
    }
  }

  @Test
  public void testSelectAll() {
    StudentMapper studentMapper = session.getMapper(StudentMapper.class);
    List<Student> list = studentMapper.selectAll();
    for (Student student : list) {
      System.out.println(student);
    }
  }

  @Test
  public void testDeleteStudentByName() {
    StudentMapper studentMapper = session.getMapper(StudentMapper.class);
    int result = studentMapper.deleteStudentByName("十一");
    System.out.println("result = " + result);
  }
}
