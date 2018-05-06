package me.silentdoer.springbootredisusage.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public void test1(){
        log.info("进来了test1");
    }

    /**
     * 这个httpSession对象并非SpringMVC通过httpServletRequest.getSession()获得的，而是它手动产生的
     * 然后这个方法结束后会判断这个httpSession对象是否有setAttribute(..)有则执行getSession()并将参数中set的所有attribute设置到真正session里
     * 但是当和redis整合后在springMVC主动产生httpSession对象时就会将主动产生的设置到或说补充到redis里；
     * @param httpSession
     */
    @GetMapping("/test2")
    public void test2(HttpSession httpSession){
        log.info("test2 in.");
        log.info(StringUtils.join("是否为null", httpSession == null, "id是", httpSession.getId()));
    }

    @GetMapping("/test3")
    public void test3(HttpSession httpSession){
        log.info("进来了test3, Id是", httpSession.getId());
    }
}
