package lj.services.impl;

import lj.services.BookShopService;
import lj.services.CashierService;
import lj.services.UserAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

/**
 * Created by min on 17-1-8.
 */
@Service
public class CachierServiceImpl implements CashierService {

    @Autowired
    private BookShopService bookShopService;

    @Transactional
    public void checkout(String username, List<String> isbns) {
        for(String isbn : isbns) {
            bookShopService.purchase(username, isbn);
        }
    }
}
