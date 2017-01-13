package lj.services.impl;

import lj.dao.UserDao;
import lj.entities.Users;
import lj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by min on 17-1-7.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * Spring Hibernate 事务流程
     * 1.在方法开始之前
     * ①获取Session
     * ②把Session和当前线程绑定，这样就可以在Dao中使用SessionFactory的getCurrentSession()来获取Session了
     * ③开启事务
     * 2.若方法正常结束，即没有出现异常，则
     * ①提交事务
     * ②使和当前线程绑定的Session解除绑定
     * ③关闭Session
     * 3.若方法抛出异常，则
     * ①回滚事务
     * ②使和当前线程绑定的Session解除绑定
     * ③关闭Session
     */

    // @Transactional(propagation = Propagation.NOT_SUPPORTED)
    // org.hibernate.HibernateException: Javassist Enhancement failed: lj.entities.Users
    public Users load(String id) {
        return userDao.load(id);
    }

    public Serializable save(Users user) {
        return userDao.save(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Users> list() {
        return userDao.list();
    }
}
