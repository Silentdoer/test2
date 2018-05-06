package me.silentdoer.springbootjustsession.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author silentdoer
 * @version 1.0
 */
@RestController
@Slf4j
public class MockController {

    @GetMapping("/test1")
    public String test1(){
        log.info("test1 inxxx");
        return "OK";
    }

    /**
     * 当没有配置redis时，这个httpSession对象不会序列化到redis里，读取的时候也不会从redis里读取
     * 但是只要配置了spring-session-core，似乎这个httpSession对象就已经不是request.getSession()获得的
     * 而是由Spring先创建了一个实现了HttpSession接口的对象（应该是Spring自定义的）
     *
     * 也就是说添加了spring-session-core的pom包后就不需要tomcat管理的那个session了，而是统一由springboot来管理
     * 这里如果httpSession有set操作那么springboot就会通过response将这个id设置为前端的SESSIONID的cookie，
     * 而前端访问时也会提供这个id，然后服务端springboot通过request获得这个cookie，并从springboot管理的session注册表里
     * 操作session对应的session对象而非从tomcat提供的session注册表里获得对应的session，即
     * TODO 创建session不再是 request.getSession()，而是springMVC自定义的方法来创建且也是由springMVC来一级管理
     *
     * 但是这样仍然无法解决当分流时同一个用户的两次请求分到不同服务器上导致产生多个session的问题（之前是session由tomcat管理
     * 现在是由springboot管理），但是可以再添加redis并@EnableRedisHttpSession，由于现在是springboot管理session，因此
     * 自然可以定制化session的操作同时将session写到redis；而之前由tomcat管理时是无法做到的；
     *
     * @param httpSession
     * @return
     */
    @GetMapping("/test2")
    public String test2(HttpSession httpSession){
        log.info("test2 in");
        httpSession.setAttribute("key", "value");
        log.info(httpSession.getId() + httpSession.getAttribute("key"));
        return "Ok";
    }
}
