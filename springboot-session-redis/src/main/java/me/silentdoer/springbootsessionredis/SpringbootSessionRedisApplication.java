package me.silentdoer.springbootsessionredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
// 注，每次产生一个httpSession对象在redis里都会有三个key，其中sessions的是redis真正存储数据的
// 而expires则是真正的存储过期时间的，即下面的30实际上对应的是expires的key的过期时间，过期了在redis
// 里也不会立刻删除存储的数据，但是springboot里访问时会依赖该过期时间因此即便redis里还有数据也将无法普通形式访问到；
// TODO 每次访问httpSession都会依赖下面的30来重置expires的值（sessions的也会，估计就是30*10）
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30, redisNamespace = "my:session")
public class SpringbootSessionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSessionRedisApplication.class, args);
    }
}
