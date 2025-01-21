package by.svistunovvv.spring.mypastebin.repository;

import by.svistunovvv.spring.mypastebin.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUserId(Long userId);
    Post findPostByHash(String hash);
    void deletePostsByUserId(Long userId);
    void deletePostByHash(String hash);
}
