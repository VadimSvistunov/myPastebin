package by.svistunovvv.spring.mypastebin.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="_user")
public class User implements Serializable {
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
