import com.example.config.AppConfig;
import com.example.service.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

// @SpringJUnitConfig(locations = "classpath:application.xml")
@SpringJUnitConfig(value = {AppConfig.class})
public class AopTest {

  @Autowired
  private Calculator calculator;

  @Test
  public void testCalculator() {
    int result = calculator.add(1, 1);
    System.out.println("result = " + result);
  }
}

