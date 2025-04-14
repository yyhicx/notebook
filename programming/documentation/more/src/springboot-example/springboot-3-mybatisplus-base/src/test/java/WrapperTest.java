import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.MainApplication;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 注意：只是示例代码，不能正常运行
 */
@SpringBootTest(classes = MainApplication.class)
public class WrapperTest {
  @Autowired
  private StudentMapper studentMapper;

  /**
   * 组装查询条件
   * 注意：只是示例代码，不能正常运行
   */
  @Test
  public void test01() {
    // 查询姓名包含 a，年龄在 20 到 30 之间，并且邮箱不为 null 的用户信息
    // select id, name, age, email, is_deleted from t_students where is_deleted=0
    // and (name like ? and age between ? and ? and email is not null)
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name", "a")
      .between("age", 20, 30)
      .isNotNull("email");
    List<Student> students = studentMapper.selectList(queryWrapper);
    students.forEach(System.out::println);
  }

  /**
   * 组装排序条件
   * 注意：只是示例代码，不能正常运行
   */
  @Test
  public void test02() {
    // 按年龄降序查询用户，如果年龄相同则按 id 升序排列
    // select id, name, age, email, is_deleted from t_students where is_deleted=0
    // order by age desc, id asc
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper
      .orderByDesc("age")
      .orderByAsc("id");
    List<Student> students = studentMapper.selectList(queryWrapper);
    students.forEach(System.out::println);
  }

  /**
   * 组装删除条件
   * 注意：只是示例代码，不能正常运行
   */
  @Test
  public void test03() {
    // 删除 email 为空的用户
    // delete from t_students where email is null
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.isNull("email");
    // 条件构造器也可以构建删除语句的条件
    int rows = studentMapper.delete(queryWrapper);
    System.out.println("Delete rows: " + rows);
  }

  /**
   * 组装更新条件
   * 使用 and 和 or 关键字
   * 注意：只是示例代码，不能正常运行
   */
  @Test
  public void test04() {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    // 将年龄大于 20 并且姓名中包含有 a 或邮箱为 null 的学生信息进行修改
    // update t_students set age=?, email=? where name like ? and age > ? or email is null
    queryWrapper
      .like("name", "a")
      .gt("age", 20)
      .or()
      .isNull("email");
    Student student = new Student();
    student.setAge(18);
    // student.setEmail("student@example.com");
    // 注意：使用 queryWrapper + 实体类可以实现修改，但是无法将列值设置为 null
    int rows = studentMapper.update(student, queryWrapper);
    System.out.println("Update rows: " + rows);
  }

  /**
   * 指定列映射查询
   * 注意：只是示例代码，不能正常运行
   */
  @Test
  public void test05() {
    // 查询用户信息的 name 和 age 字段
    // select name, age from t_students
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("name", "age");
    // selectMaps() 返回 Map 集合列表，通常配合 select() 使用，避免 Student 对象中没有被查询到的列值为 null
    List<Map<String, Object>> maps = studentMapper.selectMaps(queryWrapper);
    maps.forEach(System.out::println);
  }

  /**
   * condition 判断组织条件
   * 注意：只是示例代码，不能正常运行
   */
  @Test
  public void test06() {
    String name = "a";
    Integer age = 20;

    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    // 判断条件拼接
    // 如果 name 不为空，则追加 name = ? 条件
    // 如果 age 大于 1，则追加 age = ? 条件
    // 方案 1：手动判断
    if (!StringUtils.isEmpty(name)){
      queryWrapper.eq("name", name);
    }
    if (age > 1){
      queryWrapper.eq("age", age);
    }

    // 方案 2：拼接 condition 判断
    // 每个条件拼接方法都 condition 参数，这是一个比较运算，为 true 追加当前条件
    // eq(condition, column_name, value)
    queryWrapper.eq(!StringUtils.isEmpty(name),"name", name)
      .eq(age > 1, "age", age);
  }

  /**
   * 使用 Lambda 表达式查询
   * 注意：只是示例代码，不能正常运行
   */
  @Test
  public void test07() {
    LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();

    // 查询姓名为 a，年龄大于等于 18 的学生信息，并按创建时间降序排列，限制返回 10 条
    lambdaQueryWrapper.eq(Student::getName, "a")
      .ge(Student::getAge, 18)
      // .orderByDesc(Student::getCreateTime)
      .last("limit 10");

    List<Student> students = studentMapper.selectList(lambdaQueryWrapper);
    students.forEach(System.out::println);
  }
}
