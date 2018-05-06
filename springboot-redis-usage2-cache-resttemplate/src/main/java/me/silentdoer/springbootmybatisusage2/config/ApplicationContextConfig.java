package me.silentdoer.springbootmybatisusage2.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @author silentdoer
 * @version 1.0
 */
@Configuration
@Import(me.silentdoer.springbootmybatisusage2.config.redis.RedisConfig.class)
public class ApplicationContextConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 无论是RedisTemplate或StringRedisTemplate都是需要配置RedisConnectionFactory的
     * 它们都是线程安全的，因此不需要@Scope以及用@Autowired比较好
     */
}
