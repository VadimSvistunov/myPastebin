package by.svistunovvv.spring.mypastebin.service.impl;

import by.svistunovvv.spring.mypastebin.model.Post;
import by.svistunovvv.spring.mypastebin.repository.PostRepository;
import by.svistunovvv.spring.mypastebin.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository repository;

    @Override
    public List<Post> findPostsByUserId(Long userId) {
        return repository.findPostsByUserId(userId);
    }

    @Override
    public Post findPostByHash(String hash) {
        return repository.findPostByHash(hash);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public Post update(Post post) {
        return repository.save(post);
    }

    @Override
    public void delete(String hash) {
        repository.deletePostByHash(hash);
    }
}
