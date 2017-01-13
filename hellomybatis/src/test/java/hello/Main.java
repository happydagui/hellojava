package hello;

import hello.dao.UserDao;
import hello.meta.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by min on 17-1-10.
 * files:
 * classpath:mybatis-config.xml
 * classpath:mapper/User.xml
 * classpath: hello.meta.User
 * classpath: hello/UserDao      # interface
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserDao userDao = session.getMapper(UserDao.class);
            List<User> users = userDao.getAllUser();
            System.out.println(users);
        } finally {
            session.close();
        }
    }
}
