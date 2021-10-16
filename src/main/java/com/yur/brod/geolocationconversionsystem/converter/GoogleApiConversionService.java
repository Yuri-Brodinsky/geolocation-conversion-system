package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service

public class GoogleApiConversionService implements ConversionService {

    private final UrlProvider urlProvider;
    private final RestTemplate restTemplate;


    public GoogleApiConversionService(UrlProvider urlProvider,RestTemplate restTemplate) {
        this.urlProvider = urlProvider;
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(cacheNames="coordinates")
    public Double [] convertAddress(String address) {
        String url = urlProvider.getUrl(address);
        return restTemplate.getForObject(url,Double [].class);
    }

    @Override
    @Cacheable(cacheNames="addresses")
    public String convertCoordinates( double latitude, double longitude) {
        String url = urlProvider.getUrl(latitude,longitude);
        System.out.println(url);
        return restTemplate.getForObject(url,String.class);
    }
}
