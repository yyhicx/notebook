import com.example.entity.Order;
import com.example.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
  public void testOrderSelectAll() {
    OrderMapper mapper = session.getMapper(OrderMapper.class);

    // 页尺寸为 2，每页显示 2 条数据；显示第 1 页，即第 1-2 条数据
    PageHelper.startPage(1, 2);
    // allOrders 必须在 PageHelper.startPage() 之后调用
    List<Order> allOrders = mapper.selectAll();
    PageInfo<Order> pageInfo = new PageInfo<>(allOrders);

    System.out.println("pageInfo = " + pageInfo);
    long total = pageInfo.getTotal();  // 获取总记录数
    System.out.println("total = " + total);
    int pages = pageInfo.getPages();  // 获取总页数
    System.out.println("pages = " + pages);
    int pageNum = pageInfo.getPageNum();  // 获取当前页码
    System.out.println("pageNum = " + pageNum);
    int pageSize = pageInfo.getPageSize();  // 获取每页显示记录数
    System.out.println("pageSize = " + pageSize);
    List<Order> orders = pageInfo.getList();  // 获取查询页的数据集合
    for (Order order : orders) {
      System.out.println(order);
    }
  }
}
