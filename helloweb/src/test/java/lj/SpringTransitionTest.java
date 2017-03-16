package lj;

import lj.dao.BookShopDao;
import lj.entities.Account;
import lj.entities.Book;
import lj.services.BookShopService;
import lj.services.BookStockException;
import lj.services.CashierService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Arrays;

/**
 * Created by min on 17-1-8.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringTransitionTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private BookShopService bookShopService;

    @Autowired
    private CashierService cashierService;

    @Autowired
    private BookShopDao bookShopDao;

    @Before
    public void setUp() {
        System.out.println("############# setUp ############");
    }

    @After
    public void tearDown() {
        System.out.println("############# tearDown ############");
    }

    @Test
    public void testBookShopDaoFindPriceByIsbn() {
        bookShopService.save(new Book("jquery", "1001", 101, 10));
        bookShopService.save(new Book("bootstrap", "1002", 91, 1));
        bookShopService.save(new Account("xiaomin", 1000));
        System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
    }

    @Test
    public void testBookShopServic(){

        bookShopService.save(new Book("jquery", "1001", 101, 10));
        bookShopService.save(new Book("bootstrap", "1002", 91, 1));
        bookShopService.save(new Account("xiaomin", 1000));
        bookShopService.purchase("xiaomin", "1001");
    }

    @Test(expected = BookStockException.class)
    public void testBookShopServiceBalanceIsNotEnough(){

        bookShopService.save(new Book("jquery", "1001", 100, 10));
        bookShopService.save(new Book("bootstrap", "1002", 90, 1));
        bookShopService.save(new Account("xiaomin", 10));
        bookShopService.purchase("xiaomin", "1001");
    }

    @Test
    public void testTransactionPropagation(){

        bookShopService.save(new Book("jquery", "1001", 101, 10));
        bookShopService.save(new Book("bootstrap", "1002", 91, 1));
        bookShopService.save(new Account("xiaomin", 1000));

        cashierService.checkout("xiaomin", Arrays.asList("1001", "1002"));
    }
}
