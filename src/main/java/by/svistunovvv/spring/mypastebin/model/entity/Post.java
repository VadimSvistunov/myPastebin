package by.svistunovvv.spring.mypastebin.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("PostRedis")
@Table (name = "post")
public class Post implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(unique=true)
    private String hash;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
