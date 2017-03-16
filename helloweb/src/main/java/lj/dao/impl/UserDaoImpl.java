package lj.dao.impl;

import lj.dao.UserDao;
import lj.entities.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by min on 17-1-7.
 */
@Repository
public class UserDaoImpl implements UserDao { // 不能 extends HibernateDaoSupport

    @Autowired
    private SessionFactory sessionFactory;

    /**HibernateTemplate和HibernateDaoSupport是Spring API，不建议使用。
     * 这样会导致Dao和Spring API的耦合。可移植性变差。
     *private HibernateTemplate hibernateTemplate;
     */

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Serializable save(Users user) {
        return this.getCurrentSession().save(user);
    }

    public void update(Users user) {
        this.getCurrentSession().update(user);
    }

    public void delete(String id) {
        Users user = this.load(id);
        this.getCurrentSession().delete(user);
    }

    public Users load(String id) {
        return (Users) this.getCurrentSession().load(Users.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Users> list() {
        String hql = "from " + Users.class.getName();
        return this.getCurrentSession().createQuery(hql).list();
    }
}
