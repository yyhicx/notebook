import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.MainApplication;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MainApplication.class)
public class StudentMapperTest {
  @Autowired
  private StudentMapper studentMapper;

  @Test
  public void testInsert() {
    Student student = new Student(null, "十一", "男", 18, "高中三班");
    studentMapper.insert(student);
  }

  @Test
  public void testDeleteById() {
    int rows =studentMapper.deleteById(9);
    System.out.println("Delete rows: " + rows);
  }

  @Test
  public void testDeleteByMap() {
    Map<String, Object> columnMap = new HashMap<>();
    // 删除 name = 十一 && age = 18 的学生
    columnMap.put("name", "十一");
    columnMap.put("age", 18);

    int rows = studentMapper.deleteByMap(columnMap);
    System.out.println("Delete rows: " + rows);
  }

  @Test
  public void testDelete() {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    // 删除 name = 十一 && age > 16 的学生
    queryWrapper.eq("name", "十一").gt("age", 16);

    int rows = studentMapper.delete(queryWrapper);
    System.out.println("Delete rows: " + rows);
  }

  @Test
  public void testDeleteBatchIds() {
    List<Integer> ids = List.of(9, 10, 11);
    int rows = studentMapper.deleteBatchIds(ids);
    System.out.println("Delete rows: " + rows);
  }

  @Test
  public void testUpdate() {
    UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
    // 更新 name = 十一 && age = 18 的学生的 age = 19
    updateWrapper.eq("name", "十一").eq("age", 18).set("age", 19);

    int rows = studentMapper.update(null, updateWrapper);
    System.out.println("Update rows: " + rows);
  }

  @Test
  public void testSelectByMap() {
    Map<String, Object> columnMap = new HashMap<>();
    // 查询 name = 十一 && age = 18 的学生
    columnMap.put("name", "十一");
    columnMap.put("age", 18);

    List<Student> students = studentMapper.selectByMap(columnMap);
    students.forEach(System.out::println);
  }

  @Test
  public void testSelectOne() {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    // 查询 id = 9 的学生
    queryWrapper.eq("id", 9);

    Student student = studentMapper.selectOne(queryWrapper);
    System.out.println(student);
  }

  @Test
  public void testExists() {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    // 查询 name = 十一 && age = 18 的学生是否存在
    queryWrapper.eq("name", "十一").eq("age", 18);

    boolean exists = studentMapper.exists(queryWrapper);
    System.out.println("Exists: " + exists);
  }

  @Test
  public void testSelectCount() {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    // 查询 age > 16 的学生数量
    queryWrapper.gt("age", 16);

    Long count = studentMapper.selectCount(queryWrapper);
    System.out.println("Count: " + count);
  }

  @Test
  public void testSelectList() {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    // 查询 age > 16 的学生列表
    queryWrapper.gt("age", 16);

    List<Student> students = studentMapper.selectList(queryWrapper);
    students.forEach(System.out::println);
  }

  @Test
  public void testSelectPage() {
    Page<Student> page = new Page<>(2, 2);

    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    // 查询 age > 16 的学生列表
    queryWrapper.gt("age", 16);

    // 每页显示 2 条数据，当前页码为 2
    Page<Student> resultPage = studentMapper.selectPage(page, queryWrapper);
    System.out.println("Total: " + resultPage.getTotal());
    resultPage.getRecords().forEach(System.out::println);
  }

  @Test
  public void testQueryAll() {
    List<Student> students = studentMapper.queryAll();
    students.forEach(System.out::println);
  }

  @Test
  public void testQueryPage() {
    Page<Student> page = new Page(2, 2);
    studentMapper.queryPage(page, 2);
    System.out.println("Total: " + page.getTotal());
    page.getRecords().forEach(System.out::println);
  }
}
