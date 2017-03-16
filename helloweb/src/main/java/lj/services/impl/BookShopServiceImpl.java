package lj.services.impl;

import lj.dao.BookShopDao;
import lj.services.BookShopService;
import lj.services.UserAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by min on 17-1-8.
 */
@Service
@Transactional
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

    @Transactional
    public void save(Object o) {
        bookShopDao.save(o);
    }

    @Transactional
    public void purchase(String username, String isbn) {
        float price = bookShopDao.findBookPriceByIsbn(isbn);
        bookShopDao.updateBookStock(isbn);
        bookShopDao.updateUserAccount(username, price);
    }
}
