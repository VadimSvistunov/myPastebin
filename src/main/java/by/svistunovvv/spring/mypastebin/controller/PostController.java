package by.svistunovvv.spring.mypastebin.controller;

import by.svistunovvv.spring.mypastebin.exception.UserNotFoundException;
import by.svistunovvv.spring.mypastebin.model.dto.PostRequest;
import by.svistunovvv.spring.mypastebin.model.entity.Post;
import by.svistunovvv.spring.mypastebin.model.entity.User;
import by.svistunovvv.spring.mypastebin.service.PostService;
import by.svistunovvv.spring.mypastebin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@AllArgsConstructor
@ResponseBody
public class PostController {
    private PostService service;
    private UserService userService;

    @GetMapping("")
    public List<Post> index() {
        return service.findAll();
    }

    @GetMapping("/{email}")
    public List<Post> getAllPostsByUser(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return service.findPostsByUserId(user.getId());
    }


    @GetMapping("/{hash}")
    public Post getPostByPostNumber(@PathVariable String hash) {
        return service.findPostByHash(hash);
    }

    @PostMapping("")
    public String save(@RequestBody PostRequest postRequest) {
        try {
            service.savePostData(postRequest);
            return "Success save";
        } catch (IllegalArgumentException | UserNotFoundException e) {
            return e.getMessage();
        }
    }

    @PutMapping("")
    public String update(@RequestBody Post Post) {
        service.update(Post);
        return "Success update";
    }

    @DeleteMapping("/{hash}")
    public String delete(@PathVariable String hash) {
        service.delete(hash);
        return "Success delete";
    }
}
