import com.example.config.AppConfig;
import com.example.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(AppConfig.class)
public class TxTest {
  @Autowired
  private StudentController studentController;

  @Test
  public void testTx() {
    // studentController.changeInfo(1, "张三", 15);
    studentController.changeInfo(1, "唐一", 18);
  }
}
