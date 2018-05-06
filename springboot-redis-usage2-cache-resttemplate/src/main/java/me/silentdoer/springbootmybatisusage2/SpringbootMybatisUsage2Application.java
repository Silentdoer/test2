package me.silentdoer.springbootmybatisusage2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootMybatisUsage2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisUsage2Application.class, args);
    }
}
