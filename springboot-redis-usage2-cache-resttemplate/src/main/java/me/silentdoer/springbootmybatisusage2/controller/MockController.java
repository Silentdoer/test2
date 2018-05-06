package me.silentdoer.springbootmybatisusage2.controller;

import lombok.extern.slf4j.Slf4j;
import me.silentdoer.springbootmybatisusage2.test.MockCachingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author silentdoer
 * @version 1.0
 */
@RestController
@Slf4j
public class MockController {
    /**
     * `<String, String>`确实是说比如value类型是String时put的value就只能是字符串（不能是字节流）
     * 当类型是ZSet、Set、List时value的元素的类型只能是字符串不能是字节流（如果是<String, Object>那么第二个参数可以
     * 传任意值，在RedisTemplate内部有个序列化器来将对象序列化为字节流（即便传的是字符串也将变成字节流）；
     *
     * 因此其实就可以用StringRedisTemplate，然后如果需要存非字符串的数据可以先转换为字节流然后用Base64转换为字符串
     *
     * 目前而言如果在application.properties里配置的是lettuce那么是可以不配置RedisConnectionFactory和RedisTemplate的
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Spring里还有很多Template，比如JdbcTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private MockCachingService mockCachingService;

    @GetMapping("/test1")
    public void test1(HttpSession httpSession){
        httpSession.setAttribute("silentdoer", "hello, world.");
        log.info(StringUtils.join("id:", httpSession.getId(), "value:", httpSession.getAttribute("silentdoer")));
        redisTemplate.opsForValue().set("foo2", "中文");

        // TODO 注意，这个是单例，网上查了下RedisTemplate是线程安全的；
        ValueOperations<String, String> valueO = stringRedisTemplate.opsForValue();
        valueO.set("foo", "中文");
    }

    /**
     * 由RestTemplate的api可知delete和put都是没有返回值的，而POST和GET是有返回值的
     * @return
     */
    @GetMapping("/test2")
    public String test2(){
        /* http://192.168.0.17/test/bbb */
        // delete没有返回值且不能有请求体（RestTemplate标准里是这样的）
        //this.restTemplate.delete();
        // put没有返回值但可以有请求体（注意，这里的返回值是指响应体而不是响应行、响应头）
        //this.restTemplate.put();
        // get有返回值但不能有请求体
        //this.restTemplate.getForObject()
        // post有返回值且可以有请求体
        /*---------------------------------*/
        String responseBody = this.restTemplate.getForObject("http://192.168.0.17/test/bb", String.class);
        System.out.println(responseBody);
        return responseBody;
    }

    @GetMapping("/test3")
    public String test3(Long uid){
        log.info(StringUtils.join("test3进入了"));
        return StringUtils.join(this.mockCachingService.doService(uid), this.mockCachingService.doService2(uid));
    }
}