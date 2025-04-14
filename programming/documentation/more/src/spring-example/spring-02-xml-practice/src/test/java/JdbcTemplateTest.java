import com.example.entity.Student;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTest {
  /**
   * 使用 jdbcTemplate 进行 DML 动作
   */
  @Test
  public void testDML() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

    String sql = "insert into students (name,gender,age,class) values (?,?,?,?);";
    // 参数 1：sql 语句
    // 参数 2：可变参数，对应 sql 语句中的占位符的值
    int rows = jdbcTemplate.update(sql, "十一", "男", 18, "高中三班");

    System.out.println("rows = " + rows);
  }

  /**
   * 查询单条实体对象
   *   public class Student {
   *     private Integer id;
   *     private String name;
   *     private String gender;
   *     private Integer age;
   *     private String classes;
   *   }
   */
  @Test
  public void testDQLForEntity(){
    String sql = "select id,name,age,gender,class as classes from students where id=?;";

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

    // 根据 id 进行查询
    Student student = jdbcTemplate.queryForObject(sql,  (rs, rowNum) -> {
      // 自己处理结果映射
      Student stu = new Student();
      stu.setId(rs.getInt("id"));
      stu.setName(rs.getString("name"));
      stu.setAge(rs.getInt("age"));
      stu.setGender(rs.getString("gender"));
      stu.setClasses(rs.getString("classes"));
      return stu;
    }, 2);

    System.out.println("student = " + student);
  }

  /**
   * 查询实体类集合
   */
  @Test
  public void testDQLForListEntity(){
    String sql = "select id,name,age,gender,class as classes from students;";

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    // query 可以返回集合
    // BeanPropertyRowMapper 就是封装好 RowMapper 的实现，要求属性名和列名相同即可
    List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));

    System.out.println("studentList = " + studentList);
  }
}
