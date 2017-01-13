package lj.entities;

import lj.dao.UserDao;
import lj.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by min on 17-1-7.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HelloDbTest extends AbstractTransactionalJUnit4SpringContextTests {

    public HelloDbTest() {
    }

    @Resource
    private UserService userService;   // Resouce 默认按照名称注入

    @Test
    public void test() {
        String id = UUID.randomUUID().toString();
        Users user = new Users("foo", "bar");
        user.setId(id);
        Serializable id2 = userService.save(user);
        System.out.println(id2);

        user = new Users("foo2", "bar");
        user.setId(UUID.randomUUID().toString());
        id2 = userService.save(user);
        System.out.println(id2);

        // TODO: Why? Table 'helloweb.users' doesn't exist

        Users u = userService.load(id);
        System.out.println(u.getName());


        List<Users> rows = userService.list();
        for (int i = 0; i < rows.size(); i++) {
            System.out.println(rows.get(i).getName());
        }
    }

    @Test
    public void test2() {
        List<Users> rows = userService.list();
        for (int i = 0; i < rows.size(); i++) {
            System.out.println(rows.get(i).getName());
        }
    }
}
