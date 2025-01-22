package by.svistunovvv.spring.mypastebin.controller;

import by.svistunovvv.spring.mypastebin.model.entity.User;
import by.svistunovvv.spring.mypastebin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
@ResponseBody
public class UserController {
    private UserService service;

    @GetMapping("")
    public List<User> index() {
        return service.findAll();
    }

    @GetMapping("/{email}")
    public User get(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PostMapping("")
    public String save(@RequestBody User user) {
        service.save(user);
        return "Success save";
    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable String email) {
        service.delete(email);
        return "Success delete";
    }

    @PutMapping("/{email}")
    public String update(@RequestBody User user) {
        service.update(user);
        return "Success update";
    }
}
