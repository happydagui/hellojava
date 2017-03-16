package lj;

import lj.entities.Account;
import lj.entities.Book;
import lj.services.BookShopService;
import lj.services.BookStockException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by min on 17-1-8.
 */
public class SpringHibernateTest {
    private ApplicationContext context = null;
    private BookShopService bookShopService;

    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookShopService = context.getBean(BookShopService.class);

    }

    @Test
    public void test() {
        bookShopService.save(new Book("jquery", "1001", 100, 10));
        bookShopService.save(new Book("bootstrap", "1002", 90, 1));
        bookShopService.save(new Account("xiaomin", 1000));
        bookShopService.purchase("xiaomin", "1001");
    }

    @Test(expected = BookStockException.class)
    public void testWithNotEnoughMoney() {
        bookShopService.save(new Book("jquery", "1001", 100, 10));
        bookShopService.save(new Book("bootstrap", "1002", 90, 1));
        bookShopService.save(new Account("xiaomin", 10));
        bookShopService.purchase("xiaomin", "1001");
    }

}
