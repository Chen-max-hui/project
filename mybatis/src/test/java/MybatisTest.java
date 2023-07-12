import com.mysql.mapper.StudentMapper;
import com.mysql.mapper.UserMapper;
import com.mysql.pojo.User;
import com.mysql.util.DbUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;

public class MybatisTest {
    @Test
    public void testDeleteById() throws IOException {
        SqlSession sqlSession = DbUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteById(15);
        sqlSession.commit();
    }
    @Test
    public void testIsExistById() throws IOException {
        SqlSession sqlSession = DbUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        boolean existById = mapper.isExistById(19);
        System.out.println(existById);

    }
    @Test
    public void testSelectUser() throws IOException {
        SqlSession sqlSession = DbUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUser("admin", "admin");
        System.out.println(user);
    }

}
