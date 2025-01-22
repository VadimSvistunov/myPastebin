package by.svistunovvv.spring.mypastebin.model.dto;

import lombok.Data;

@Data
public class PostRequest {
    private String text;
    private String email;
}
