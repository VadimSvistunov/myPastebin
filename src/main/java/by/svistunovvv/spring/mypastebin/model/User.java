package by.svistunovvv.spring.mypastebin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String username;
    @NonNull
    private String email;
    private String password;

}
