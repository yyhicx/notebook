import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.mapper.CustomerMapper;
import com.example.mapper.OrderMapper;
import java.io.IOException;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyBatisTest {
  private SqlSession session;

  @BeforeEach
  public void init() throws IOException {
    session = new SqlSessionFactoryBuilder()
      .build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
  }

  @AfterEach
  public void destroy() {
    session.commit();
    session.close();
  }

  @Test
  public void testSelectOrderWithCustomer() {
    OrderMapper mapper = session.getMapper(OrderMapper.class);
    Order order = mapper.selectOrderWithCustomer(1);
    System.out.println(order);
  }

  @Test
  public void testSelectCustomerWithOrderList() {
    CustomerMapper mapper = session.getMapper(CustomerMapper.class);
    Customer customer = mapper.selectCustomerWithOrderList(1);

    System.out.println("Customer ID: " + customer.getCustomerId());
    System.out.println("Customer Name: " + customer.getCustomerName());
    for (Order order : customer.getOrderList()) {
      System.out.println(order);
    }
  }
}
