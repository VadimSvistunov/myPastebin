package by.svistunovvv.spring.mypastebin.service;

import by.svistunovvv.spring.mypastebin.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findPostsByUserId(Long userId);
    Post findPostByHash(String hash);
    List<Post> findAll();
    Post save(Post post);
    Post update(Post post);
    void delete(String hash);
}
