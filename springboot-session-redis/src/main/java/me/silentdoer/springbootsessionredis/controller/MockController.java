package me.silentdoer.springbootsessionredis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author silentdoer
 * @version 1.0
 */
@Slf4j
@RestController
public class MockController {

    /**
     * TODO 成功，以后这种单例的就用@Autowired即可，证明Jedis也可以而不是只能Lettuce
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加了spring-session-core的jar包后session的产生方式就不再是由request.getSession()，管理也不是由tomcat来管理
     *
     * 序列化到redis的对象的key是那个sessions里的，但是过期时间是由expires管理
     *
     * 注意session对象在spring内部也维护了一个，而不只是存储到了redis里
     * @param httpSession
     * @return
     */
    @GetMapping("/test1")
    public long test1(HttpSession httpSession){  // TODO 注意现在就已经是可以多个服务实现session共享了
        // out: org.springframework.session.web.http.SessionRepositoryFilter$SessionRepositoryRequestWrapper$HttpSessionWrapper
        // 果然不是tomcat里的session，而是spring里的
        log.info(httpSession.getClass().getName());
        log.info(httpSession.getId());
        // TODO 注意如果调用两次会在redis里产生两个sessions，即后者替代前者？？
        // TODO 这个方法内部似乎就有操作redis的代码
        httpSession.setAttribute("silentdoer", "helloworld");
        //log.info(httpSession.);
        log.info("由于配置了@EnableRedisHttpSession和添加了spring-session-core等包，session会序列化到redis");
        return 88L;
    }

    /**
     *
     * TODO 重要，无论session是否有key都不会产生新的session，之前是因为expires过期了，但是sessions会稍微保留一会
     * TODO 然后自己以为会产生多个session
     * @param httpSession
     * @return
     */
    @GetMapping("/test4")
    public String test4(HttpSession httpSession){
        log.info("test4 in");
        httpSession.setAttribute("key3", "uuuu");
        return httpSession.getAttribute("silentdoer").toString();
    }

    /**
     * TODO 重要，经过仔细测试，只要访问了就会重置expires，即便参数里没有httpSession
     */
    @GetMapping("/test2")
    public void test2(){
        log.info("test2 in");
        log.info(stringRedisTemplate.keys("*").toString());
    }

    @GetMapping("/test3")
    public String test3(HttpSession httpSession){
        String silentdoer = httpSession.getAttribute("silentdoer").toString();
        log.info(silentdoer);
        return silentdoer;
    }
}
