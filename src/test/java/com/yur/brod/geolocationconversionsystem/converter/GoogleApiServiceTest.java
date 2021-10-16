package com.yur.brod.geolocationconversionsystem.converter;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GoogleApiServiceTest {

    interface Datasource {
        void get();
    }
    static class Service {
        private final Datasource datasource;

        Service(Datasource datasource) {
            this.datasource = datasource;
        }

        public Double [] convertAddress( String s) {
            datasource.get();
            return new Double[]{2.01,4.05};
        }
        public String convertCoordinates(double latitude, double longitude) {
            datasource.get();
            return "address";
        }
    }

    @Configuration
    @EnableCaching
    static class Config {

        // Simulating your caching configuration
        @Bean
        CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("sample");
        }

        // A repository mock instead of the real proxy
        @Bean
        Service service() {
            return new Service(datasource());
        }
        @Bean
        Datasource datasource() {
            return Mockito.mock(Datasource.class);
        }
    }

    @Autowired CacheManager manager;
    @Autowired
    Service service;
    @Autowired
    Datasource datasource;

    @Test
    public void methodInvocationShouldBeCached() {

        service.convertAddress("address");
        verify(datasource,times(1)).get();
        /*verify(service, times(1)).findByEmail("foo");
        assertThat(manager.getCache("sample").get("foo"), is(notNullValue()));
        // Set up the mock to return *different* objects for the first and second call
        when(service.findByEmail(Mockito.any(String.class))).thenReturn(first, second);

        // First invocation returns object returned by the method
        Object result = service.findByEmail("foo");
        assertThat(result, is(first));

        // Second invocation should return cached value, *not* second (as set up above)
        result = service.findByEmail("foo");
        assertThat(result, is(first));

        // Verify repository method was invoked once
        verify(service, times(1)).findByEmail("foo");
        assertThat(manager.getCache("sample").get("foo"), is(notNullValue()));

        // Third invocation with different key is triggers the second invocation of the repo method
        result = service.findByEmail("bar");
        assertThat(result, is(second));*/
    }
}