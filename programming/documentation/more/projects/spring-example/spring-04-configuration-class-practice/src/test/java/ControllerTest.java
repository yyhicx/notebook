import com.example.config.AppConfig;
import com.example.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ControllerTest {
  @Test
  public void testRun() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
      AppConfig.class);
    StudentController studentController = applicationContext.getBean(StudentController.class);
    studentController.findAll();
  }
}
