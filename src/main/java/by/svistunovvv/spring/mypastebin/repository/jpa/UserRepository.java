package by.svistunovvv.spring.mypastebin.repository.jpa;

import by.svistunovvv.spring.mypastebin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    void deleteUserByEmail(String email);
}
