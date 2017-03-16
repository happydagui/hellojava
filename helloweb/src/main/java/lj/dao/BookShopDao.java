package lj.dao;

import lj.entities.Book;

/**
 * Created by min on 17-1-8.
 */
public interface BookShopDao {

    void save(java.lang.Object o);
    java.lang.Object load(java.lang.Class aClass, java.io.Serializable serializable);
    float findBookPriceByIsbn(String isbn);
    // 更新书的库存，使书号对应的库存-1
    void updateBookStock(String isbn);
    // 更新用户的账户余额：account的balance-price
    void updateUserAccount(String username, float price);
}
