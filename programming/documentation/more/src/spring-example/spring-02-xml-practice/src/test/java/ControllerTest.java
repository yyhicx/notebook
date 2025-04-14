import com.example.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControllerTest {
  @Test
  public  void testRun(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    StudentController studentController = applicationContext.getBean(StudentController.class);
    studentController.findAll();
  }
}
