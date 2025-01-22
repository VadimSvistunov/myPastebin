package by.svistunovvv.spring.mypastebin.service;

import by.svistunovvv.spring.mypastebin.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByEmail(String email);
    User save(User user);
    User update(User user);
    void delete(String email);
}
