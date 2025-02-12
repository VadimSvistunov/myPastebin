package by.svistunovvv.spring.mypastebin.repository.redis;

import by.svistunovvv.spring.mypastebin.model.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("redisPostRepository")
public interface RedisPostRepository extends CrudRepository<Post, Long> {
    Optional<Post> findByHash(String hash);
}
