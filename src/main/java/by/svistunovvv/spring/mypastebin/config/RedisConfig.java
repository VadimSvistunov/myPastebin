package by.svistunovvv.spring.mypastebin.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@EnableRedisRepositories(
        basePackages = "by.svistunovvv.spring.mypastebin.repository.redis",
        repositoryImplementationPostfix="redis"
)
@EnableJpaRepositories(
        basePackages = "by.svistunovvv.spring.mypastebin.repository.jpa",
        repositoryImplementationPostfix="jpa"
)
@AllArgsConstructor
@NoArgsConstructor
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                redisHost,
                redisPort
        );
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
}
