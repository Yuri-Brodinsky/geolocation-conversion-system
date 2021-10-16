package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;



@Service

public class GoogleApiConversionService implements ConversionService {

    private final UrlProvider urlProvider;
    private final RestOperations restOperations;


    public GoogleApiConversionService(UrlProvider urlProvider,RestOperations restOperations) {
        this.urlProvider = urlProvider;
        this.restOperations = restOperations;
    }


    @Override
    @Cacheable(cacheNames="coordinates")
    public Double [] convertAddress(String address) {
        String url = urlProvider.getUrl(address);
        return restOperations.getForObject(url,Double [].class);
    }

    @Override
    @Cacheable(cacheNames="addresses")
    public String convertCoordinates( double latitude, double longitude) {
        String url = urlProvider.getUrl(latitude,longitude);
        return restOperations.getForObject(url,String.class);
    }
}
