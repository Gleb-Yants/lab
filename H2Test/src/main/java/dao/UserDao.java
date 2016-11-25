package dao;

import model.User;

import java.util.List;

/**
 * Created by Gleb_Yants on 26.11.2016.
 */
public interface UserDao {
    User findByName(String name);
}
