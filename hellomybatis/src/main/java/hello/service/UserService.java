package hello.service;

import hello.dao.UserDao;
import hello.meta.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by min on 17-1-10.
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
