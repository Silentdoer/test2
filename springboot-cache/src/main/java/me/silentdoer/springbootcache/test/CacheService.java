package me.silentdoer.springbootcache.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author silentdoer
 * @version 1.0
 */
@Service
@Slf4j
public class CacheService {

    /**
     * 这种命名方式似乎不错
     *
     * TODO 如果添加了redis的正确配置，那么这个缓存还会写到redis里
     * @param uid
     * @return
     */
    @Cacheable("doServiceCacheService")
    public String doService(Long uid){
        log.info("doService");
        return uid + 180 + "";
    }
}
