package by.svistunovvv.spring.mypastebin.service.impl;

import by.svistunovvv.spring.mypastebin.exception.PostNotFoundException;
import by.svistunovvv.spring.mypastebin.exception.UserNotFoundException;
import by.svistunovvv.spring.mypastebin.minio.MinioService;
import by.svistunovvv.spring.mypastebin.model.dto.PostRequest;
import by.svistunovvv.spring.mypastebin.model.dto.PostResponse;
import by.svistunovvv.spring.mypastebin.model.entity.Post;
import by.svistunovvv.spring.mypastebin.model.entity.User;
import by.svistunovvv.spring.mypastebin.repository.jpa.PostRepository;
import by.svistunovvv.spring.mypastebin.repository.redis.RedisPostRepository;
import by.svistunovvv.spring.mypastebin.service.PostService;
import by.svistunovvv.spring.mypastebin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository repository;
    private RedisPostRepository redisPostRepository;
    private UserService userService;
    private MinioService minioService;
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Post savePostData(PostRequest postRequest) {
        User user = validatePostRequest(postRequest);

        if (!minioService.isBucketExist(Integer.toString(postRequest.getEmail().hashCode()))) {
            minioService.makeBucket(Integer.toString(postRequest.getEmail().hashCode()));
        }
        String hash = Integer.toString(postRequest.hashCode());
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
    public List<PostResponse> findPostsByUserId(Long userId) {
//        return repository.findPostsByUserId(userId);
        return null;
    }

    @Override
    public PostResponse findPostByHash(String hash) {
        Post post = redisPostRepository.findByHash(hash)
                .orElseGet(() -> repository.findPostByHash(hash).map(
                        foundPost -> {
                            redisPostRepository.save(foundPost);
                            return foundPost;
                        })
                        .orElseThrow(() -> new PostNotFoundException("No post found with hash: " + hash)));

        String bucketName = Integer.toString(post.getUser().getEmail().hashCode());
        if(minioService.isBucketExist(bucketName)) {
            String text = minioService.getPostText(bucketName, hash);
            return PostResponse.builder()
                    .email(post.getUser().getEmail())
                    .hash(hash)
                    .text(text)
                    .build();
        }

        return null;
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
