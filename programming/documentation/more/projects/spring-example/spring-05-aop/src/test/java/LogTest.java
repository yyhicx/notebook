import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

public class LogTest {
  private final static Log logger = LogFactory.getLog(LogTest.class);

  @Test
  public void testLog() {
    logger.info("This is a test log message.");
    logger.error("This is an error log message.");
  }
}
