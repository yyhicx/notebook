import com.example.MainApplication;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 注意：只是示例代码，不能正常运行
 */
@SpringBootTest(classes = MainApplication.class)
public class OptimisticLockerTest {
  @Autowired
  private StudentMapper studentMapper;

  /**
   * 注意：只是示例代码，不能正常运行
   */
  @Test
  public void test01() {
    // 先查询，再更新，获取version数据
    Student student1 = studentMapper.selectById(1);
    Student student2 = studentMapper.selectById(1);

    student1.setAge(20);
    student2.setAge(21);

    studentMapper.updateById(student1);
    studentMapper.updateById(student2);  // 这里会抛出异常，提示版本冲突
  }
}
