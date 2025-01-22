package by.svistunovvv.spring.mypastebin.service;

import by.svistunovvv.spring.mypastebin.model.dto.PostRequest;
import by.svistunovvv.spring.mypastebin.model.entity.Post;

import java.util.List;

public interface PostService {
    Post savePostData(PostRequest postRequest);
    List<Post> findPostsByUserId(Long userId);
    Post findPostByHash(String hash);
    List<Post> findAll();
    Post save(Post post);
    Post update(Post post);
    void delete(String hash);
}
