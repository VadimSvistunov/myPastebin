package by.svistunovvv.spring.mypastebin.repository;

import by.svistunovvv.spring.mypastebin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    void deleteUserByEmail(String email);
}
