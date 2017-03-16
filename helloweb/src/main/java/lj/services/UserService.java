package lj.services;

import lj.entities.Users;

import java.io.Serializable;
import java.util.List;

/**
 * Created by min on 17-1-7.
 */
public interface UserService {
    Users load(String id);
    Serializable save(Users user);
    List<Users> list();
}
