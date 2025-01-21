package by.svistunovvv.spring.mypastebin.service.impl;

import by.svistunovvv.spring.mypastebin.model.User;
import by.svistunovvv.spring.mypastebin.repository.UserRepository;
import by.svistunovvv.spring.mypastebin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findUserByEmail(email);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(String email) {
        repository.deleteUserByEmail(email);
    }
}
