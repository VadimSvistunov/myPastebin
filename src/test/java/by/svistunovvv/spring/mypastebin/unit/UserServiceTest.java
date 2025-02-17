package by.svistunovvv.spring.mypastebin.unit;

import by.svistunovvv.spring.mypastebin.model.entity.User;
import by.svistunovvv.spring.mypastebin.repository.jpa.UserRepository;
import by.svistunovvv.spring.mypastebin.service.UserService;
import by.svistunovvv.spring.mypastebin.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserByEmail() {
        User user = User.builder()
                .email("test@test.com")
                .username("testUsername")
                .build();
        when(userRepository.findUserByEmail("test@test.com")).thenReturn(user);

        User result = userService.findByEmail("test@test.com");

        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());
    }
}
