package by.svistunovvv.spring.mypastebin.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "post")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    @NonNull
    private String url;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
