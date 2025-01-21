package by.svistunovvv.spring.mypastebin.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(unique = true)
    private String username;
    @NonNull
    @Column(unique = true)
    private String email;
    private String password;

}
