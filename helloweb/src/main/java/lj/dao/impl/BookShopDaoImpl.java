package lj.dao.impl;

import lj.dao.BookShopDao;
import lj.entities.Account;
import lj.entities.Book;
import lj.services.BookStockException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by min on 17-1-8.
 */
@Repository
public class BookShopDaoImpl implements BookShopDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**HibernateTemplate和HibernateDaoSupport是Spring API，不建议使用。
     * 这样会导致Dao和Spring API的耦合。可移植性变差。
     *private HibernateTemplate hibernateTemplate;
     */

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void save(java.lang.Object o) {
        this.getCurrentSession().save(o);
    }

    public Object load(Class aClass, Serializable serializable) {
        return this.getCurrentSession().load(aClass, serializable);
    }

    public float findBookPriceByIsbn(String isbn) {
        String hql = "FROM " + Book.class.getName() + " WHERE isbn=:isbn";
        Object obj = this.getCurrentSession().createQuery(hql).setString("isbn", isbn).uniqueResult();
        if (obj != null)
            return ((Book) obj).getPrice();
        throw new BookStockException("no book with isbn: " + isbn);
    }

    public void updateBookStock(String isbn) {
        String hql = "FROM " + Book.class.getName() + " WHERE isbn=:isbn";
        Object obj = this.getCurrentSession().createQuery(hql).setString("isbn", isbn).uniqueResult();
        if (obj == null || ((Book) obj).getStock() == 0) {
            throw new BookStockException("no book with isbn =" + isbn + " or no stock");
        }

        hql = "UPDATE " + Book.class.getName() + " SET stock=stock-1 WHERE isbn=:isbn";
        this.getCurrentSession().createQuery(hql).setString("isbn", isbn).executeUpdate();
    }

    public void updateUserAccount(String username, float price) {
        String hql = "FROM " + Account.class.getName() + " WHERE username=:username";
        Object obj = this.getCurrentSession().createQuery(hql).setString("username", username).uniqueResult();
        if (obj == null || ((Account) obj).getBalance() < price) {
            throw new BookStockException("no account with username=" + username + " or no enough balance");
        }
        hql = "UPDATE " + Account.class.getName() + " SET balance=:balance WHERE username=:username";
        this.getCurrentSession().createQuery(hql)
                .setString("username", username).setFloat("balance", ((Account) obj).getBalance() - price)
                .executeUpdate();
    }
}