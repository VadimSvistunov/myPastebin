package by.svistunovvv.spring.mypastebin.service.impl;

import by.svistunovvv.spring.mypastebin.exception.UserNotFoundException;
import by.svistunovvv.spring.mypastebin.minio.MinioService;
import by.svistunovvv.spring.mypastebin.model.dto.PostRequest;
import by.svistunovvv.spring.mypastebin.model.entity.Post;
import by.svistunovvv.spring.mypastebin.model.entity.User;
import by.svistunovvv.spring.mypastebin.repository.PostRepository;
import by.svistunovvv.spring.mypastebin.service.PostService;
import by.svistunovvv.spring.mypastebin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository repository;
    private UserService userService;
    private MinioService minioService;

    @Override
    public Post savePostData(PostRequest postRequest) {
        User user = validatePostRequest(postRequest);

        if (!minioService.isBucketExist(postRequest.getEmail())) {
            minioService.makeBucket(postRequest.getEmail());
        }
        int hash = postRequest.hashCode();
        minioService.loadPostDataToMinio(postRequest, hash);

        Post post = Post.builder()
                .hash(hash)
                .user(user)
                .build();

        return repository.save(post);
    }

    private User validatePostRequest(PostRequest postRequest) {
        if (postRequest.getText() == null || postRequest.getText().isBlank()) {
            throw new IllegalArgumentException("Text cannot be null or empty.");
        }
        User user = userService.findByEmail(postRequest.getEmail());
        if (user == null) {
            throw new UserNotFoundException("No user found with email: " + postRequest.getEmail());
        }
        return user;
    }

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
