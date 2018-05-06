package me.silentdoer.springbootcache.controller;

import lombok.extern.slf4j.Slf4j;
import me.silentdoer.springbootcache.test.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author silentdoer
 * @version 1.0
 */
@RestController
@Slf4j
public class MockController {
    @Autowired
    private CacheService cacheService;

    @GetMapping("/test1")
    public String test1(Long uid){
        log.info("test1 in");

        return this.cacheService.doService(uid);
    }
}
