package by.svistunovvv.spring.mypastebin.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    private String email;
    private String text;
    private String hash;
}
