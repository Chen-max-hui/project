import com.union.mapper.UserMapper;
import com.union.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class Union {
    @Test
    public void testSelectAll() throws Exception {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        UserMapper mapper = applicationContext.getBean(UserMapper.class);
        List<User> users = mapper.selectAll();
        System.out.println(users);
    }
}
