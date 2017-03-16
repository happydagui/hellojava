package lj.dao;

import lj.entities.Users;

import java.io.Serializable;
import java.util.List;

/**
 * Created by min on 17-1-7.
 */
public interface UserDao {
    Serializable save(Users user);

    void update(Users user);

    void delete(String id);

    Users load(String id);

    List<Users> list();

}
