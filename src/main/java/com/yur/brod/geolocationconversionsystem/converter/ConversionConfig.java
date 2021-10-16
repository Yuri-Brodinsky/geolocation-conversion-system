package com.yur.brod.geolocationconversionsystem.converter;

import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class ConversionConfig {
    @Value("${cache.expiration-time}")
    private long expTimeWithWrite;
    @Value("${cache.extra-time}")
    private long expTimeWIthAccess;
    @Value("${cache.max-size}")
    private long cacheMaxSize;

    @Bean
    public RestOperations restOperations(){
        return new RestTemplate();
    }
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager(){
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                                .expireAfterWrite(expTimeWithWrite, TimeUnit.MINUTES)
                                .expireAfterAccess(expTimeWIthAccess,TimeUnit.MINUTES)
                                .maximumSize(cacheMaxSize)
                                .build().asMap(),
                        false);
            }
        };
    }
}
