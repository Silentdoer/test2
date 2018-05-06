package me.silentdoer.springbootmybatisusage2.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * TODO 看了下目前似乎无论Jedis或lectuce都必须配置RedisConnectionFactory，它们不会自动从application.properties里读取配置
 * 但是Lettuce的能够自动读取application.properties中的配置
 *
 * @author silentdoer
 * @version 1.0
 */
public class RedisConfig {
    /**
     * 经过测试这两个其实都可以不要，而且用@Autowired还会报错，注意StringRedisTemplate同样匹配RedisTemplate的类型；
     * 因为在org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
     * 已经配置了一个StringRedisTemplate或RedisTemplate的bean
     * 而RedisAutoConfiguration能生效是因为被@EnableAutoConfiguration作为了run的参数
     * 但是RedisAutoConfiguration是因为添加了spring-boot-starter-data-redis的pom包，因此该
     * 类才能作为插件被扫描到
     */

    /*@Bean
    public RedisConnectionFactory getConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        return factory;
    }

    @Bean
    public RedisTemplate<String, String> getRedisTemplate() {
        return new StringRedisTemplate(getConnectionFactory());
    }*/
}