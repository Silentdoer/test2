package me.silentdoer.springbootmybatisusage2.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author silentdoer
 * @version 1.0
 */
@Slf4j
@Service
public class MockCachingService {
    /**
     * TODO 注意@Cacheable的value必须有值，它是将方法返回值作为value对应的key，每个@Cacheable的key都不能重复
     * 否则重复的那个方法有可能会访问不到
     *
     * TODO 重要，如果在application.properties正确配置了redis，那么Cacheable的值会被写到redis里，且没有expire（经过了测试）
     * 它在redis里的key是chacheKey1::100，100就是uid的值，即cacheKey1和参数值共同构成key
     * @param uid
     * @return
     */
    @Cacheable("doServiceMockCachingService")
    public String doService(Long uid){
        log.info("doService方法进入了");
        return StringUtils.join("silentdoer", uid);
    }

    /**
     * 原理是spring里会先拦截，发现方法上有@Cacheable，然后取出value作为key搜索Caching的注册表，
     * 找到则直接返回对应的value而不执行此方法，因此每个key都不应该重复；
     *
     * 通过在@Cachable的方法所在的class上添加CacheConfig(cacheNames = "users")似乎可以自定义缓存对象
     * ，即不是用默认的缓存对象来存储方法返回值
     * @param uid
     * @return
     */
    @Cacheable("doServiceMockCachingService2")
    public String doService2(Long uid){
        log.info("doService2 进入了");
        return StringUtils.join("uuuu", uid);
    }
}
