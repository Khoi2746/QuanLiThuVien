package com.fpoly.dao;

import com.fpoly.entity.User;
import java.util.List;

public interface UserDao {
    User findById(int id);
    List<User> findAll();
    void create(User entity);
    void update(User entity);
    void delete(int id);
    List<User> findByUsername(String keyword);
    boolean checkUsernameExists(String username);
    boolean updatePassword(String username, String newPass);
}
