package by.svistunovvv.spring.mypastebin.repository.jpa;

import by.svistunovvv.spring.mypastebin.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpaPostRepository")
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUserId(Long userId);
    Post findPostByHash(String hash);
    void deletePostsByUserId(Long userId);
    void deletePostByHash(String hash);
}
