package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleApiConversionService implements ConversionService {

    private final UrlProvider urlProvider;
    private final RestTemplate restTemplate;

    public GoogleApiConversionService(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
        restTemplate = new RestTemplate();
    }

    @Override
    public String convertAddress(final String address) {
        final String url = urlProvider.getUrl(address);
        return restTemplate.getForObject(url,String.class);
    }

    @Override
    public String convertCoordinates(final double latitude,final double longitude) {
        String [] coor = new String[2];
        final String lat = String.valueOf(latitude);
        final String lng = String.valueOf(longitude);
        final String url = urlProvider.getUrl(lat,lng);
        return restTemplate.getForObject(url,String.class);
    }
}
