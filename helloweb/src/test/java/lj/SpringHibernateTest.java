package lj;

import lj.services.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by min on 17-1-8.
 */
public class SpringHibernateTest {
    private ApplicationContext context = null;
    private UserService service = null;

    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean(UserService.class);
    }

    @Test
    public void test1() {
        service.list();
    }
}
